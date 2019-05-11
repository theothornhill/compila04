import bytecode.*;
import bytecode.type.*;
import bytecode.instructions.*;

public class BinaryExpr extends Expr {
    Object e1;
    Object e2;
    Object op;
    Instruction operator;
    public boolean isLogical;
    public boolean isArit;
    public boolean isBoolean;

    public BinaryExpr(Object e1, Object op, Object e2) throws Exception {
        this.e1 = e1;
        this.op = op;
        this.e2 = e2;
        setOperationType(op.toString());
    }

    public void setOperationType(String op) {
        if (op.equals("||") || op.equals("&&"))
            isLogical = true;
        else if (op.equals("<")
                 || op.equals("<=")
                 || op.equals(">")
                 || op.equals(">=")
                 || op.equals("=")
                 || op.equals("<>"))
            isBoolean = true;
        else
            isArit = true;
    }

    public void setExprType(Expr e1, Expr e2) throws Exception {
        // TODO: this is very dirty and error prone code. Fix
        if (isArit) {
            if (op.equals("/")) {
                if (e1.type.equals("int") && e2.type.equals("int")    ||
                    e1.type.equals("int") && e2.type.equals("float")  ||
                     e1.type.equals("float") && e2.type.equals("int") ||
                     e1.type.equals("float") && e2.type.equals("float"))
                this.type = new Type("float");
                else
                    throw new Exception("Arguments of divsion operation not correct type");
            }
            else if (e1.type.equals("int")  && e2.type.equals("int"))
                this.type = new Type("int");
            else if (e1.type.equals("int") && e2.type.equals("float") ||
                     e1.type.equals("float") && e2.type.equals("int") ||
                     e1.type.equals("float") && e2.type.equals("float"))
                this.type = new Type("float");
            else
                throw new Exception("Arguments of artihmetic operation not correct type");
        } else if (isBoolean) {
            if (e1.type.equals("int") && e2.type.equals("bool")    ||
                e1.type.equals("float") && e2.type.equals("bool")  ||
                e1.type.equals("string") && e2.type.equals("bool") ||
                e1.type.equals("bool") && e2.type.equals("int")    ||
                e1.type.equals("bool") && e2.type.equals("float")  ||
                e1.type.equals("bool") && e2.type.equals("string"))
                throw new Exception("Arguments of relational operation not correct type");
            this.type = new Type("bool");
        } else if (isLogical) {
            if (e1.type.equals("bool") && e2.type.equals("bool"))
                this.type = new Type("bool");
            else
                throw new Exception("Arguments of logical operation not correct type");
        }
        else
            throw new Exception("Arguments of operation not correct type");
    }

    public void typeCheck(SymbolTable table, Object scope) throws Exception {
        typeCheckExpr(e1, table, scope);
        typeCheckExpr(e2, table, scope);
        setExprType(((Expr)e1), ((Expr)e2));
    }

    public void typeCheckExpr(Object e, SymbolTable table, Object scope) throws Exception {
        Object exp = table.lookup(scope, e.toString());
        Var v = null;
        Param p = null;
        if (e instanceof Var) {
            ((Expr)e).typeCheck(table, scope);
            if (((Var)e).expr != null)
                exp = table.lookup(scope, ((Var)e).expr.toString());
            else
                exp = table.lookup(scope, ((Var)e).name.toString());  
        }
        if (exp instanceof Param) {
            p = (Param)exp;
            RecDecl record = null;
            if (paramtypeIsUserDefined(p)) {
                record = (RecDecl)table.lookup(scope, p.type.toString());
                Param recParam = (Param)table.lookup(record, e.toString());
                ((Expr)e).type = new Type(recParam.type.toString());
            } else {
                ((Expr)e).type = new Type(p.type.toString());
            }
            p.typeCheck(table, scope);
        } else if (exp instanceof VarDecl) {
            ((Expr)e).type = new Type(((VarDecl)exp).type.toString());
        } 
    }

    public boolean paramtypeIsUserDefined(Param p) {
        return !(p.type.equals("string") ||
                 p.type.equals("float")  ||
                 p.type.equals("int")    ||
                 p.type.equals("bool"));
    }

    public void addToSymbolTable(SymbolTable table) {
    }

    public void generateCode(CodeProcedure proc, SymbolTable table, Object scope) {
        // CodeGenerationHelper.exprHelper(proc, e1, table, scope);
        // CodeGenerationHelper.exprHelper(proc, e2, table, scope);
        codeGenExpr(e1, proc, table, scope);
        codeGenExpr(e2, proc, table, scope);
        // proc.addInstruction(new LOADLOCAL(proc.variableNumber(e1.toString())));
        // proc.addInstruction(new LOADLOCAL(proc.variableNumber(e2.toString())));
        proc.addInstruction(CodeGenerationHelper.instructionHelper(proc, op));
    }

    public void codeGenExpr(Object ex, CodeProcedure proc,
                            SymbolTable table, Object scope) {
        if (ex instanceof Literal)
            proc.addInstruction(CodeGenerationHelper.literalHelper(((Literal)ex), proc));
        else if (ex instanceof Var) {
            if (((Var)ex).expr != null) {
                CodeGenerationHelper.generateRecordGetField(ex, proc, table, scope);
            } else {
                proc.addInstruction(new LOADLOCAL(proc.variableNumber(ex.toString()))); 
            }
        }
        
    }
    
    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(BINARY_OPERATION " + op.toString());
        sb.append(PrintHelper.newlineAndIndentWithHelper(e1, indentLevel+1));
        sb.append(PrintHelper.newlineAndIndentWithHelper(e2, indentLevel+1));
        sb.append(PrintHelper.endWithParen(indentLevel));
        return sb.toString();
    }

}

import bytecode.*;
import bytecode.type.*;
import bytecode.instructions.*;

public class BinaryExpr extends Expr {
    Object e1;
    Object e2;
    Object op;
    Instruction operator;
    // Booleans for type-checker. In example we need our type checker to know
    // when expressions are boolean
    public boolean isLogical;
    public boolean isArit;
    public boolean isBoolean;

    public BinaryExpr(Object e1, Object op, Object e2) throws Exception {
        this.e1 = e1;
        this.op = op;
        this.e2 = e2;
        setOperationType(op.toString());
    }

    // This is not proper type checking. Think through this.
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
        // System.out.println(e1type);
        if (isArit) {
            if (op.equals("/"))
                this.type = new Type("float");
            else if (e1.type.equals("int")  && e2.type.equals("int"))
                this.type = new Type("int");
            else if (e1.type.equals("int") && e2.type.equals("float") ||
                     e1.type.equals("float") && e2.type.equals("int") ||
                     e1.type.equals("float") && e2.type.equals("float"))
                this.type = new Type("float");

        } else if (isBoolean) {
            this.type = new Type("bool");
        } else if (isLogical) {
            if (e1.type.equals("bool") && e2.type.equals("bool"))
                this.type = new Type("bool");
        }
        else
            throw new Exception("Arguments of operation not correct type");

    }

    public void typeCheck(SymbolTable table, Object scope) throws Exception {

        // if (!((Expr)e1).type.equals(((Expr)e2).type))
        //     throw new Exception("Operands in binary expr not the same type");
        typeCheckExpr(e1, table, scope);
        typeCheckExpr(e2, table, scope);
        System.out.println(e1 + " " + e2);
        setExprType(((Expr)e1), ((Expr)e2));

    }

    public void typeCheckExpr(Object e, SymbolTable table, Object scope) throws Exception {
        Var v = null;
        if (e instanceof Var) {
            v = (Var)e;
            v.typeCheck(table, scope);
            e = table.lookup(scope, v.name); 
        }
    }

    public void addToSymbolTable(SymbolTable table) {
        // table.insert("Name", this.getClass());
        // table.insert("Operation", op);
        // table.insert("Expr1", CodeGenerationHelper.getTable(e1));
        // table.insert("Expr2", CodeGenerationHelper.getTable(e2));
    }

    public void generateCode(CodeProcedure proc) {
        CodeGenerationHelper.exprHelper(proc, e1);
        proc.addInstruction(CodeGenerationHelper.instructionHelper(proc, op));
        CodeGenerationHelper.exprHelper(proc, e2);
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

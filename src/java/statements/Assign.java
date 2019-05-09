import bytecode.*;
import bytecode.type.*;
import bytecode.instructions.*;

public class Assign extends Stmt {
    public Assign(Object name, Object e) {
        this.e2 = name;
        this.e = e;
    }

    public void typeCheck(SymbolTable table, Object scope) throws Exception {
        Object expr = null;
        RecDecl o = null;
        Param p = null;
        if (((Var)e2).expr != null)
            expr = table.lookup(scope, ((Var)e2).expr.toString());
        else
            expr = table.lookup(scope, ((Var)e2).name.toString());

        if (expr == null)
            throw new Exception("Symbol " + e2 + " is not declared");

        if (expr instanceof VarDecl) {
            if (((Expr)e2).expr == null) {
                if (table.lookup(scope, ((Var)e2).name) == null)
                    throw new Exception("Variable not declared");
                if (e instanceof Call) {
                    ((Call)e).typeCheck(table, scope);
                    if (!((Call)e).type.equals(((VarDecl)expr).type.toString()))
                        throw new Exception("Wrong type in assignment");
                } else {
                    ((Expr)e).typeCheck(table, scope);
                    if (!((Expr)e).type.equals(((VarDecl)expr).type.toString())) {
                        if (!isAssignable((VarDecl)expr)) {
                            throw new Exception("Wrong type in assignment on " + expr + " and " + e); 
                        } 
                    }
                }


            }
            
            if (((Expr)e2).expr != null) {
                // Now we know it is a RecDecl
                o = (RecDecl)table.lookup(scope, ((VarDecl)expr).type.toString());
                if (o == null)
                    throw new Exception("Record not declared");
                p = (Param)table.lookup(o, ((Var)e2).name);
                if (p == null)
                    throw new Exception("Attribute not declared");
                if (e instanceof BinaryExpr)
                    ((BinaryExpr)e).typeCheck(table, scope);
                if (!((Expr)e).type.equals(p.type.toString())) {
                    if (!isAssignable(p)) {
                        throw new Exception(""+ p + " type: " + p.type + " cannot be assigned a " + ((Expr)e).type); 
                    }
                }                    
            }
        }
    }

    // Checks whether conversion can be done implicitly
    public boolean isAssignable(VarDecl v) {
       if (v.type.equals("float") && ((Expr)e).type.equals("int"))
            return true;
        return false;
    }

    public boolean isAssignable(Param p) {
       if (p.type.equals("float") && ((Expr)e).type.equals("int"))
            return true;
        return false;
    }

    public Object getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Object node) {
        this.createdBy = node;
    }

    public void setCreatorOf() {
        // Does not do anything yet
    }

    public void setLexicalScopeLevel(int scope) {
        this.lexicalScopeLevel = scope;
    }

    public void addToSymbolTable(SymbolTable table) {
        try {
            table.insert("assign");
            table.insert(name);
            table.insert(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateCode(CodeFile codeFile, SymbolTable table, Object scope) {

    }

    public void generateCode(CodeProcedure proc, SymbolTable table, Object scope) {
        // System.out.println(e.getClass());
        if (e instanceof Literal) {
            proc.addInstruction(CodeGenerationHelper.literalHelper(((Literal)e)));
            if (e2 instanceof Var)
                if (((Var)e2).expr != null) {
                    System.out.println(((Var)e2) + " will be assigned a " + e);
                    proc.addInstruction(new LOADLOCAL(proc.variableNumber("f")));
                    proc.addInstruction(new PUTFIELD(proc.fieldNumber("Foo", ((Var)e2).toString()),
                                                     proc.structNumber("Foo")));
                    // proc.addInstruction(new STORELOCAL(proc.variableNumber(((Var)e2).expr.toString())));
                    return;
                }

        }

        else if (e instanceof BinaryExpr)
            ((BinaryExpr)e).generateCode(proc, table, scope);
        else if (e instanceof Var)
            proc.addInstruction(new LOADLOCAL(proc.variableNumber(e.toString())));
        else if (e instanceof New) {
            ((New)e).generateCode(proc, table, scope);
            // System.out.println(((Expr)e2).expr);
            // if (e instanceof Literal)
            //     proc.addInstruction(CodeGenerationHelper.literalHelper(((Literal)e)));
        } else if (e instanceof Call) {
            ((Call)e).generateCode(proc, table, scope);
        }

        proc.addInstruction(new STORELOCAL(proc.variableNumber(e2.toString())));
    }

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(ASSIGN_STMT");
        sb.append(PrintHelper.newlineAndIndentWithHelper(e2, indentLevel+1));
        sb.append(PrintHelper.newlineAndIndentWithHelper(e, indentLevel+1));
        sb.append(PrintHelper.endWithParen(indentLevel));
        return sb.toString();
    }

    public String toString() {
        return e2.toString();
    }

}

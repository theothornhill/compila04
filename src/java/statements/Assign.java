import bytecode.*;
import bytecode.type.*;

public class Assign extends Stmt {
    public Assign(Object name, Object e) {
        this.e2 = name;
        this.e = e;
    }

    public void typeCheck(SymbolTable table, Object scope) throws Exception {
        Object expr = table.lookup(scope, e2.toString());
        if (expr == null)
            throw new Exception("Symbol " + e + " is not declared");

        System.out.println(((VarDecl)expr) + " " + ((VarDecl)expr).type + ": " + e);

        if (e instanceof Var)
            ((Var)e).typeCheck(table, scope);

        if (e instanceof BinaryExpr) {
            ((BinaryExpr)e).typeCheck(table, scope);
        }

        // TODO: Do this later
        // if (e instanceof Call) {
        //     ((Call)e).typeCheck(table, scope);
        // }


        
        if (expr instanceof VarDecl) {
            VarDecl v = (VarDecl)expr;
            Object vname = table.lookup(this.getCreatedBy(), v.name);
            if (v == null)
                throw new Exception("" + v + "." + v.name + " not declared");
            if (vname == null)
                throw new Exception("" + v + "." + v.name + " not declared");
            if (!v.type.equals(((Expr)e).type.toString()) && !isAssignable(v))
                throw new Exception("" + v.type + " cannot be assigned a " + ((Expr)e).type);
        }
    }

    // Checks whether conversion can be done implicitly
    public boolean isAssignable(VarDecl v) {
        if (v.type.equals("float") && ((Expr)e).type.equals("int"))
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

    public void generateCode(CodeFile codeFile) {
        codeFile.addStringConstant("Added constant here asshole");
    }

    public void generateCode(CodeProcedure proc) {

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

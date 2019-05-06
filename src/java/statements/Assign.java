import bytecode.*;
import bytecode.type.*;

public class Assign extends Stmt {
    public Assign(Object name, Object e) {
        this.e2 = name;
        this.e = e;
    }

    public void typeCheck(SymbolTable table) throws Exception {
        Object expr = table.lookup(this.e2.toString());
        if (expr == null)
            throw new Exception("Symbol " + e2 + " is not declared");
        if (expr instanceof VarDecl) {
            VarDecl v = (VarDecl)expr;
            Object vname = table.lookup(v.name);
            if (v == null)
                throw new Exception("" + v + "." + v.name + " not declared");
            if (vname == null)
                throw new Exception("" + v + "." + v.name + " not declared");
            if (!v.type.equals(((Expr)e).type.toString()))
                throw new Exception("" + v.type + " cannot be assigned a " + ((Expr)e).type);
        }
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
        table.insert("assign");
        table.insert(name);
        table.insert(e);
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

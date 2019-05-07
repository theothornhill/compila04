import bytecode.*;
import bytecode.type.*;

public class New extends Expr {
    String name;
    public New(String name) {
        this.name = name;
        this.type = new Type(name);
    }

    public void typeCheck() throws Exception {
        Object struct = table.lookup(this, name);
        if (struct == null)
            throw new Exception("Argument of new is not declared");
    }

    public void typeCheck(SymbolTable table, Object scope) throws Exception {
        Object struct = table.lookup(scope, name);
        if (struct == null || !(struct instanceof RecDecl))
            throw new Exception("Argument of new is not declared");
    }

    public void addToSymbolTable(SymbolTable table) {
        try {
            table.insert(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateCode(CodeProcedure proc) {
        
    }

    public String toString() {
        return name;
    }

    public String printAst(int indentLevel) {
        return "(NEW " + PrintHelper.astHelper(name, indentLevel) + ")";
    }
}

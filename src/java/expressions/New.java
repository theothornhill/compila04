import bytecode.*;
import bytecode.type.*;

public class New extends Expr {
    String name;
    public New(String name) {
        this.name = name;
    }

    public void typeCheck(SymbolTable table) throws Exception {
        Object struct = table.lookup(name);
        if (struct == null)
            throw new Exception("Argument of new is not declared");
    }

    public void addToSymbolTable(SymbolTable table) {
        // Some reference stuff here. How to handle this?
        table.insert(name);
    }

    public void generateCode(CodeProcedure proc) {
        
    }

    public String printAst(int indentLevel) {
        return "(NEW " + PrintHelper.astHelper(name, indentLevel) + ")";
    }
}

import bytecode.*;
import bytecode.type.*;
import bytecode.instructions.*;

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

    public void addToSymbolTable(SymbolTable table) throws Exception {
        table.insert(name);
    }

    public void generateCode(CodeProcedure proc, SymbolTable table, Object scope) {
        proc.addInstruction(new NEW(proc.structNumber(type.toString())));
        
    }

    public String toString() {
        return name;
    }

    public String printAst(int indentLevel) {
        return "(NEW " + PrintHelper.astHelper(name, indentLevel) + ")";
    }
}

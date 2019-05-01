import bytecode.*;
import bytecode.type.*;
public abstract class Expr {
    Object expr;
    public SymbolTable table = new SymbolTable();
    public abstract String printAst(int indentLevel);
    public abstract void addToSymbolTable(SymbolTable table);
    public abstract void generateCode(CodeProcedure proc);

    public SymbolTable getTable() {
        return table;
    }

    public String toString() {
        return "Expression";
    }

}

import bytecode.*;

public abstract class Decl {
    public String name;
    public abstract String printAst(int indentLevel);
    public abstract void addToSymbolTable(SymbolTable table, int scope);
    public abstract void generateCode(CodeFile codeFile);

    public String toString() {
        return name;
    }
}

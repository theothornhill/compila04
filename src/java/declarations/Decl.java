import bytecode.*;

public abstract class Decl {
    public String name;
    public SymbolTable table = new SymbolTable();
    public abstract String printAst(int indentLevel);
    public abstract void addToSymbolTable();
    public abstract void generateCode(CodeFile codeFile);
}

import bytecode.*;

public abstract class Decl {
    public String name;
    public abstract String printAst(int indentLevel);
    public abstract void generateCode(CodeFile codeFile);
}

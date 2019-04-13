import bytecode.*;

public abstract class Decl {
    String name;
    public abstract String printAst(int indentLevel);
    public abstract void generateCode(CodeFile codeFile);
}

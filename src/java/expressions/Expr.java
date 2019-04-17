import bytecode.*;
import bytecode.type.*;
public abstract class Expr {
    Object expr;
    public abstract String printAst(int indentLevel);
    public abstract void generateCode(CodeFile codeFile);
}

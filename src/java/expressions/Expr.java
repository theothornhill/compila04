import bytecode.*;
import bytecode.type.*;
public abstract class Expr {
    Object expr;
    public abstract String printAst(int indentLevel);
    public abstract void typeCheck() throws Exception;
    public abstract void addToSymbolTable(SymbolTable table);
    public abstract void generateCode(CodeProcedure proc);
}

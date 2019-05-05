import bytecode.*;
import bytecode.type.*;
public abstract class Expr {
    public Object expr;
    public Type type;
    public abstract String printAst(int indentLevel);
    public abstract void typeCheck(SymbolTable table) throws Exception;
    public abstract void addToSymbolTable(SymbolTable table);
    public abstract void generateCode(CodeProcedure proc);
}

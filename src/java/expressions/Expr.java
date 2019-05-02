import bytecode.*;
import bytecode.type.*;
public abstract class Expr {
    Object expr;
    public abstract String printAst(int indentLevel);
    public abstract void addToSymbolTable(SymbolTable table, int scope);
    public abstract void generateCode(CodeProcedure proc);
}

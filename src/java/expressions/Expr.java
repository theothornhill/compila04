import bytecode.*;
import bytecode.type.*;
public abstract class Expr {
    Object expr;
    SymbolTable table = new SymbolTable();
    public abstract String printAst(int indentLevel);
    public abstract void addToSymbolTable();
    public abstract void generateCode(CodeProcedure proc);

}

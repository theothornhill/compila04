import bytecode.*;
import bytecode.type.*;
public abstract class Expr {
    public Object expr;
    public Type type;
    public int lexicalScopeLevel = 6;
    public SymbolTable table = new SymbolTable();
    public abstract String printAst(int indentLevel);
    public abstract void typeCheck(SymbolTable table, Object scope) throws Exception;
    public abstract void generateCode(CodeProcedure proc, SymbolTable table, Object object) throws Exception;
}

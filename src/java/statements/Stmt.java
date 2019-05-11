import java.util.*;
import bytecode.*;
import bytecode.type.*;

public abstract class Stmt implements AttributeGrammar {
    String name;
    Object e;
    Object target;
    LinkedList<Stmt> sl;
    public Type type;
    public Object createdBy;
    public abstract String printAst(int indentLevel);
    public abstract void typeCheck(SymbolTable table, Object scope) throws Exception;
    public abstract void generateCode(CodeFile codeFile, SymbolTable table, Object scope);
    public abstract void generateCode(CodeProcedure proc, SymbolTable table, Object scope);
}

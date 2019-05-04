import java.util.*;
import bytecode.*;
import bytecode.type.*;

public abstract class Stmt implements AttributeGrammar {
    String name;
    Object e;
    Object e2;
    LinkedList<Stmt> sl;
    public Object createdBy;
    public int lexicalScopeLevel;
    public abstract String printAst(int indentLevel);
    public abstract void typeCheck() throws Exception;
    public abstract void addToSymbolTable(SymbolTable table);
    public abstract void generateCode(CodeFile codeFile);
    public abstract void generateCode(CodeProcedure proc);

}

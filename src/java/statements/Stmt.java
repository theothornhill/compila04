import java.util.*;
import bytecode.*;
import bytecode.type.*;

public abstract class Stmt implements AttributeGrammar {
    String name;
    Object e;
    Object e2;
    LinkedList<Stmt> sl;
    public Type type;
    public Object createdBy;
    public int lexicalScopeLevel;
    public abstract String printAst(int indentLevel);
    public abstract void typeCheck(SymbolTable table, Object scope) throws Exception;
    public abstract void addToSymbolTable(SymbolTable table);
    public abstract void generateCode(CodeFile codeFile, SymbolTable table, Object scope);
    public abstract void generateCode(CodeProcedure proc, SymbolTable table, Object scope);

    public int getLexicalScopeLevel() {
        return this.lexicalScopeLevel;
    }

}

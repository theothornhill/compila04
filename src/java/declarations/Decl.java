import bytecode.*;

public abstract class Decl implements AttributeGrammar {
    public String name;
    public Object createdBy;
    public int lexicalScopeLevel;
    public abstract String printAst(int indentLevel);
    public abstract void typeCheck() throws Exception;
    public abstract void addToSymbolTable(SymbolTable table);
    public abstract void generateCode(CodeFile codeFile);

    public String toString() {
        return name;
    }
}

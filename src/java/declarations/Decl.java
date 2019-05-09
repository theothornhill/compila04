import bytecode.*;

public abstract class Decl implements AttributeGrammar {
    public String name;
    public Type type;
    public Object createdBy;
    public int lexicalScopeLevel;
    public SymbolTable table = new SymbolTable();
    public abstract String printAst(int indentLevel);
    public abstract void typeCheck() throws Exception;
    public abstract void addToSymbolTable() throws Exception;
    public abstract void generateCode(CodeFile codeFile);
    public abstract void generateCode(CodeProcedure proc);

    public SymbolTable getTable() {
        return this.table;
    }

    public int getLexicalScopeLevel() {
        return this.lexicalScopeLevel;
    }

    public String toString() {
        return name;
    }
}

import bytecode.*;

public abstract class Decl implements AttributeGrammar {
    public String name;
    public Type type;
    public Object createdBy;
    public SymbolTable table = new SymbolTable();
    public abstract String printAst(int indentLevel);
    public abstract void typeCheck() throws Exception;
    public abstract void addToSymbolTable() throws Exception;
    public abstract void generateCode(CodeFile codeFile) throws Exception;
    public abstract void generateCode(CodeProcedure proc) throws Exception;

    public SymbolTable getTable() {
        return this.table;
    }

    public String toString() {
        return name;
    }
}

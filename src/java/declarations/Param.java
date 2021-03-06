import bytecode.*;
import bytecode.type.*;
import bytecode.instructions.*;

public class Param extends Expr implements AttributeGrammar {
    public Type type;
    public String name;
    public Object createdBy;
    public int lexicalScopeLevel;
    CodeType t;

    public Param(String name, Type type) {
        this.name = name;
        this.type = type;
        this.t = type.setCodeType(this.type.toString());
    }

    public void typeCheck() throws Exception {
        // if (table.lookup(this.getCreatedBy(), this.name, lexicalScopeLevel) != null)
        //     throw new Exception("Symbol " + this.name + " already declared");
    }

    public void typeCheck(SymbolTable table, Object scope) throws Exception {
        
    }

    public int getLexicalScopeLevel() {
        return lexicalScopeLevel;
    }

    public Object getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Object node) {
        this.createdBy = node;
    }

    public void setCreatorOf() {
        type.setCreatedBy(this);
    }

    public void setLexicalScopeLevel(int scope) {
        this.lexicalScopeLevel = scope;
    }

    public void addToSymbolTable() {
        // Think through why its Decl or Expr
    }

    public void addToSymbolTable(SymbolTable table) throws Exception {
        try {
            table.insert(name);
            table.insert(type);                
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void generateCode(CodeStruct struct) {
        struct.addVariable(name, t);
    }

    public void generateCode(CodeFile codeFile) {
        
    }

    public void generateCode(CodeProcedure proc, SymbolTable table, Object scope) {
        proc.addParameter(name, t);
        // proc.addInstruction(new LOADLOCAL(proc.variableNumber(name)));
    }

    public String printAst(int indentLevel) {
        return "(PARAM_DECL "
            + type.printAst(indentLevel)
            + PrintHelper.printName(name)
            + ")";
    }

    public String toString() {
        return name;
    }

}

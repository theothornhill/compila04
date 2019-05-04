import bytecode.*;
import bytecode.type.*;
import bytecode.instructions.*;

public class Param implements AttributeGrammar {
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

    public Object createdBy() {
        return createdBy;
    }

    public void setCreatedBy(Object node) {
        this.createdBy = node;
    }

    public void setCreatorOf() {
        
    }

    public void setLexicalScopeLevel(int scope) {
        this.lexicalScopeLevel = scope;
    }

    public void addToSymbolTable(SymbolTable table, int scope) {
        table.insert(name);
        table.insert(type);
    }
    
    public void generateCode(CodeStruct struct) {
        struct.addVariable(name, t);
    }

    public void generateCode(CodeProcedure proc) {
        proc.addParameter(name, t);
        proc.addInstruction(new LOADLOCAL(proc.variableNumber(name)));
    }

    public String printAst(int indentLevel) {
        return "(PARAM_DECL "
            + type.printAst(indentLevel)
            + PrintHelper.printName(name)
            + ")";
    }
}

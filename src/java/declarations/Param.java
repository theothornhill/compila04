import bytecode.*;
import bytecode.type.*;
import bytecode.instructions.*;

public class Param {
    Type type;
    String name;
    CodeType t;
    SymbolTable table = new SymbolTable();

    public Param(String name, Type type) {
        this.name = name;
        this.type = type;
        this.t = type.setCodeType(this.type.toString());
    }

    public void addToSymbolTable() {
        table.insert("Name", name);
        table.insert("Type", type);
    }
    
    public void generateCode(CodeStruct struct) {
        struct.addVariable(name, t);
    }

    public void generateCode(CodeProcedure proc) {
        proc.addParameter(name, t);
        proc.addInstruction(new LOADLOCAL(proc.variableNumber(name)));
    }

    public SymbolTable getInfo() {
        return table;
    }

    public String printAst(int indentLevel) {
        return "(PARAM_DECL "
            + type.printAst(indentLevel)
            + PrintHelper.printName(name)
            + ")";
    }
}

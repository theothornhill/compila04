import bytecode.*;
import bytecode.type.*;
import bytecode.instructions.*;

public class Param {
    Type type;
    String name;
    CodeType t;

    public Param(String name, Type type) {
        this.name = name;
        this.type = type;
        this.t = type.setCodeType(this.type.toString());
    }

    public void addToSymbolTable(SymbolTable table, int scope) {
        table.insert(scope, name);
        table.insert(scope, type);
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

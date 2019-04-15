import bytecode.*;
import bytecode.type.*;

public class Param {
    Type type;
    String name;
    CodeType t;

    public Param(String name, Type type) {
        this.name = name;
        this.type = type;
        this.t = type.setCodeType(this.type.toString());
    }

    public void generateCode(CodeStruct struct) {
        struct.addVariable(name, t);
    }

    // TODO: Maybe not necessary to overload this?
    public void generateCode(CodeProcedure proc) {
        proc.addParameter(name, t);
    }

    public String printAst(int indentLevel) {
        return "(PARAM_DECL "
            + type.printAst(indentLevel)
            + PrintHelper.printName(name)
            + ")";
    }
}

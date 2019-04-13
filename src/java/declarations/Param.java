import bytecode.*;
import bytecode.type.*;

public class Param {
    Type type;
    String name;
    CodeType t;

    public Param(String name, Type type) {
        this.name = name;
        this.type = type;
        this.t = FloatType.TYPE; // TODO: This is hardcoded - fix this!
    }

    public void generateCode(CodeStruct struct) {
        struct.addVariable(name, t);
    }

    public String printAst(int indentLevel) {
        return "(PARAM_DECL "
            + type.printAst(indentLevel)
            + PrintHelper.printName(name)
            + ")";
    }
}

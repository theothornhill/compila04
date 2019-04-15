import bytecode.*;
import bytecode.type.*;

public class Param {
    Type type;
    String name;
    CodeType t;

    public Param(String name, Type type) {
        this.name = name;
        this.type = type;
        this.t = setCodeType(this.type);
    }

    public CodeType setCodeType(Type type) {
        if (type.toString().equals("float")) {
            return FloatType.TYPE;
        } else if (type.toString().equals("int")) {
            return IntType.TYPE;
        } else if (type.toString().equals("void")) {
            return VoidType.TYPE;            
        } else if (type.toString().equals("bool")) {
            return BoolType.TYPE;
        } else if (type.toString().equals("string")) {
            return StringType.TYPE;
        }
        return VoidType.TYPE; // TODO: not good, handle correctly

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

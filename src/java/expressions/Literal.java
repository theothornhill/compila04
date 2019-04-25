import bytecode.*;
import bytecode.type.*;

public class Literal {
    public String type;
    public String value;
    CodeType codeType;

    public Literal(String type, String value) {
        this.type = type;
        this.value = value;
        this.codeType = getLiteralType();
    }


    public void generateCode(CodeProcedure proc) {

    }

    public CodeType getLiteralType() {
        return this.type.equals("INT_LITERAL")
            ? IntType.TYPE
            : this.type.equals("STRING_LITERAL")
            ? StringType.TYPE
            : this.type.equals("FLOAT_LITERAL")
            ? FloatType.TYPE
            : this.type.equals("BOOL_LITERAL")
            ? BoolType.TYPE
            : VoidType.TYPE;    // TODO: Literals can never be void. Fix
    }

    public CodeType getCodeType() {
        return this.codeType;
    }

    public Object getValue() {
        return this.value;
    }

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(" + type + " ");
        sb.append(value + ")");
        return sb.toString();
    }
}

import bytecode.*;
import bytecode.type.*;

public class Literal {
    String type;
    String value;

    public Literal(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public void generateCode(CodeFile codeFile) {
        
    }

    public CodeType getLiteralType(String type) {
        return type.equals("INT_LITERAL")
            ? IntType.TYPE
            : type.equals("STRING_LITERAL")
            ? StringType.TYPE
            : type.equals("FLOAT_LITERAL")
            ? FloatType.TYPE
            : type.equals("BOOL_LITERAL")
            ? BoolType.TYPE
            : VoidType.TYPE;
    }

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(" + type + " ");
        sb.append(value + ")");
        return sb.toString();
    }
}

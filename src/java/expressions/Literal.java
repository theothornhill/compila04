import bytecode.*;
import bytecode.type.*;

public class Literal {
    public String type;
    public String value;
    CodeType codeType;

    public Literal(String type, String value) {
        this.type = type;
        this.value = value;
        this.codeType = CodeGenerationHelper.getLiteralType(this.type);
    }

    public void generateCode(CodeProcedure proc) {

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

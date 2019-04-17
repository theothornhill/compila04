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

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(" + type + " ");
        sb.append(value + ")");
        return sb.toString();
    }
}

import bytecode.*;
import bytecode.type.*;
import bytecode.instructions.*;

public class Literal extends Expr {
    public String t;
    public String value;
    CodeType codeType;

    public Literal(String type, String value) {
        this.t = type;
        this.value = value;
        this.type = new Type(type);
        this.codeType = CodeGenerationHelper.getLiteralType(this.t);
    }

    public void typeCheck() throws Exception {
        
    }

    public void typeCheck(SymbolTable table, Object scope) throws Exception {
        
    }

    public void generateCode(CodeProcedure proc, SymbolTable table, Object scope) {

    }

    public String getType() {
        return this.t;
    }

    public CodeType getCodeType() {
        return this.codeType;
    }

    public Object getValue() {
        return this.value;
    }

    public String toString() {
        return value;
    }

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(" + type + " ");
        sb.append(value + ")");
        return sb.toString();
    }
}

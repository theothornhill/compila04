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

    public void typeCheck(SymbolTable table) throws Exception {
        
    }

    public void addToSymbolTable(SymbolTable table) {
        table.insert(type);
        table.insert(value);
    }

    public void generateCode(CodeProcedure proc) {
        // TODO: Do we need to generate code for the literals? Or can we just
        // return them directly?
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

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(" + type + " ");
        sb.append(value + ")");
        return sb.toString();
    }
}

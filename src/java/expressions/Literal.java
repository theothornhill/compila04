import bytecode.*;
import bytecode.type.*;
import bytecode.instructions.*;

public class Literal {
    public String type;
    public String value;
    CodeType codeType;
    SymbolTable table = new SymbolTable();

    public Literal(String type, String value) {
        this.type = type;
        this.value = value;
        this.codeType = CodeGenerationHelper.getLiteralType(this.type);
    }

    public SymbolTable getTable() {
        return table;
    }

    public void addToSymbolTable(SymbolTable table) {
        table.insert("Type", type);
        table.insert("Value", value);
    }

    public void generateCode(CodeProcedure proc) {
        // TODO: Do we need to generate code for the literals? Or can we just
        // return them directly?
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

    public String toString() {
        return table.toString();
    }
}

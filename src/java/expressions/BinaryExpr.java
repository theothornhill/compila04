import bytecode.*;
import bytecode.type.*;
import bytecode.instructions.*;

public class BinaryExpr extends Expr {
    Object e1;
    Object e2;
    Object op;
    Instruction operator;

    public BinaryExpr(Object e1, Object op, Object e2) {
        this.e1 = e1;
        this.op = op;
        this.e2 = e2;
    }

    public void generateCode(CodeFile codeFile) {
        
    }

    // TODO: Add more operators here
    public Instruction getInstruction() {
        return op.toString().equals("+")
            ? new ADD()
            : op.toString().equals("-")
            ? new SUB()
            : op.toString().equals("/")
            ? new DIV()
            // : op.toString().equals("*")
            : new MUL();

    }
        

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(BINARY_OPERATION " + op.toString());
        sb.append(PrintHelper.newlineAndIndentWithHelper(e1, indentLevel+1));
        sb.append(PrintHelper.newlineAndIndentWithHelper(e2, indentLevel+1));
        sb.append(PrintHelper.endWithParen(indentLevel));
        return sb.toString();
    }
}

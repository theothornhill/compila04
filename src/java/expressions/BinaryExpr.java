import bytecode.*;
import bytecode.type.*;

public class BinaryExpr extends Expr {
    Object e1;
    Object e2;
    Object op;
    public BinaryExpr(Object e1, Object op, Object e2) {
        this.e1 = e1;
        this.op = op;
        this.e2 = e2;
    }

    public void generateCode(CodeFile codeFile) {
        
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

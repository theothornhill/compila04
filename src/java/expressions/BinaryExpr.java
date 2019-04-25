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

    public void generateCode(CodeProcedure proc) {
        proc.addInstruction(new ADD());
        CodeGenerationHelper.exprHelper(proc, e1);
        CodeGenerationHelper.exprHelper(proc, e2);
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

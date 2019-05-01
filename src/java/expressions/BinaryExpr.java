import bytecode.*;
import bytecode.type.*;
import bytecode.instructions.*;

public class BinaryExpr extends Expr {
    Object e1;
    Object e2;
    Object op;
    Instruction operator;
    // Booleans for type-checker. In example we need our type checker to know
    // when expressions are boolean
    public boolean isLogical;
    public boolean isArit;
    public boolean isRelational;

    public BinaryExpr(Object e1, Object op, Object e2) {
        this.e1 = e1;
        this.op = op;
        this.e2 = e2;
        setOperationType(op.toString());
    }

    // This is not proper type checking. Think through this.
    public void setOperationType(String op) {
        if (op.equals("||") || op.equals("&&"))
            isLogical = true;
        else if (op.equals("<")
                 || op.equals("<=")
                 || op.equals(">")
                 || op.equals(">=")
                 || op.equals("==")
                 || op.equals("<>"))
            isRelational = true;
        else
            isArit = true;
    }

    public void addToSymbolTable() {
        table.insert("Name", this.getClass());
        table.insert("Operation", op);
        table.insert("Expr1", CodeGenerationHelper.getTable(e1));
        table.insert("Expr2", CodeGenerationHelper.getTable(e2));
    }

    public void generateCode(CodeProcedure proc) {
        CodeGenerationHelper.exprHelper(proc, e1);
        proc.addInstruction(CodeGenerationHelper.instructionHelper(proc, op));
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

public class BinaryExpr extends Expr {
    Object e1;
    Object e2;
    Object op;
    public BinaryExpr(Object e1, Object op, Object e2) {
        super(op);
        this.e1 = e1;
        this.op = op;
        this.e2 = e2;
    }

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(BINARY_OPERATION " + op.toString() + "\n");
        sb.append(Main.buildIndentation(indentLevel+1) + Main.astHelper(this.e1, indentLevel+1));
        sb.append(")\n");
        sb.append(Main.buildIndentation(indentLevel+1) + Main.astHelper(this.e2, indentLevel+1));
        sb.append("\n" + Main.buildIndentation(indentLevel));
        return sb.toString();
    }
}

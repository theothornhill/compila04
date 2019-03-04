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

    private String astHelper(Object node, int indentLevel) {
        return node instanceof Expr
            ? ((Expr) node).printAst(indentLevel)
            : node instanceof Call
            ? ((Call) node).printAst(indentLevel)
            : node.toString();
    }

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append(astHelper(this.e1, indentLevel));
        sb.append(op.toString());
        sb.append(astHelper(this.e2, indentLevel));
        return sb.toString();
    }
}

public class AritExpr extends Expr {
    Object e1;
    Object e2;
    Object op;
    public AritExpr(Object e1, Object e2, Object aritOp) {
        super(aritOp);
        this.e1 = e1;
        this.e2 = e2;
        this.op = aritOp;
    }

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append(e1.toString());
        sb.append(op.toString());
        sb.append(e2.toString());
        return sb.toString();
    }
}

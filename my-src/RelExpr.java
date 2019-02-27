public class RelExpr extends Expr {
    public RelExpr(Expr e1, Expr e2, String relOp) {
        super(relOp);
        this.e1 = e1;
        this.e2 = e2;
    }

    public String toString() {
        return e1.toString() + this.op + e2.toString();
    }
}

public class AritExpr extends Expr {
    public AritExpr(Expr e1, Expr e2, String aritOp) {
        super(aritOp);
        this.e1 = e1;
        this.e2 = e2;
    }
}

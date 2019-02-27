public class LogicExpr extends Expr {
    public LogicExpr(Expr e1, Expr e2, String logOp) {
        super(logOp);
        this.op = logOp;
        this.e1 = e1;
        this.e2 = e2;
    }
}

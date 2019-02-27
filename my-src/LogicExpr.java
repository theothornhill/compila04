public class LogicExpr extends Expr {
    public LogicExpr(Expr e1, Expr e2, String logOp) {
        this.op = logOp;
    }
}

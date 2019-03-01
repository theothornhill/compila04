public class LogicExpr extends Expr {
    Object e1;
    Object e2;
    Object op;
    public LogicExpr(Object e1, Object e2, Object logOp) {
        super(logOp);
        this.e1 = e1;
        this.e2 = e2;
        this.op = logOp;
    }

    public String printAst(int indentLevel) {
        return "";
    }
}

public class LogicExpr extends Expr {
    Expr e1;
    Expr e2;
    String op;
    public LogicExpr(Expr e1, Expr e2, String logOp) {
        super(logOp);
        this.e1 = e1;
        this.e2 = e2;
        this.op = logOp;
    }

    public String printAst(int indentLevel) {
        return "";
    }
}

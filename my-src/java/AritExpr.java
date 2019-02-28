public class AritExpr extends Expr {
    Expr e1;
    Expr e2;
    String op;
    public AritExpr(Expr e1, Expr e2, String aritOp) {
        super(aritOp);
        this.e1 = e1;
        this.e2 = e2;
        this.op = aritOp;
    }

    public String printAst(int indentLevel) {
        return "";
    }
}

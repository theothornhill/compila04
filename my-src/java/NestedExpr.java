public class NestedExpr extends Expr {
    public NestedExpr(Object expr) {
        super(expr);
    }

    public String printAst(int indentLevel) {
        return ""+ Main.astHelper(this.expr, indentLevel) +"";
    }
}

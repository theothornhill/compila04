public class NestedExpr extends Expr {
    public NestedExpr(Object expr) {
        this.expr = expr;
    }

    public String printAst(int indentLevel) {
        return ""+ PrintHelper.astHelper(this.expr, indentLevel) +"";
    }
}

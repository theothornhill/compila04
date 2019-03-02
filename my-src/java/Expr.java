public class Expr {
    Object expr;
    public Expr(Object expr) {
        this.expr = expr;
    }
    
    public String printAst(int indentLevel) {
        if (expr instanceof Expr) {
            Expr e = (Expr) expr;
            return e.printAst(indentLevel);
        }
        return expr.toString();
    }
}

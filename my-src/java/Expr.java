public class Expr {
    Object expr;
    public Expr(Object expr) {
        this.expr = expr;
    }

    public Expr() {
        
    }
    
    public String printAst(int indentLevel) {
        return expr instanceof Expr
            ? ((Expr) expr).printAst(indentLevel)
            : expr.toString();
    }
}

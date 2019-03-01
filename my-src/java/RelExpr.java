public class RelExpr extends Expr {
    Object e1;
    Object e2;
    Object op;
    public RelExpr(Object e1, Object e2, Object relOp) {
        super(relOp);
        this.e1 = e1;
        this.e2 = e2;
        this.op = relOp;
    }
    
    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("Relexpression!");
        return sb.toString();
    }
}
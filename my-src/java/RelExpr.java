public class RelExpr extends Expr {
    Expr e1;
    Expr e2;
    String op;
    public RelExpr(Expr e1, Expr e2, String relOp) {
        super(relOp);
        this.e1 = e1;
        this.e2 = e2;
        this.op = relOp;
    }

    public String toString() {
        return this.op;
    }

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("?" + this.e1.printAst(indentLevel));
        sb.append("!" + super.printAst(indentLevel));
        sb.append(this.e2.printAst(indentLevel));
        return sb.toString();
    }
}

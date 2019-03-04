public class BinaryExpr extends Expr {
    Object e1;
    Object e2;
    Object op;
    public BinaryExpr(Object e1, Object op, Object e2) {
        super(op);
        this.e1 = e1;
        this.op = op;
        this.e2 = e2;
    }

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        if (e1 instanceof Expr) {
            Expr e1 = (Expr) this.e1;
            sb.append(e1.printAst(indentLevel));
        } else if (e1 instanceof Stmt) {
            Stmt e1 = (Stmt) this.e1;
            sb.append(e1.printAst(indentLevel));
        } else {
            sb.append(e1.toString());
        }
        sb.append(op.toString());
        if (e2 instanceof Expr) {
            Expr e2 = (Expr) this.e2;
            sb.append(e2.printAst(indentLevel));
        } else if (e2 instanceof Stmt) {
            Stmt e2 = (Stmt) this.e2;
            sb.append(e2.printAst(indentLevel));
        } else {
            sb.append(e2.toString());
        }
        return sb.toString();
    }
}

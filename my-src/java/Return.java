public class Return extends Stmt {
    public Return(Object expr) {
        this.e = expr;
    }

    public Return() {
        
    }

    public String printAst(int indentLevel) {
        Expr e = (Expr) this.e;
        return "(RETURN " + e.printAst(indentLevel) + ")";
    }

}

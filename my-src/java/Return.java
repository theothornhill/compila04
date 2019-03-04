public class Return extends Stmt {
    public Return(Object expr) {
        this.e = expr;
    }

    public Return() {
        
    }

    public String printAst(int indentLevel) {
        return "(RETURN " + Main.astHelper(this.e, indentLevel) + ")";
    }

}

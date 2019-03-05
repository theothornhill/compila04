public class Return extends Stmt {
    public Return(Object expr) {
        this.e = expr;
    }

    public Return() {
        
    }

    public String printAst(int indentLevel) {
        if (this.e == null) {
            return "(RETURN)";
        }
        return "(RETURN " + Main.astHelper(this.e, indentLevel+1) + ")";
    }

}

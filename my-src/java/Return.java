public class Return extends Stmt {
    public Return(Object expr) {
        this.e = expr;
    }

    public Return() {
        
    }

    public String printAst(int indentLevel) {
        if (this.e == null) {
            return "(RETURN_STMT)";
        }
        return "(RETURN_STMT " + Main.astHelper(this.e, indentLevel) + ")";
    }

}

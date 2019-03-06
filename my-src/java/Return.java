public class Return extends Stmt {
    public Return(Object expr) {
        this.e = expr;
    }

    public Return() {
        
    }

    public String printAst(int indentLevel) {
        if (this.e == null) {
            return "(RETURN)";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("(RETURN " + Main.astHelper(this.e, indentLevel+1));
            sb.append("\n" + Main.buildIndentation(indentLevel) + ")");
            return sb.toString();
        }
    }

}

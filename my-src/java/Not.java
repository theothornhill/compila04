public class Not extends Expr {
    public Not(Object expr) {
        super(expr);
    }

    public String printAst(int indentLevel) {
        return "(NOT " + Main.astHelper(expr, indentLevel+2);
    }
}

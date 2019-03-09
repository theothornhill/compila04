public class Not extends Expr {
    public Not(Object expr) {
        super(expr);
    }

    public String printAst(int indentLevel) {
        return "(NOT " + PrintHelper.astHelper(expr, indentLevel+1)
            + PrintHelper.endWithParen(indentLevel)
            ;
    }
}

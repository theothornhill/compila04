public class PrintHelper {
    public static String buildIndentation(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indentLevel; i++) {
            sb.append("\t");
        }
        return sb.toString();
    }

    public static String astHelper(Object node, int indentLevel) {
        return node instanceof Expr
            ? ((Expr) node).printAst(indentLevel)
            : node instanceof Call
            ? ((Call) node).printAst(indentLevel)
            : node instanceof Literal
            ? ((Literal) node).printAst(indentLevel)
            : node.toString();
    }

    public static String newlineAndIndentWithHelper(Object node, int indentLevel) {
        return "\n" + PrintHelper.buildIndentation(indentLevel+1) + PrintHelper.astHelper(node, indentLevel+1);
    }
}

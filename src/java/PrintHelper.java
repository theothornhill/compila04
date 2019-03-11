public class PrintHelper {
    public static String buildIndentation(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indentLevel; i++) {
            sb.append("  "); // Adds two spaces to indentation
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
        return "\n" + buildIndentation(indentLevel) + astHelper(node, indentLevel);
    }

    public static String printName(String name) {
        return " (NAME " + name + ")";
    }
    
    public static String printDecl(Decl decl, int indentLevel) {
        return "\n" + buildIndentation(indentLevel) + decl.printAst(indentLevel);
    }

    public static String printStmt(Stmt stmt, int indentLevel) {
        return "\n" + buildIndentation(indentLevel) + stmt.printAst(indentLevel);
    }

    public static String printParam(Param param, int indentLevel) {
        return "\n" + buildIndentation(indentLevel) + param.printAst(indentLevel);
    }

    public static String endWithParen(int indentLevel) {
        return "\n" + buildIndentation(indentLevel) + ")";        
    }

}

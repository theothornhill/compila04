public class New extends Expr {
    String name;
    public New(String name) {
        this.name = name;
    }

    public String printAst(int indentLevel) {
        return "(NEW " + PrintHelper.astHelper(name, indentLevel) + ")";
    }
}

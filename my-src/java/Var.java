public class Var extends Expr {
    String name;
    public Var(Object expr, String name) {
        super(expr);
        this.name = name;
    }
    public Var(String name) {
        super();
        this.name = name;
    }

    public String printAst(int indentLevel) {
        if (this.expr == null) {
            return "(VAR " + PrintHelper.printName(name);
        }
        return "(VAR " + PrintHelper.astHelper(expr, indentLevel)
            + PrintHelper.printName(name);
    }
}

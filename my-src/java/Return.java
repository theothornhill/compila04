public class Return extends Stmt {
    public Return(String name) {
        this.name = name;
    }

    public String printAst(int indentLevel) {
        return "(RETURN " + name + ")";
    }
}

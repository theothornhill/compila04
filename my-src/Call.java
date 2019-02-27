public class Call extends Stmt {
    public Call(String name) {
        this.name = name;
    }

    public String printAst(int indentLevel) {
        return Main.buildIndentation(indentLevel) + "(CALL " + name + ")";
    }
}

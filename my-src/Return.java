public class Return extends Stmt {
    public Return(String name) {
        this.name = name;
    }

    public String printAst() {
        return "(RETURN " + name + ")";
    }
}

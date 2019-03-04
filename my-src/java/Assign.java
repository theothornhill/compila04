public class Assign extends Stmt {
    public Assign(String name, Object e) {
        this.name = name;
        this.e = e;
    }

    public String printAst(int indentLevel) {
        return "(ASSIGN " + this.name + " "
            + Main.astHelper(this.e, indentLevel) + ")";
    }
}

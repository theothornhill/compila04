public class Assign extends Stmt {
    public Assign(String name) {
        this.name = name;
    }

    public String printAst() {
        return "(ASSIGN " + name + ")";
    }
}

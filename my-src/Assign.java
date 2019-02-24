public class Assign extends Stmt {
    public Assign(String name) {
        this.name = name;
    }

    public String toString() {
        return "(ASSIGN " + name + ")";
    }
}

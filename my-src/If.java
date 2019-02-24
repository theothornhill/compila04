public class If extends Stmt {
    public If(String name) {
        this.name = name;
    }

    public String toString() {
        return "(IF " + name + ")";
    }
}

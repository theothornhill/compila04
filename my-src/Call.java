public class Call extends Stmt {
    public Call(String name) {
        this.name = name;
    }

    public String toString() {
        return "(CALL " + name + ")";
    }
}
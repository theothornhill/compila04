public class Expr {
    String name;
    public Expr(String name) {
        this.name = name;
    }

    public String toString() {
        return "(EXPR " + name + ")";
    }
}

public class Expr {
    String name;
    Expr e1;
    Expr e2;
    String op;
    public Expr(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}

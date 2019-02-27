public class Expr {
    Expr e1;
    Expr e2;
    String op;
    public Expr(String op) {
        this.op = op;
    }

    public String toString() {
        return this.op;
    }
}

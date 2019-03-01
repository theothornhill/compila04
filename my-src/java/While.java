import java.util.*;

public class While extends Stmt {
    Object e;
    LinkedList<Stmt> el;
    public While(Object e, LinkedList<Stmt> el) {
        this.e = e;
        this.el = el;
    }

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(WHILE ");
        Expr ex = (Expr) this.e;
        sb.append(ex.printAst(indentLevel));
        for (Object e : el) {
            if (e instanceof Expr) {
                Expr expr = (Expr) e;
                sb.append("\n" + Main.buildIndentation(indentLevel+1) +  expr.printAst(indentLevel+1));
            } else if (e instanceof Stmt) {
                Stmt stmt = (Stmt) e;
                sb.append("\n" + Main.buildIndentation(indentLevel+1) + stmt.printAst(indentLevel+1));
            } else {
                sb.append("\n" + Main.buildIndentation(indentLevel+1) + "(" + e);
            }
        }
        sb.append("\n" + Main.buildIndentation(indentLevel) + ")");
        return sb.toString();
    }
}

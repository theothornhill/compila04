import java.util.*;

public class While extends Stmt {
    Expr e;
    LinkedList<Stmt> el;
    public While(Expr e, LinkedList<Stmt> el) {
        this.e = e;
        this.el = el;
    }

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(WHILE ");
        sb.append(this.e);
        sb.append("\n");
        for (Stmt stmt : el) {
            sb.append(Main.buildIndentation(indentLevel+1) + stmt.printAst(indentLevel+1));
            sb.append("\n");                
        }
        sb.append(Main.buildIndentation(indentLevel) + ")");
        return sb.toString();
    }
}

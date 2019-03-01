import java.util.*;

public class Call extends Stmt {
    LinkedList<Object> el;
    public Call(String name, LinkedList<Object> el) {
        this.name = name;
        this.el = el;
    }

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(CALL " + name);
        if (el != null) {
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
            sb.append(")");
        }
        sb.append("\n" + Main.buildIndentation(indentLevel) + ")");
        return sb.toString();
    }
}

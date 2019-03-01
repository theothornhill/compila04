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
                sb.append("\n" + Main.buildIndentation(indentLevel+1) + "(" + e);
                sb.append("\n");                
            }            
        }
        sb.append("\n" + Main.buildIndentation(indentLevel) + ")");
        return sb.toString();
    }
}

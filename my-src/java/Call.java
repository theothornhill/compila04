import java.util.*;

public class Call extends Stmt {
    LinkedList<Object> el;
    public Call(String name, LinkedList<Object> el) {
        this.name = name;
        this.el = el;
    }

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(CALL_STMT (NAME " + name + ")");
        if (el != null) {
            int i = 1;
            for (Object e : el) {
                sb.append("\n" + Main.buildIndentation(indentLevel+i)
                          + Main.astHelper(e, indentLevel+i));
                i++;
            }            
        }
        sb.append(")");
        return sb.toString();
    }
}

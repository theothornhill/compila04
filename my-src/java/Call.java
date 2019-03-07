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
            for (Object e : el) {
                sb.append("\n" + PrintHelper.buildIndentation(indentLevel+1)
                          + PrintHelper.astHelper(e, indentLevel+1));
            }
            sb.append("\n" + PrintHelper.buildIndentation(indentLevel) + ")");
        } else {
            sb.append(")");            
        }

        return sb.toString();
    }
}

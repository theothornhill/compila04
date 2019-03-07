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
        sb.append(PrintHelper.astHelper(this.e, indentLevel+1));
        for (Stmt stmt : el) {
            sb.append("\n" + PrintHelper.buildIndentation(indentLevel+1) + stmt.printAst(indentLevel+1));
        }
        sb.append("\n" + PrintHelper.buildIndentation(indentLevel) + ")");
        return sb.toString();
    }
}

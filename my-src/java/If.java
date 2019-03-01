import java.util.*;
public class If extends Stmt {
    LinkedList<Stmt> sl2;
    public If(Object e, LinkedList<Stmt> sl) {
        this.e = e;
        this.sl = sl;
    }

    public If(Object e, LinkedList<Stmt> sl, LinkedList<Stmt> sl2) {
        this.e = e;
        this.sl = sl;
        this.sl2 = sl2;
    }

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(IF ");
        sb.append("expr here");
        sb.append("\n");
        for (Stmt stmt : sl) {
            sb.append(Main.buildIndentation(indentLevel+1) + stmt.printAst(indentLevel+1));
            sb.append("\n");                
        }
        if (sl2 != null) {
            sb.append(Main.buildIndentation(indentLevel) + "(ELSE \n");
            for (Stmt stmt : sl2) {
                sb.append(Main.buildIndentation(indentLevel+1) + stmt.printAst(indentLevel+1));
                sb.append("\n");                
            }   
        }
        sb.append(Main.buildIndentation(indentLevel) + ")");
        return sb.toString();
    }
}

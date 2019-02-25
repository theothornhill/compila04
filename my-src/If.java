import java.util.*;
public class If extends Stmt {
    LinkedList<Stmt> sl2;
    public If(Expr e, LinkedList<Stmt> sl) {
        this.e = e;
        this.sl = sl;
    }

    public If(Expr e, LinkedList<Stmt> sl, LinkedList<Stmt> sl2) {
        this.e = e;
        this.sl = sl;
        this.sl2 = sl2;
    }

    public String printAst() {
        StringBuilder sb = new StringBuilder();
        sb.append("(IF ");
        sb.append(this.e);
        sb.append(")\n");
        for (Stmt stmt : sl) {
            sb.append("\t" + stmt.printAst());
            sb.append("\n");                
        }
        if (sl2 != null) {
            for (Stmt stmt : sl) {
                sb.append("\t" + stmt.printAst());
                sb.append("\n");                
            }   
        }
        return sb.toString();
    }
}

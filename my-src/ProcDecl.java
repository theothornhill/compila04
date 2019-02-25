import java.util.*;

public class ProcDecl extends Decl {
    LinkedList<Param> pl;
    LinkedList<Decl> dl;
    LinkedList<Stmt> sl;
    public ProcDecl(String name,
                    LinkedList<Param> pl,
                    LinkedList<Decl> dl,
                    LinkedList<Stmt> sl) {
        this.name = name;
        this.pl = pl;
        this.dl = dl;
        this.sl = sl;
    }

    public String printAst() {
        StringBuilder sb = new StringBuilder();
        sb.append("(PROCEDURE (NAME ");
        sb.append(this.name);
        sb.append(")\n");
        for (Param param : pl) {
            sb.append("\t" + param.printAst());
            sb.append("\n");                
        }
        for (Stmt stmt : sl) {
            sb.append("\t" + stmt.printAst());
            sb.append("\n");                
        }
        for (Decl decl : dl) {
            sb.append("\t" + decl.printAst());
            sb.append("\n");                
        }
        return sb.toString();
    }
}

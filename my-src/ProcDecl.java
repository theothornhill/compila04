import java.util.*;

public class ProcDecl extends Decl {
    LinkedList<Param> pl;
    LinkedList<Decl> dl;
    LinkedList<Stmt> sl;
    String type;
    
    public ProcDecl(String name,
                    LinkedList<Param> pl,
                    LinkedList<Decl> dl,
                    LinkedList<Stmt> sl) {
        this.name = name;
        this.pl = pl;
        this.dl = dl;
        this.sl = sl;
    }

    public ProcDecl(String name,
                    LinkedList<Param> pl,
                    String type,
                    LinkedList<Decl> dl,
                    LinkedList<Stmt> sl) {
        this.name = name;
        this.pl = pl;
        this.type = type;
        this.dl = dl;
        this.sl = sl;
    }

    public String printAst() {
        StringBuilder sb = new StringBuilder();
        sb.append("(PROCEDURE (NAME ");
        sb.append(this.name);
        sb.append(" (TYPE " + this.type + ")\n\t");
        for (Param param : pl) {
            sb.append("\t" + param.printAst());
            sb.append("\n\t");                
        }
        for (Decl decl : dl) {
            sb.append("\t" + decl.printAst());
            sb.append("\n");                
        }
        for (Stmt stmt : sl) {
            sb.append("\t" + stmt.printAst());
            sb.append("\n");                
        }
        sb.append("\t)\n");
        return sb.toString();
    }
}

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

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(PROCEDURE (NAME ");
        sb.append(this.name);
        sb.append(" (TYPE " + this.type + ")");
        for (Param param : pl) {
            sb.append("\n" + Main.buildIndentation(indentLevel+2) + param.printAst(indentLevel));
            // sb.append(" ");
        }
        sb.append("\n");
        for (Decl decl : dl) {
            sb.append(Main.buildIndentation(indentLevel+1) + decl.printAst(indentLevel+1));
            sb.append("\n");
        }
        for (Stmt stmt : sl) {
            sb.append(Main.buildIndentation(indentLevel+1) + stmt.printAst(indentLevel+1));
            sb.append("\n");
        }
        sb.append(Main.buildIndentation(indentLevel) + ")\n");
        return sb.toString();
    }
}

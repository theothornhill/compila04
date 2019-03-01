import java.util.*;

public class ProcDecl extends Decl {
    LinkedList<Param> pl;
    LinkedList<Decl> dl;
    LinkedList<Stmt> sl;
    Object type;
    
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
                    Object type,
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
            sb.append("\n" + Main.buildIndentation(indentLevel+1) + param.printAst(indentLevel));
         }
         for (Decl decl : dl) {
            sb.append("\n" + Main.buildIndentation(indentLevel) + decl.printAst(indentLevel+1));
         }
        for (Stmt stmt : sl) {
            sb.append("\n" + Main.buildIndentation(indentLevel) + stmt.printAst(indentLevel));
         }
        sb.append("\n" + Main.buildIndentation(indentLevel-1) + ")");
        return sb.toString();
    }
}

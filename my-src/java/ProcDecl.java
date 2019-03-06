import java.util.*;

public class ProcDecl extends Decl {
    LinkedList<Param> pl;
    LinkedList<Decl> dl;
    LinkedList<Stmt> sl;
    Type type;
    
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
                    Type type,
                    LinkedList<Decl> dl,
                    LinkedList<Stmt> sl) {
        this.name = name;
        this.pl = pl;
        this.type = type;
        this.dl = dl;
        this.sl = sl;
    }

    private String printType(int indentLevel) {
        return this.type == null ? "(TYPE void)" : this.type.printAst(indentLevel);
    }

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(PROC_DECL ");
        sb.append(printType(indentLevel));
        sb.append(" (NAME " + this.name + ")");
        if (pl != null) {
            for (Param param : pl) {
                sb.append("\n" + Main.buildIndentation(indentLevel+1) + param.printAst(indentLevel+1));
            }
        }
        sb.append("\n");
        if (dl != null) {
            for (Decl decl : dl) {
                sb.append("\n" + Main.buildIndentation(indentLevel+1) + decl.printAst(indentLevel+1));
            }            
        }
        sb.append("\n");
        if (sl != null) {
            for (Stmt stmt : sl) {
                sb.append("\n" + Main.buildIndentation(indentLevel+1) + stmt.printAst(indentLevel+1));
            }            
        }
        sb.append("\n" + Main.buildIndentation(indentLevel) + ")");
        return sb.toString();
    }
}

import java.util.*;

public class RecDecl extends Decl {
    LinkedList<Param> pl;
    public RecDecl(String name, LinkedList<Param> pl) {
        this.name = name;
        this.pl = pl;
    }

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(STRUCT (NAME ");
        sb.append(this.name);
        sb.append(")\n");
        if (pl != null) {
            for (Param param : pl) {
                sb.append(Main.buildIndentation(indentLevel) + param.printAst(indentLevel+1));
                sb.append("\n");                
            }            
        }
        sb.append(Main.buildIndentation(indentLevel-1) + ")");
        return sb.toString();
    }
}

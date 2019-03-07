import java.util.*;

public class Program {
    String name;
    List<Decl> declarations;
    public Program(String name, LinkedList<Decl> declarations) {
        this.name = name;
        this.declarations = declarations;
    }

    public String printAst() {
        StringBuilder sb = new StringBuilder();
        int indentLevel = 0;
        sb.append("(PROGRAM (NAME ");
        if (this.name != null) {
            sb.append(this.name);
            sb.append(")\n");            
        }
        if (declarations != null) {
            for (Decl decl : declarations) {
                if (decl != null) {
                    sb.append(PrintHelper.buildIndentation(indentLevel+1) + decl.printAst(indentLevel+1));
                    sb.append("\n");
                }
            }            
        }

        // sb.append(declarations);
        sb.append(PrintHelper.buildIndentation(indentLevel-1) + ")");
        return sb.toString();
    }
}

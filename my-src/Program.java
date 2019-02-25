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
        sb.append("(PROGRAM (NAME ");
        if (this.name != null) {
            sb.append(this.name);
            sb.append(")\n");            
        }
        // declarations.forEach(s -> {
        //         sb.append(s.toString());
        //             });
        if (declarations != null) {
            for (Decl decl : declarations) {
                if (decl != null) {
                    sb.append("\t" + decl.printAst());
                    sb.append("\n");                
                }
            }            
        }

        // sb.append(declarations);
        sb.append(")");
        return sb.toString();
    }
}

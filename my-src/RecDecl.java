import java.util.*;

public class RecDecl extends Decl {
    LinkedList<Param> pl;
    public RecDecl(String name, LinkedList<Param> pl) {
        this.name = name;
        this.pl = pl;
    }

    public String printAst() {
        StringBuilder sb = new StringBuilder();
        sb.append("(STRUCT (NAME ");
        sb.append(this.name);
        sb.append(")\n");
        for (Param param : pl) {
            sb.append("\t" + param.printAst());
            sb.append("\n");                
        }
        return sb.toString();
    }
}

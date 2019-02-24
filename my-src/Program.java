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
        sb.append(this.name);
        sb.append(")\n\t");
        sb.append(declarations);
        sb.append("\n)\n");
        return sb.toString();
    }
}

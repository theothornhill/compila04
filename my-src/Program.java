import java.util.*;

public class Program {
    String name;
    List<LinkedList> declarations;
    public Program(String name, LinkedList<LinkedList> declarations) {
        this.name = name;
        this.declarations = declarations;
    }

    public String printAst() {
        StringBuilder sb = new StringBuilder();
        sb.append("(PROGRAM ");
        sb.append("(NAME ");
        sb.append(this.name);
        sb.append(")\n");
        sb.append(declarations.toString());
        sb.append("\n)\n");
        return sb.toString();
    }
}

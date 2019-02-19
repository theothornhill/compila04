import java.util.*;

public class Program {
    String name;
    List<String> declarations;
    public Program(String name, LinkedList<String> declarations) {
        this.name = name;
        this.declarations = declarations;
    }

    public String printAst() {
        StringBuilder sb = new StringBuilder();
        sb.append("(PROGRAM ");
        sb.append("(NAME ");
        sb.append(this.name);
        sb.append(")\n");
        declarations.stream().forEach(s -> {
                sb.append("\t" + s);
                sb.append("\n");
            });
        sb.append(")\n");
        return sb.toString();
    }
}

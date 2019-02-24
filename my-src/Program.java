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
        sb.append("(PROGRAM (NAME ");
        sb.append(this.name);
        sb.append(")\n");
        sb.append(declarations);
        sb.append("\n)\n");
        return sb.toString();
    }
}

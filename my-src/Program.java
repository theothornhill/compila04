public class Program {
    String name;
    public Program(String name) {
        this.name = name;
    }

    public String printAst() {
        StringBuilder sb = new StringBuilder();
        sb.append("(PROGRAM ");
        sb.append("(NAME ");
        sb.append(this.name);
        sb.append(")\n");
        sb.append(")\n");
        return sb.toString();
    }
}

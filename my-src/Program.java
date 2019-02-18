public class Program {
    String name;
    String stmt;
    public Program(String name, String stmt) {
        this.name = name;
        this.stmt = stmt;
    }

    public String printAst() {
        StringBuilder sb = new StringBuilder();
        sb.append("(PROGRAM ");
        sb.append("(NAME ");
        sb.append(this.name);
        sb.append(")\n");
        sb.append("\t(" + stmt);
        sb.append(")\n");
        sb.append(")\n");
        return sb.toString();
    }
}

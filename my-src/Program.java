public class Program {
    String name;
    String stmt;
    String var;
    public Program(String name, String stmt, String var) {
        this.name = name;
        this.stmt = stmt;
        this.var = var;
    }

    public String printAst() {
        StringBuilder sb = new StringBuilder();
        sb.append("(PROGRAM ");
        sb.append("(NAME ");
        sb.append(this.name);
        sb.append(")\n");
        sb.append("\t(" + stmt);
        sb.append(")\n");
        sb.append("\t(" + var);
        sb.append(")\n");
        sb.append("\t)\n");
        sb.append(")\n");
        return sb.toString();
    }
}

public class VarDecl extends Decl {
    String type;
    public VarDecl(String name, String type) {
        this.name = name;
        this.type = type;
    }
    
    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(VAR (NAME ");
        sb.append(this.name);
        sb.append(")\n");
        sb.append(Main.buildIndentation(indentLevel) + "(TYPE ");
        sb.append(this.type);
        sb.append(")\n");
        sb.append(Main.buildIndentation(indentLevel-1) + ")");
        return sb.toString();
    }
}

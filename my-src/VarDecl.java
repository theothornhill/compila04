public class VarDecl extends Decl {
    String type;
    public VarDecl(String name, String type) {
        this.name = name;
        this.type = type;
    }
    
    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append(Main.buildIndentation(indentLevel) + "(VAR (NAME ");
        sb.append(this.name);
        sb.append(") ");
        sb.append("(TYPE ");
        sb.append(this.type);
        sb.append("))");
        return sb.toString();
    }
}

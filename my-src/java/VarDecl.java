public class VarDecl extends Decl {
    Object type;
    public VarDecl(String name, Object type) {
        this.name = name;
        this.type = type;
    }
    
    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(VAR ");
        sb.append("(TYPE ");
        sb.append(this.type);
        sb.append(") ");
        sb.append("(NAME ");
        sb.append(this.name);
        sb.append(")");
        sb.append(")");
        return sb.toString();
    }
}

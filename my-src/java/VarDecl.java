public class VarDecl extends Decl {
    Type type;
    public VarDecl(String name, Type type) {
        this.name = name;
        this.type = type;
    }
    
    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(VAR ");
        sb.append(type.printAst(indentLevel+1));
        sb.append(" (NAME ");
        sb.append(this.name);
        sb.append(")");
        sb.append(")");
        return sb.toString();
    }
}

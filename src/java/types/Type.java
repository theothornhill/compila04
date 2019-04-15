public class Type {
    String type;
    Type t;
    public Type(String type) {
        this.type = type;
    }

    public Type(Type t) {
        this.t = t;
    }

    public String toString() {
        return this.type;
    }

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(TYPE ");
        sb.append(type);
        sb.append(")");
        return sb.toString();
    }
}

public class RefType extends Type {
    public RefType(Type type) {
        super(type);
    }

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(REFTYPE ");
        sb.append(this.t.printAst(indentLevel));
        sb.append(")");
        return sb.toString();
    }

    public String toString() {
        return type;
    }

}

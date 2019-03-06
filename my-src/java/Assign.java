public class Assign extends Stmt {
    public Assign(String name, Object e) {
        this.name = name;
        this.e = e;
    }

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(ASSIGN_STMT \n" + Main.buildIndentation(indentLevel+1));
        sb.append("(NAME " + this.name + ")\n" + Main.buildIndentation(indentLevel+1));
        sb.append(Main.astHelper(this.e, indentLevel+1));
        sb.append("\n" + Main.buildIndentation(indentLevel) + ")");
        return sb.toString();
    }
}

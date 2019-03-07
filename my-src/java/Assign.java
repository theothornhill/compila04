public class Assign extends Stmt {
    public Assign(String name, Object e) {
        this.name = name;
        this.e = e;
    }

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(ASSIGN_STMT \n" + PrintHelper.buildIndentation(indentLevel+1));
        sb.append("(NAME " + this.name + ")\n" + PrintHelper.buildIndentation(indentLevel+1));
        sb.append(PrintHelper.astHelper(this.e, indentLevel+1));
        sb.append("\n" + PrintHelper.buildIndentation(indentLevel) + ")");
        return sb.toString();
    }
}

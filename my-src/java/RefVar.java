public class RefVar extends Var {
    public RefVar(Var variable) {
        super(variable.expr, variable.name);
    }

    public String printAst(int indentLevel) {
        if (this.expr == null) {
            return "(REFVAR " + this.name + ")";
        }
        return "(REFVAR (" + PrintHelper.astHelper(this.expr, indentLevel)
            + " (NAME " + this.name + "))";
    }
}

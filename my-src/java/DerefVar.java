
public class DerefVar extends Var {
    public DerefVar(Var variable) {
        super(variable.expr, variable.name);
    }

    public String printAst(int indentLevel) {
        if (this.expr == null) {
            return "(DEREFVAR " + this.name;
        }
        return "(DEREFVAR (" + PrintHelper.astHelper(this.expr, indentLevel)
            + " (NAME " + this.name + "))";
    }
}

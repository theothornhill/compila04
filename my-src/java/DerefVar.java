
public class DerefVar extends Var {
    public DerefVar(Var variable) {
        super(variable.expr, variable.name);
    }

    public String printAst(int indentLevel) {
        if (this.expr == null) {
            return "(DEREFVAR " + PrintHelper.printName(name);
        }
        return "(DEREFVAR " + PrintHelper.astHelper(expr, indentLevel)
            + PrintHelper.printName(name);
    }
}

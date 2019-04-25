import bytecode.*;
import bytecode.type.*;

public class RefVar extends Var {
    public RefVar(Var variable) {
        super(variable.expr, variable.name);
    }

    public RefVar(Object obj) {
        super(obj.toString());
    }

    public String printAst(int indentLevel) {
        if (this.expr == null) {
            return "(REFVAR" + PrintHelper.printName(name) + ")";
             }
        return "(REFVAR" + PrintHelper.astHelper(expr, indentLevel)
            + PrintHelper.printName(name)
            + PrintHelper.endWithParen(indentLevel);
    }
}

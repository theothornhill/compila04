import bytecode.*;
import bytecode.type.*;

public class Not extends Expr {
    public Not(Object expr) {
        this.expr = expr;
    }

    public void generateCode(CodeFile codeFile) {
        
    }

    public String printAst(int indentLevel) {
        return "(NOT " + PrintHelper.astHelper(expr, indentLevel+1)
            + PrintHelper.endWithParen(indentLevel)
            ;
    }
}

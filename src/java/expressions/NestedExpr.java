import bytecode.*;
import bytecode.type.*;

public class NestedExpr extends Expr {
    public NestedExpr(Object expr) {
        this.expr = expr;
    }

    public void generateCode(CodeFile codeFile) {
        
    }

    public void generateCode(CodeProcedure proc) {
        
    }
    public String printAst(int indentLevel) {
        return ""+ PrintHelper.astHelper(this.expr, indentLevel) +"";
    }
}

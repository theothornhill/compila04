import bytecode.*;
import bytecode.type.*;

public class NestedExpr extends Expr {
    public NestedExpr(Object expr) {
        this.expr = expr;
    }

    public void typeCheck(SymbolTable table, Object scope) throws Exception {
        ((Expr)expr).typeCheck(table, scope);
        this.type = new Type(((Expr)expr).type.toString());
    }

    public void generateCode(CodeProcedure proc, SymbolTable table, Object scope) {
        CodeGenerationHelper.exprHelper(proc, expr, table, scope);
    }
    
    public String toString() {
        return expr.toString();
    }

    public String printAst(int indentLevel) {
        return ""+ PrintHelper.astHelper(this.expr, indentLevel) +"";
    }
}

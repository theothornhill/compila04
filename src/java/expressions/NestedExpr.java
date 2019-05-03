import bytecode.*;
import bytecode.type.*;

public class NestedExpr extends Expr {
    public NestedExpr(Object expr) {
        this.expr = expr;
    }

    public void addToSymbolTable(SymbolTable table) {
        table.insert(expr);
    }

    public void generateCode(CodeProcedure proc) {
        CodeGenerationHelper.exprHelper(proc, expr);
    }

    public String printAst(int indentLevel) {
        return ""+ PrintHelper.astHelper(this.expr, indentLevel) +"";
    }
}

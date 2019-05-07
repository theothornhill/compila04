import bytecode.*;
import bytecode.type.*;

public class NestedExpr extends Expr {
    public NestedExpr(Object expr) {
        this.expr = expr;
    }

    public void typeCheck() {
        
    }

    public void typeCheck(SymbolTable table, Object scope) throws Exception {
        
    }

    public void addToSymbolTable(SymbolTable table) {
        try {
            table.insert(expr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateCode(CodeProcedure proc) {
        CodeGenerationHelper.exprHelper(proc, expr);
    }
    
    public String toString() {
        return expr.toString();
    }

    public String printAst(int indentLevel) {
        return ""+ PrintHelper.astHelper(this.expr, indentLevel) +"";
    }
}

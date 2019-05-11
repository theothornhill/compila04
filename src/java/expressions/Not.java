import bytecode.*;
import bytecode.type.*;

public class Not extends Expr {
    CodeType t;
    public Not(Object expr) {
        this.expr = expr;
        this.type = new Type("bool");
    }

    public void typeCheck(SymbolTable table, Object scope) throws Exception {
        Object e = table.lookup(scope, ((Var)expr).name);
        if (expr instanceof Expr) {
            ((Expr)expr).typeCheck(table, scope);
            if (!((Expr)expr).type.equals("bool"))
                throw new Exception("Argument of not operator not of type bool");            
        }
        if (!((BinaryExpr)expr).isBoolean) {
            throw new Exception("Result of binary expression must be boolean");
        }
    }

    public void addToSymbolTable(SymbolTable table) {
        try {
            table.insert(expr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateCode(CodeProcedure proc, SymbolTable table, Object scope) {
        
    }    

    public String toString() {
        return expr.toString();
    }

    public String printAst(int indentLevel) {
        return "(NOT " + PrintHelper.astHelper(expr, indentLevel+1)
            + PrintHelper.endWithParen(indentLevel)
            ;
    }
}

import bytecode.*;
import bytecode.type.*;
import bytecode.instructions.*;

public class Not extends Expr {
    CodeType t;
    public Not(Object expr) {
        this.expr = expr;
        this.type = new Type("bool");
    }

    public void typeCheck(SymbolTable table, Object scope) throws Exception {
        Object e = null;
        if (expr instanceof Var) 
            e = table.lookup(scope, ((Var)expr).name);
        if (expr instanceof Expr) {
            ((Expr)expr).typeCheck(table, scope);
            if (!((Expr)expr).type.equals("bool"))
                throw new Exception("Argument of not operator not of type bool");            
        }
        if (expr instanceof BinaryExpr) {
            if (!((BinaryExpr)expr).isBoolean) {
                throw new Exception("Result of binary expression must be boolean");
            }            
        }
    }

    public void generateCode(CodeProcedure proc, SymbolTable table, Object scope) throws Exception {
        if (expr != null) {
            if (expr instanceof BinaryExpr) {
                ((BinaryExpr)expr).generateCode(proc, table, scope);                
            } 
            proc.addInstruction(new NOT());                
        }
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

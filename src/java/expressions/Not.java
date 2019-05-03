import bytecode.*;
import bytecode.type.*;

// Must take a boolean value and return its negation. 
public class Not extends Expr {
    CodeType t;
    public Not(Object expr) {
        this.expr = expr;
    }

    public void addToSymbolTable(SymbolTable table) {
        // Doesn't give ant type checking info here
        table.insert(expr);
    }

    public void generateCode(CodeProcedure proc) {
        
    }    

    public String printAst(int indentLevel) {
        return "(NOT " + PrintHelper.astHelper(expr, indentLevel+1)
            + PrintHelper.endWithParen(indentLevel)
            ;
    }
}

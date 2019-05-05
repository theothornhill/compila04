import bytecode.*;
import bytecode.type.*;
    
public class Var extends Expr {
    String name;

    public Var(Object expr, String name) {
        this.expr = expr;
        this.name = name;
    }

    public Var(String name) {
        this.name = name;
    }

    public void typeCheck(SymbolTable table) throws Exception {
        VarDecl e;
        if (expr != null)
            e = (VarDecl)table.lookup(name);
        // if (expr == null)
            // throw new Exception("No such expression in variable");

    }

    public void addToSymbolTable(SymbolTable table) {
        table.insert(name);
        table.insert(expr);
    }

    public void generateCode(CodeProcedure proc) {
        
    }
    
    public String printAst(int indentLevel) {
        if (this.expr == null) {
            return "(VAR" + PrintHelper.printName(name) + ")";
        }
        return PrintHelper.astHelper(expr, indentLevel)
            + PrintHelper.printName(name)
            + PrintHelper.endWithParen(indentLevel);
    }

    public String toString() {
        return name;
    }
}

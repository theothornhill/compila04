import bytecode.*;
import bytecode.type.*;
    
public class Var extends Expr {
    public String name;

    public Var(Object expr, String name) {
        this.expr = expr;
        this.name = name;
    }

    public Var(String name) {
        this.name = name;
    }

    public void typeCheck(SymbolTable table) throws Exception {
        Object v = table.lookup(name);
        if (v == null)
            throw new Exception("Variable " + name + " not declared");
        if (v instanceof Expr) {
            Expr variable = (Expr)v;
            this.type = variable.type;            
        }
        if (v instanceof Decl) {
            Decl variable = (Decl)v;
            System.out.println(variable.type);
            this.type = variable.type;
        }
        // if (expr == null)
        //     throw new Exception("No such expression in variable");

    }

    public void addToSymbolTable(SymbolTable table) {
        table.insert(name);
        table.insert(expr);
    }

    public void setType(Type t) {
        this.type = t;
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

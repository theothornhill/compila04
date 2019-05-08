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

    public void typeCheck(SymbolTable table, Object scope) throws Exception {
        Object v = table.lookup(scope, name);

        if (v == null)
            throw new Exception("Variable " + name + " not declared");
        if (v instanceof Param) {
            this.type = ((Param)v).type;
        } else if (v instanceof Literal) {
            this.type = new Type(((Literal)v).type.toString());
        } else if (v instanceof Expr) {
            Expr variable = (Expr)v;
            this.type = variable.type;            
        } else if (v instanceof Decl) {
            Decl variable = (Decl)v;
            this.type = variable.type;
        } else if (this instanceof RefVar) {
            this.type = new Type("reftype");
        }
    }

    public void addToSymbolTable(SymbolTable table) {
        try {
            table.insert(name);
            table.insert(expr);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

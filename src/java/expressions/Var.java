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
        Object v = null;
        if (expr != null)
            v = table.lookup(scope, expr.toString());
        else
            v = table.lookup(scope, name);
        
        if (v == null)
            throw new Exception("Variable " + expr + "." + name + " not declared");
        checkIfRecord(table, scope);
        setTypeFromExpr(v);
    }

    public void checkIfRecord(SymbolTable table, Object scope) throws Exception {
        RecDecl r = null;
        Param p = null;
        if (expr != null) {
            ((Expr)expr).typeCheck(table, scope);
            r = (RecDecl)table.lookup(scope, ((Expr)expr).type.toString());
            if (r == null)
                throw new Exception("Record not declared");
            p = (Param)table.lookup(r, name);
            if (p == null)
                throw new Exception("Attribute not declared");
        }

    }

    public void setTypeFromExpr(Object variable) {
        if (variable instanceof Param) {
            this.type = ((Param)variable).type;
        } else if (variable instanceof Literal) {
            this.type = new Type(((Literal)variable).type.toString());
        } else if (variable instanceof Expr) {
            Expr vari = (Expr)variable;
            this.type = vari.type;            
        } else if (variable instanceof Decl) {
            Decl vari = (Decl)variable;
            this.type = vari.type;
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

    public void generateCode(CodeProcedure proc, SymbolTable table, Object scope) {
        
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

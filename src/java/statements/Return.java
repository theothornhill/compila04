import bytecode.*;
import bytecode.type.*;

public class Return extends Stmt {
    public Return(Object expr) {
        this.e = expr;
        this.type = ((Expr)expr).type;
    }

    public Return() {
        this.type = new Type("null");
    }

    public Object getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Object node) {
        this.createdBy = node;
    }

    public void setCreatorOf() {

    }

    public void setLexicalScopeLevel(int scope) {
        this.lexicalScopeLevel = scope;
    }

    public void typeCheck(SymbolTable table, Object scope) throws Exception {
        Object proc = table.lookup(scope, ((ProcDecl)scope).name);
        if (proc == null)
            throw new Exception("Procedure not declared");
        typecheckExpressionsInReturn(table, scope);
        Type returnType = ((ProcDecl)proc).type;
        if (!this.type.equals(returnType.toString()))
            throw new Exception("Return type differs from stated returntype");
    }

    public void typecheckExpressionsInReturn(SymbolTable table, Object scope) {
        try {
            if (e != null) {
                ((Expr)e).typeCheck(table, scope);
                // Sets the return type based on the expressions it has
                this.type = new Type(((Expr)e).type.toString()); 

            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addToSymbolTable(SymbolTable table) {

    }

    public void generateCode(CodeFile codeFile, SymbolTable table, Object scope) {
        
    }

    public void generateCode(CodeProcedure proc, SymbolTable table, Object scope) {
        // CodeGenerationHelper.returnHelper(proc, table, scope);
        if (e != null) {
            ((BinaryExpr)e).generateCode(proc, table, scope);
        }
    }
    
    public String printAst(int indentLevel) {
        if (this.e == null) {
            return "(RETURN)";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("(RETURN " + PrintHelper.astHelper(this.e, indentLevel+1));
            sb.append(PrintHelper.endWithParen(indentLevel));
            return sb.toString();
        }
    }

}

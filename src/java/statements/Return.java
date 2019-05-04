import bytecode.*;
import bytecode.type.*;

public class Return extends Stmt {
    public Return(Object expr) {
        this.e = expr;
    }

    public Return() {
        
    }

    public Object createdBy() {
        return this.createdBy;
    }

    public void setCreatorOf() {
        
    }

    public void setLexicalScopeLevel(int scope) {
        
    }

    public void setCreatedBy(Object node) {
        
    }


    public void typeCheck() {
        
    }

    public void addToSymbolTable(SymbolTable table) {
        // table.insert("Statement-type", "Return");
        // table.insert("Expr", e);
    }

    public void generateCode(CodeFile codeFile) {
        
    }

    public void generateCode(CodeProcedure proc) {
        CodeGenerationHelper.returnHelper(proc);
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

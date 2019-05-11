import java.util.*;
import bytecode.*;
import bytecode.type.*;

public class While extends Stmt {
    Object e;
    LinkedList<Stmt> sl;

    public While(Object e, LinkedList<Stmt> sl) {
        this.e = e;
        this.sl = sl;
    }

    public void typeCheck(SymbolTable table, Object scope) throws Exception {
        ((Expr)e).typeCheck(table, scope);
        if (!(((Expr)e).type.equals("bool"))) {
            throw new Exception("Condition in while-statement must be boolean");
        }
        if (sl != null)
            TypeCheckHelper.typeCheckStatements(sl, table, scope);
    }

    public Object getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Object node) {
        this.createdBy = node;
    }

    public void setCreatorOf() {
        // Nothing yet
    }

    public void setLexicalScopeLevel(int scope) {
        this.lexicalScopeLevel = scope;
    }

    public void addToSymbolTable(SymbolTable table) {

    }

    public void generateCode(CodeFile codeFile, SymbolTable table, Object scope) {
        
    }

    public void generateCode(CodeProcedure proc, SymbolTable table, Object scope) {
        
    }
    
    public void generateCode(CodeFile codeFile,
                             CodeProcedure proc,
                             SymbolTable table,
                             Object scope) {
        CodeGenerationHelper.exprHelper(proc, e, table, scope);
        CodeGenerationHelper.stmtTraverser(sl, codeFile, proc, table, scope);
    }
    
    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(WHILE ");
        sb.append(PrintHelper.astHelper(e, indentLevel+1));
        for (Stmt stmt : sl) {
            sb.append(PrintHelper.printStmt(stmt, indentLevel+1));
        }
        sb.append(PrintHelper.endWithParen(indentLevel));
        return sb.toString();
    }
}

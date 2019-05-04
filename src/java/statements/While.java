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

    public void typeCheck() throws Exception {
        if (!(e instanceof BinaryExpr))
            throw new Exception("Condition in while-statement must be binary");
        if (!((BinaryExpr)e).isBoolean) {
            throw new Exception("Condition in while-statement must be boolean");
        }
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


    public void addToSymbolTable(SymbolTable table) {
        // table.insert("Expr", e);
        // sl.stream().forEach(s -> table.insert(s.toString(), s));
    }

    public void generateCode(CodeFile codeFile) {
        
    }

    public void generateCode(CodeProcedure proc) {
        
    }
    
    public void generateCode(CodeFile codeFile, CodeProcedure proc) {
        CodeGenerationHelper.exprHelper(proc, e);
        CodeGenerationHelper.stmtTraverser(sl, codeFile, proc);
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

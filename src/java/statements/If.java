import java.util.*;
import bytecode.*;
import bytecode.type.*;

public class If extends Stmt {
    LinkedList<Stmt> sl2;
    public If(Object e, LinkedList<Stmt> sl) {
        this.e = e;
        this.sl = sl;
    }

    public If(Object e, LinkedList<Stmt> sl, LinkedList<Stmt> sl2) {
        this.e = e;
        this.sl = sl;
        this.sl2 = sl2;
    }

    public Object getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Object node) {
        this.createdBy = node;
    }

    public void setCreatorOf() {
        // nothing yet
    }

    public void setLexicalScopeLevel(int scope) {
        this.lexicalScopeLevel = scope;
    }

    public void typeCheck(SymbolTable table, Object scope) throws Exception {
        ((Expr)e).typeCheck(table, scope);
        if (!(((Expr)e).type.equals("bool"))) {
            throw new Exception("Condition in while-statement must be boolean");
        }
        if (sl != null)
            typecheckStmtList(table, scope, sl);
        if (sl2 != null)
            typecheckStmtList(table, scope, sl2);
    }

    public void typecheckStmtList(SymbolTable table, Object scope, LinkedList<Stmt> stmts) throws Exception {
        try {
            for (Stmt s : stmts) {
                s.typeCheck(table, scope);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addToSymbolTable(SymbolTable table) {
        // // table.insert("Expr", CodeGenerationHelper.getTable(e));
        // sl.stream().forEach(s -> table.insert(s.toString(), s));
        // sl2.stream().forEach(s -> table.insert(s.toString(), s));
    }

    public void generateCode(CodeFile codeFile) {
        
    }

    public void generateCode(CodeProcedure proc) {
        
    }

    public void generateCode(CodeFile codeFile, CodeProcedure proc) {
        CodeGenerationHelper.exprHelper(proc, e);
        CodeGenerationHelper.stmtTraverser(sl, codeFile, proc);
        if (sl2 != null)
            CodeGenerationHelper.stmtTraverser(sl2, codeFile, proc);
    }    

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(IF ");
        sb.append(PrintHelper.astHelper(e, indentLevel+1));
        if (sl != null) {
            for (Stmt stmt : sl) {
                sb.append(PrintHelper.printStmt(stmt, indentLevel+1));
            }            
        }
        if (sl2 != null) {
            sb.append(PrintHelper.newlineAndIndentWithHelper("(ELSE", indentLevel+1));
            for (Stmt stmt : sl2) {
                sb.append(PrintHelper.printStmt(stmt, indentLevel+2));
             }
            sb.append(PrintHelper.endWithParen(indentLevel+1));
        }
        sb.append(PrintHelper.endWithParen(indentLevel));
        return sb.toString();
    }
}

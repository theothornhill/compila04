import java.util.*;
import bytecode.*;
import bytecode.type.*;
import bytecode.instructions.*;

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
            throw new Exception("Condition in if-statement must be boolean");
        }
        if (sl != null)
            TypeCheckHelper.typeCheckStatements(sl, table, scope);
        if (sl2 != null)
            TypeCheckHelper.typeCheckStatements(sl2, table, scope);
    }

    public void addToSymbolTable(SymbolTable table) {

    }

    public void generateCode(CodeFile codeFile, SymbolTable table, Object scope) {
        
    }

    public void generateCode(CodeProcedure proc, SymbolTable table, Object scope) {
        int jump;
        int trueClause;
        int falseClause;
        int end;
        if (e instanceof BinaryExpr) {
            ((BinaryExpr)e).generateCode(proc, table, scope);

            jump = proc.addInstruction(new NOP());
            CodeGenerationHelper.stmtTraverser(sl, proc.getCodeFile(), proc, table, scope);
            trueClause = proc.addInstruction(new NOP());

            falseClause = proc.addInstruction(new NOP());
            CodeGenerationHelper.stmtTraverser(sl2, proc.getCodeFile(), proc, table, scope);
            end = proc.addInstruction(new NOP());

            proc.replaceInstruction(trueClause, new JMP(end));
            proc.replaceInstruction(jump, new JMPFALSE(falseClause));
        }        
    }

    // public void generateCode(CodeFile codeFile, CodeProcedure proc, SymbolTable table, Object scope) {


    // }    

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

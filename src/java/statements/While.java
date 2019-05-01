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

    public void addToSymbolTable(SymbolTable table) {
        table.insert("Statement-type", "while");
        table.insert("Expr", e);
        sl.stream().forEach(s -> table.insert(s.toString(), s));
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

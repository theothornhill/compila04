import java.util.*;
import bytecode.*;
import bytecode.type.*;
import bytecode.instructions.*;

public class ProcDecl extends Decl {
    LinkedList<Param> pl;
    LinkedList<Decl> dl;
    LinkedList<Stmt> sl;
    Type type;
    CodeProcedure proc;

    // Constructors
    public ProcDecl(String name,
                    LinkedList<Param> pl,
                    LinkedList<Decl> dl,
                    LinkedList<Stmt> sl) {
        this.name = name;
        this.pl = pl;
        this.dl = dl;
        this.sl = sl;
    }

    public ProcDecl(String name,
                    LinkedList<Param> pl,
                    Type type,
                    LinkedList<Decl> dl,
                    LinkedList<Stmt> sl) {
        this.name = name;
        this.pl = pl;
        this.type = type;
        this.dl = dl;
        this.sl = sl;
    }

    public ProcDecl(String name,
                    LinkedList<Param> pl,
                    Type type,
                    LinkedList<Stmt> sl) {
        this.name = name;
        this.pl = pl;
        this.sl = sl;
    }

    public ProcDecl(String name,
                    LinkedList<Param> pl,
                    LinkedList<Stmt> sl) {
        this.name = name;
        this.pl = pl;
        this.dl = dl;
        this.sl = sl;
    }

    public void generateCode(CodeFile codeFile) {
        codeFile.addProcedure(this.name);

        if (type == null)
            proc = CodeGenerationHelper.newProc(name, null, codeFile);
        else 
            proc = CodeGenerationHelper.newProc(name, type, codeFile);
        
        CodeGenerationHelper.paramTraverser(pl, proc);

        CodeGenerationHelper.declTraverser(dl, codeFile);

        CodeGenerationHelper.stmtTraverser(sl, codeFile, proc);
        // Handle the return statement differently?
        proc.addInstruction(new RETURN());
        codeFile.updateProcedure(proc);

    }

    private String printType(int indentLevel) {
        return type == null ? "(TYPE void)" : type.printAst(indentLevel);
    }

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(PROC_DECL ");
        sb.append(printType(indentLevel));
        sb.append(PrintHelper.printName(name));
        if (pl != null) {
            for (Param param : pl) {
                sb.append(PrintHelper.printParam(param, indentLevel+1));
            }
            sb.append("\n");
        }

        if (dl != null) {
            for (Decl decl : dl) {
                sb.append(PrintHelper.printDecl(decl, indentLevel+1));
            }            
            sb.append("\n");
        }

        if (sl != null) {
            for (Stmt stmt : sl) {
                sb.append(PrintHelper.printStmt(stmt, indentLevel+1));
            }            
        }
        sb.append(PrintHelper.endWithParen(indentLevel));
        return sb.toString();
    }
}

import java.util.*;
import bytecode.*;
import bytecode.type.*;
import bytecode.instructions.*;

public class ProcDecl extends Decl {
    public LinkedList<Param> pl;
    public LinkedList<Decl> dl;
    public LinkedList<Stmt> sl;
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
        this.type = new Type("null");
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

    public void typeCheck() throws Exception {
        ProcDecl proc = null;
        if (table.lookup(this, this.name) instanceof ProcDecl)
            table.lookup(this, this.name);
        TypeCheckHelper.typeCheckReturnType(table, this);
        TypeCheckHelper.typeCheckParams(pl, table, this);
        TypeCheckHelper.typeCheckDecls(dl);
        TypeCheckHelper.typeCheckStatements(sl, table, this);
        TypeCheckHelper.typeCheckIfReturnTypeIsMatching(sl, this);
    }

    public Object getCreatedBy() {
        return this.createdBy;
    }

    public LinkedList<Param> getParams() {
        return pl;
    }

    public void setCreatedBy(Object node) {
        this.createdBy = node;
    }

    public void setCreatorOf() {
        if (pl != null) 
            pl.stream().forEach(d -> d.setCreatedBy(this));
        if (dl != null) 
            dl.stream().forEach(d -> d.setCreatedBy(this));
        if (sl != null) 
            sl.stream().forEach(d -> d.setCreatedBy(this));
    }

    public void setLexicalScopeLevel(int scope) {
        this.lexicalScopeLevel = scope;
        if (pl != null) {
            pl.stream().forEach(d -> d.setLexicalScopeLevel(lexicalScopeLevel+1));
        }
        if (dl != null) {
            dl.stream().forEach(d -> d.setLexicalScopeLevel(lexicalScopeLevel+1));
        }
        if (sl != null) {
            sl.stream().forEach(d -> d.setLexicalScopeLevel(lexicalScopeLevel+1));
        }
    }

    public void addToSymbolTable() throws Exception {
        if (pl != null)
            for (Param p : pl)
                table.insert(p);
        if (dl != null)
            for (Decl d : dl) {
                table.insert(d);
                d.addToSymbolTable();
            }
    }

    public void generateCode(CodeFile codeFile) {
        codeFile.addProcedure(this.name);
        if (!CodeGenerationHelper.isLibraryProcedure(this.name)) {
            proc = type == null
                ? CodeGenerationHelper.newProc(name, null, codeFile)
                : CodeGenerationHelper.newProc(name, type.toString(), codeFile);
                
            CodeGenerationHelper.paramTraverser(pl, proc, table, this);
            CodeGenerationHelper.declTraverser(dl, proc);
            CodeGenerationHelper.stmtTraverser(sl, codeFile, proc, table, this);
            // Handle the return statement differently?
            proc.addInstruction(new RETURN());
            codeFile.updateProcedure(proc);
        }
    }

    public void generateCode(CodeProcedure proc) {
        CodeFile codeFile = proc.getCodeFile();
        this.generateCode(codeFile);
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
            for (Param param : pl)
                sb.append(PrintHelper.printParam(param, indentLevel+1));
            sb.append("\n");
        }

        if (dl != null) {
            for (Decl decl : dl)
                sb.append(PrintHelper.printDecl(decl, indentLevel+1));
            sb.append("\n");
        }

        if (sl != null) {
            for (Stmt stmt : sl)
                sb.append(PrintHelper.printStmt(stmt, indentLevel+1));
        }
        sb.append(PrintHelper.endWithParen(indentLevel));
        return sb.toString();
    }
}

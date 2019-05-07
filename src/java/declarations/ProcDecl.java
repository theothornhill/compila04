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
        Object returnTypeCheck = table.lookup(this, type.toString());
        if (returnTypeCheck == null)
            if (!type.equals("null"))
                throw new Exception("Undeclared return type");

        ProcDecl proc = null;
        if (table.lookup(this, this.name) instanceof ProcDecl)
            table.lookup(this, this.name);

        if (proc != null && proc.getLexicalScopeLevel() != this.lexicalScopeLevel)
            throw new Exception("Procedure " + this.name + " already declared");

        // TODO:There is a major bug here... nullpointer and type assignment??
        // if (sl != null && sl.getLast() instanceof Return)
        //     if (!sl.getLast().type.equals(this.type))
        //         throw new Exception("Not matching return type");
        typecheckParamsInProcDecl();
        typecheckDeclarationsInProcDecl();
        typecheckStatementsInProcDecl();
    }

    public void typecheckParamsInProcDecl() {
        try {
            if (pl != null)
                for (Param p : pl) {
                    p.typeCheck();
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void typecheckDeclarationsInProcDecl() {
        try {
            if (dl != null)
                for (Decl d : dl) {
                    d.addToSymbolTable();
                    d.typeCheck();
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void typecheckStatementsInProcDecl() {
        try {
            if (sl != null)
                for (Stmt s : sl) {
                    s.typeCheck(this.table, this);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            pl.stream().forEach(d ->
                                System.out.println("" + d.name + ": scope " + d.lexicalScopeLevel +
                                                   " createdby " + d.createdBy));            
        }
        if (dl != null) {
            dl.stream().forEach(d -> d.setLexicalScopeLevel(lexicalScopeLevel+1));
            dl.stream().forEach(d ->
                                System.out.println("" + d.name + ": scope " + d.lexicalScopeLevel +
                                                   " createdby " + d.createdBy));            
        }
        if (sl != null) {
            sl.stream().forEach(d -> d.setLexicalScopeLevel(lexicalScopeLevel+1));
            sl.stream().forEach(d ->
                                System.out.println("" + d.name + ": scope " + d.lexicalScopeLevel +
                                                   " createdby " + d.createdBy));            
        }
    }

    public void addToSymbolTable() throws Exception {
        try {
            if (pl != null)
                for (Param p : pl)
                    table.insert(p);
            if (dl != null)
                for (Decl d : dl)
                    table.insert(d);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void generateCode(CodeFile codeFile) {
        codeFile.addProcedure(this.name);
        // meant to handle if it is a library procedure, and as such just update
        // at once
        if (CodeGenerationHelper.isLibraryProcedure(name))
            codeFile.updateProcedure(proc);
        else
            proc = type == null
                ? CodeGenerationHelper.newProc(name, null, codeFile)
                : CodeGenerationHelper.newProc(name, type, codeFile);
                
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

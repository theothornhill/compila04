import java.util.*;
import bytecode.*;
import bytecode.type.*;
import bytecode.instructions.*;

public class Call extends Stmt {
    LinkedList<Object> el;
    public Call(String name, LinkedList<Object> el) {
        this.name = name;
        this.el = el;
    }

    public void typeCheck(SymbolTable table, Object scope) throws Exception {
        typecheckArguments(table, scope);
        int procedureParamNumber = 0;
        int exprParamNumber = 0;
        Object proc = table.lookup(scope, name);
        // Is procedure declared?
        System.out.println(name);
        if (proc == null)
            throw new Exception("Procedure not declared");
        ProcDecl procedure = (ProcDecl)proc;
        this.type = new Type(procedure.type.toString());
        // Need to account for when one or the other is null...
        if (procedure.pl != null)
            procedureParamNumber = procedure.pl.size();
        if (el != null)
            exprParamNumber = el.size();
                    
        if (procedureParamNumber != exprParamNumber)
            throw new Exception("Procedure requires " + procedureParamNumber + 
                                " arguments, but was given " + exprParamNumber +
                                " arguments");

        LinkedList<Param> params = procedure.getParams();
        if (params != null && el != null) {
            for (int i = 0; i < el.size(); i++) {
                Expr e = null;
                Call c = null;
                Param p = params.get(i);
                if (el.get(i) instanceof Call)
                    c = (Call)el.get(i);
                else 
                    e = (Expr)el.get(i);
                if (e != null) {
                    if (e.type != null)
                        if (e instanceof RefVar) {
                            e.type = new Type("reftype");                            
                        }
                    if (!p.type.equals(e.type.toString()))
                        throw new Exception("argument " + e + ": type " + e.type +
                                            " is not the same type as param " + p +
                                            ": type " + p.type);                    
                }
                if (c != null)
                    if (c.type != null)
                        if (!p.type.equals(c.type.toString()))
                            throw new Exception("argument " + c + ": type " + c.type +
                                                " is not the same type as param " + p +
                                                ": type " + p.type);
            }            
        }
    }

    public void typecheckArguments(SymbolTable table, Object scope) throws Exception {
        try {
            if (el != null)
                for (Object e : el) {
                    if (e instanceof Expr)
                        ((Expr)e).typeCheck(table, scope);
                    if (e instanceof Call)
                        ((Call)e).typeCheck(table, scope);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        try {
            table.insert("call");
            table.insert(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateCode(CodeFile codeFile) {
        
    }

    public void generateCode(CodeProcedure proc) {
        CodeGenerationHelper.exprTraverser(el, proc);
        proc.addInstruction(new CALL(proc.procedureNumber(this.name)));
    }

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(CALL_STMT");
        sb.append(PrintHelper.printName(name));
        if (el != null) {
            for (Object e : el) {
                sb.append(PrintHelper.newlineAndIndentWithHelper(e, indentLevel+1));
            }
            sb.append(PrintHelper.endWithParen(indentLevel));
        } else {
            sb.append(")");            
        }

        return sb.toString();
    }
}

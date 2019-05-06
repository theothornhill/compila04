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

    public void typeCheck(SymbolTable table) throws Exception {
        Object proc = table.lookup(name);
        // Is procedure declared?
        if (proc == null)
            throw new Exception("Procedure not declared");
        ProcDecl procedure = (ProcDecl)proc;
        if (procedure.pl.size() != el.size())
            throw new Exception("Procedure requires " + procedure.pl.size() +
                                " arguments, but was given " + el.size() +
                                " arguments");
        LinkedList<Param> params = ((ProcDecl)procedure).pl;
        for (int i = 0; i < el.size(); i++) {
            Param p = params.get(i); Expr e = (Expr)el.get(i);
            if (!p.type.toString().equals(e.type.toString()))
                throw new Exception("argument " + e + ": type " + e.type +
                                    " is not the same type as param " + p +
                                    ": type " + p.type);
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
        table.insert("call");
        table.insert(name);
        // el.stream()
        //     .forEach(e -> table.insert(e.getClass().toString(),
        //                                CodeGenerationHelper.getTable(e)));
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

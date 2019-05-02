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

    public void addToSymbolTable(SymbolTable table) {
        table.insert("Statement-type", "call");
        table.insert("Name", name);
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

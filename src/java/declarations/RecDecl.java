import java.util.*;
import bytecode.*;
import bytecode.type.*;
import bytecode.instructions.*;

public class RecDecl extends Decl {
    LinkedList<Param> pl;
    CodeStruct struct;
    SymbolTable table = new SymbolTable();

    public RecDecl(String name, LinkedList<Param> pl) {
        this.name = name;
        this.pl = pl;
        struct = new CodeStruct(this.name);
    }

    public void generateCode(CodeFile codeFile) {
        codeFile.addStruct(this.name);
        CodeGenerationHelper.paramTraverser(pl, struct);
        codeFile.updateStruct(struct);
    }

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(STRUCT");
        sb.append(PrintHelper.printName(name));
        if (pl != null) {
            for (Param param : pl) {
                sb.append(PrintHelper.printParam(param, indentLevel+1));
            }            
        }
        sb.append(PrintHelper.endWithParen(indentLevel));
        return sb.toString();
    }
}

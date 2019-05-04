import java.util.*;
import bytecode.*;
import bytecode.type.*;
import bytecode.instructions.*;

public class RecDecl extends Decl {
    LinkedList<Param> pl;
    CodeStruct struct;

    public RecDecl(String name, LinkedList<Param> pl) {
        this.name = name;
        this.pl = pl;
        struct = new CodeStruct(this.name);
    }

    public void typeCheck() throws Exception {
        // pl.stream().forEach(p -> {
        //         if (!(p.type instanceof Type))
        //             throw new Exception("Wrong type declaration in RecDecl");
        //     });
    }

    public Object createdBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Object node) {
        this.createdBy = node;
    }

    public void setCreatorOf() {
        if (pl != null)
            pl.stream().forEach(p -> p.setCreatedBy(this));
    }

    public void setLexicalScopeLevel(int scope) {
        this.lexicalScopeLevel = scope;
        if (pl != null) {
            pl.stream().forEach(d -> d.setLexicalScopeLevel(lexicalScopeLevel+1));
            pl.stream().forEach(d ->
                                System.out.println("" + d.name + d.lexicalScopeLevel +
                                                   " createdby " + d.createdBy));                        
        }
    }

    public void addToSymbolTable(SymbolTable table) {
        // table.insert(name);
        for (Param p : pl) {
            table.insert(p);
        }
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

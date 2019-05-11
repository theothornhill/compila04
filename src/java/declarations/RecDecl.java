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
        this.type = new Type(name);
        struct = new CodeStruct(this.name);
    }

    public void typeCheck() throws Exception {
        TypeCheckHelper.typeCheckParams(pl, table, this);
    }

    public int getLexicalScopeLevel() {
        return lexicalScopeLevel;
    }

    // Attribute grammar annotation methods
    public Object getCreatedBy() {
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
        }
    }

    public void addToSymbolTable() throws Exception {
        for (Param p : pl) {
            table.insert(p);
        }
    }
    
    public void generateCode(CodeFile codeFile) {
        // As for now structs are added globally. Fix later maybe?
        codeFile.addStruct(this.name);
        CodeGenerationHelper.paramTraverser(pl, struct);
        codeFile.updateStruct(struct);
    }

    public void generateCode(CodeProcedure proc) {
        // Do I need to add to procedures? This adds globally
        this.generateCode(proc.getCodeFile());
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

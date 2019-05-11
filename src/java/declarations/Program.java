import java.util.*;
import bytecode.*;

public class Program extends Decl implements AttributeGrammar {
    LinkedList<Decl> declarations;
    Object mainUppercase;
    Object mainLowercase;
    public Program(String name, LinkedList<Decl> declarations) {
        this.name = name;
        this.declarations = declarations;
    }

    public void typeCheck() throws Exception {
        mainUppercase = table.lookup(this, "Main");
        mainLowercase = table.lookup(this, "main");
        if (mainUppercase == null && mainLowercase == null) {
            throw new Exception("No Main procedure declared in program");
        }

        TypeCheckHelper.typeCheckDecls(declarations);
    }

    public SymbolTable getTable() {
        return this.table;
    }

    // Attribute grammar methods
    public Object getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(Object node) {
        this.createdBy = "Base level program";
    }

    public void setCreatorOf() {
        declarations.stream().forEach(d -> d.setCreatedBy(this));
    }

    public void generateCode(CodeFile codeFile) {
        CodeGenerationHelper.declTraverser(declarations, codeFile);
        if (mainLowercase != null)
            codeFile.setMain("main");
        else 
            codeFile.setMain("Main");
    }

    public void generateCode(CodeProcedure proc) {
        
    }

    public void addToSymbolTable() throws Exception {
        if (declarations != null) {
            for (Decl d : declarations) {
                table.insert(d);
                d.addToSymbolTable();
            }
        }
    }

    public void printTable(SymbolTable table) {
        System.out.println(table);
    }

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        indentLevel = 0;
        sb.append("(PROGRAM");
        sb.append(PrintHelper.printName(name));
        if (declarations != null) {
            for (Decl decl : declarations) {
                if (decl != null) {
                    sb.append(PrintHelper.printDecl(decl, indentLevel+1));
                }
            }            
        }
        sb.append(PrintHelper.endWithParen(indentLevel));
        return sb.toString();
    }

    public String toString() {
        return "Program " + name;
    }

}

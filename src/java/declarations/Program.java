import java.util.*;
import bytecode.*;
import java.util.*;

public class Program implements AttributeGrammar {
    String name;
    LinkedList<Decl> declarations;
    public Object createdBy;

    public Program(String name, LinkedList<Decl> declarations) {
        this.name = name;
        this.declarations = declarations;
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

    public void setLexicalScopeLevel(int scope) {
        declarations.stream().forEach(d -> d.setLexicalScopeLevel(scope));
        declarations.stream().forEach(d ->
                   System.out.println("" + d.name + ": scope " + d.lexicalScopeLevel +
                                      " createdby " + d.createdBy));
    }

    public void generateCode(CodeFile codeFile) {
        CodeGenerationHelper.declTraverser(declarations, codeFile);
    }

    public void addToSymbolTable(SymbolTable table) {
        declarations.stream().forEach(decl -> table.insert(decl));
    }

    public void printTable(SymbolTable table) {
        System.out.println(table);
    }

    public String printAst() {
        StringBuilder sb = new StringBuilder();
        int indentLevel = 0;
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

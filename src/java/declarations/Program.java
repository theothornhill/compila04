import java.util.*;
import bytecode.*;

public class Program extends Decl implements AttributeGrammar {
    String name;
    LinkedList<Decl> declarations;
    public SymbolTable table = new SymbolTable();
    public Object createdBy;
    public int lexicalScopeLevel;

    public Program(String name, LinkedList<Decl> declarations) {
        this.name = name;
        this.declarations = declarations;
    }

    public void typeCheck() throws Exception {
        if (table.lookup(this, "Main") == null
            && table.lookup(this, "main") == null) {
            throw new Exception("No Main procedure declared in program");
        }
        typecheckDeclarationsInProgram();
    }

    public void typecheckDeclarationsInProgram() throws Exception {
        try {
            for (Decl d : declarations) {
                d.typeCheck();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int getLexicalScopeLevel() {
        return this.lexicalScopeLevel;
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

    public void setLexicalScopeLevel(int scope) {
        declarations.stream().forEach(d -> d.setLexicalScopeLevel(scope));
        declarations.stream().forEach(d ->
                   System.out.println("" + d.name + ": scope " + d.lexicalScopeLevel +
                                      " createdby " + d.createdBy));
    }

    public void generateCode(CodeFile codeFile) {
        CodeGenerationHelper.declTraverser(declarations, codeFile);
    }

    public void addToSymbolTable() throws Exception {
        try {
            if (declarations != null) {
                for (Decl d : declarations) {
                    table.insert(d);
                    d.addToSymbolTable();
                }
            }
                
        } catch (Exception e) {
            e.printStackTrace();
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

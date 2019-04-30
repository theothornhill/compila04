import java.util.*;
import bytecode.*;


public class Program {
    String name;
    LinkedList<Decl> declarations;
    SymbolTable table = new SymbolTable();

    public Program(String name, LinkedList<Decl> declarations) {
        this.name = name;
        this.declarations = declarations;
    }

    public void generateCode(CodeFile codeFile) {
        CodeGenerationHelper.declTraverser(declarations, codeFile);
    }

    public void addToSymbolTable() {
        table.insert("Name", name);
        declarations.stream().forEach(decl -> table.insert(decl.name, decl));
    }

    public void printTable() {
        System.out.println(table.toString());
        declarations.stream().forEach(d -> System.out.println(d.table.toString()));
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
}

import java.util.*;
import bytecode.*;
import java.util.*;

public class Program {
    String name;
    LinkedList<Decl> declarations;

    public Program(String name, LinkedList<Decl> declarations) {
        this.name = name;
        this.declarations = declarations;
    }

    public void generateCode(CodeFile codeFile) {
        CodeGenerationHelper.declTraverser(declarations, codeFile);
    }

    public void addToSymbolTable(SymbolTable table, int scope) {
        declarations.stream().forEach(decl -> table.insert(scope, decl));
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
}

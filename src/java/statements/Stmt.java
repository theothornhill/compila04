import java.util.*;
import bytecode.*;
import bytecode.type.*;

public abstract class Stmt {
    String name;
    Object e;
    Object e2;
    LinkedList<Stmt> sl;
    SymbolTable table = new SymbolTable();
    public abstract String printAst(int indentLevel);
    public abstract void addToSymbolTable();
    public abstract void generateCode(CodeFile codeFile);
    public abstract void generateCode(CodeProcedure proc);

    public SymbolTable getTable() {
        return table;
    }

    public String toString() {
        return table.toString();
    }


}

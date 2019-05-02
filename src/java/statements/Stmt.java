import java.util.*;
import bytecode.*;
import bytecode.type.*;

public abstract class Stmt {
    String name;
    Object e;
    Object e2;
    LinkedList<Stmt> sl;
    public abstract String printAst(int indentLevel);
    public abstract void addToSymbolTable(SymbolTable table, int scope);
    public abstract void generateCode(CodeFile codeFile);
    public abstract void generateCode(CodeProcedure proc);

}

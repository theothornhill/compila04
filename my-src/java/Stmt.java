import java.util.*;
public abstract class Stmt {
    String name;
    Object e;
    Object e2;
    LinkedList<Stmt> sl;
    public abstract String printAst(int indentLevel);
}

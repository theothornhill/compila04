import java.util.*;
public abstract class Stmt {
    String name;
    Object e;
    LinkedList<Stmt> sl;
    public abstract String printAst(int indentLevel);
}

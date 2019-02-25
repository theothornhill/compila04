import java.util.*;
public abstract class Stmt {
    String name;
    Expr e;
    LinkedList<Stmt> sl;
    public abstract String printAst();
}

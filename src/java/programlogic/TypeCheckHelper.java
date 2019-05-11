import java.util.*;

public class TypeCheckHelper {

    public static void typeCheckDecls(LinkedList<Decl> dl) {
        try {
            if (dl != null) {
                for (Decl d : dl) {
                    d.typeCheck();
                }                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void typeCheckParams(LinkedList<Param> pl,
                                       SymbolTable table, Object scope) {
        try {
            if (pl != null)
                for (Param p : pl) {
                    p.typeCheck(table, scope);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void typeCheckStatements(LinkedList<Stmt> sl,
                                           SymbolTable table,
                                           Object scope) {
        try {
            if (sl != null)
                for (Stmt s : sl) {
                    s.typeCheck(table, scope);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void typeCheckReturnType(SymbolTable table, ProcDecl proc) throws Exception {
        if (CodeGenerationHelper.returntypeIsUserDefined(proc.type.toString())) {
            if (table.lookup(proc, proc.type.toString()) == null)
                throw new Exception("Undeclared user defined return type " + proc.type);
        }
    }

    public static void typeCheckIfReturnTypeIsMatching(LinkedList<Stmt> sl, ProcDecl proc) throws Exception {
        if (sl != null && sl.getLast() instanceof Return)
            if (!sl.getLast().type.equals(proc.type.toString()))
                throw new Exception("Not matching return type");
    }

    public static boolean isAssignable(Object o, Expr e) {
        if (o instanceof Param)
            return ((Param)o).type.equals("float") && e.type.equals("int");
        return ((VarDecl)o).type.equals("float") && e.type.equals("int");
    }

    public static void typeCheckArguments(LinkedList<Object> el, SymbolTable table, Object scope) throws Exception {
        try {
            if (el != null)
                for (Object e : el) {
                    if (e instanceof Expr)
                        ((Expr)e).typeCheck(table, scope);
                    if (e instanceof Call)
                        ((Call)e).typeCheck(table, scope);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static boolean isLibraryProcedure(String name) {
        return name.equals("printint") ||
            name.equals("printfloat")  ||
            name.equals("printstr")    ||
            name.equals("printline")   ||
            name.equals("readint")     ||
            name.equals("readfloat")   ||
            name.equals("readchar")    ||
            name.equals("readstring")  ||
            name.equals("readline");
    }

}

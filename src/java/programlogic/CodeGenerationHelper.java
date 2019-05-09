import bytecode.*;
import bytecode.type.*;
import bytecode.instructions.*;
import java.util.LinkedList;

// The idea of this helper class is to act like a factory for exprs. The problem
// with the expressions is that they can be a statement or expression. Therefore
// I need an elegant way to assign the values. 
public class CodeGenerationHelper {
    public static void exprHelper(CodeProcedure proc,
                                  Object node,
                                  SymbolTable table,
                                  Object scope) {
        if (node instanceof Expr)
            ((Expr) node).generateCode(proc, table, scope);
        else if (node instanceof Call)
            ((Call) node).generateCode(proc, table, scope);
        else if (node instanceof Literal) {
            // Get the literal and add it as a variable. Good for now, but must
            // be changed later I believe.
            Literal lit = ((Literal) node);
            // Also, dont use the toString later. 
            proc.addLocalVariable(lit.getValue().toString(), lit.getCodeType());
        }
        else
            System.out.println("Bug in Codegenerationhelper");
    }

    public static Instruction instructionHelper(CodeProcedure proc, Object op) {
        return op.toString().equals("+")
            ? new ADD()
            : op.toString().equals("-")
            ? new SUB()
            : op.toString().equals("/")
            ? new DIV()
            : op.toString().equals("*")
            ? new MUL()
            : op.toString().equals("<")
            ? new LT()
            : op.toString().equals("<=")
            ? new LTEQ()
            : op.toString().equals(">")
            ? new GT()
            : op.toString().equals(">=")
            ? new GTEQ()
            : op.toString().equals("<>")
            ? new NEQ()
            : op.toString().equals("||")
            ? new OR()
            : op.toString().equals("&&")
            ? new AND()
            : null;             // TODO: This seems like a terrible idea
    }

    // Return the literal value. Helps when you want to know what codetype a
    // literal is
    public static CodeType getLiteralType(String type) {
        return type.equals("int")
            ? IntType.TYPE
            : type.equals("string")
            ? StringType.TYPE
            : type.equals("float")
            ? FloatType.TYPE
            : type.equals("bool")
            ? BoolType.TYPE
            : type.equals("printint")
            ? IntType.TYPE
            : type.equals("printfloat")
            ? FloatType.TYPE
            : VoidType.TYPE;    // TODO: Literals can never be void. Fix
    }

    public static CodeProcedure newProc(String name, String type, CodeFile codeFile) {
        CodeProcedure proc;
        if (type == null) {
            proc = new CodeProcedure(name, VoidType.TYPE, codeFile);
        } else {
            proc = new CodeProcedure(name, getLiteralType(type), codeFile);
        }
        return proc;
    }

    public static void exprTraverser(LinkedList<Object> el,
                                     CodeProcedure proc, SymbolTable table,
                                     Object scope) {
        if (el != null)  {
            for (Object e : el) {
                CodeGenerationHelper.exprHelper(proc, e, table, scope);
            }
        }
    }

    public static void stmtTraverser(LinkedList<Stmt> sl,
                                     CodeFile codeFile,
                                     CodeProcedure proc,
                                     SymbolTable table,
                                     Object scope) {
        if (sl != null) {
            for (Stmt stmt : sl) {
                if (stmt instanceof Assign) {
                    ((Assign) stmt).generateCode(proc, table, scope);
                } else if (stmt instanceof If) {
                    // ((If) stmt).generateCode(codeFile, proc, table, scope);
                } else {
                    stmt.generateCode(proc, table, scope); 
                }
            }            
        }
    }

    // Generates code for all the parameters connected to a CodeProcedure
    public static void paramTraverser(LinkedList<Param> pl,
                                      CodeProcedure proc,
                                      SymbolTable table,
                                      Object scope) {
        if (pl != null) {
            for (Param param : pl) {
                param.generateCode(proc, table, scope);
            }
        }
    }

    // Generates code for all the parameters connected to a CodeStruct
    public static void paramTraverser(LinkedList<Param> pl,
                                      CodeStruct struct) {
        if (pl != null) {
            for (Param param : pl) {
                param.generateCode(struct);
            }
        }
    }

    // Generates code for all the declarations connected to a codeFile
    public static void declTraverser(LinkedList<Decl> dl,
                                     CodeFile codeFile) {
        if (dl != null) {
            for (Decl decl : dl) {
                decl.generateCode(codeFile);
            }            
        }
    }

    public static void declTraverser(LinkedList<Decl> dl,
                                     CodeProcedure proc) {
        if (dl != null) {
            for (Decl decl : dl) {
                // if (decl instanceof VarDecl)
                    decl.generateCode(proc);
            }            
        }
    }

    public static void returnHelper(CodeProcedure proc, SymbolTable table, Object scope) {
        
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

    // public static SymbolTable getTable(Object node) {
    //     return node instanceof Expr
    //         ? ((Expr) node).getTable()
    //         : node instanceof Call
    //         ? ((Call) node).getTable()
    //         : node instanceof Literal
    //         ? ((Literal) node).getTable()
    //         : null;
    // }

    public static void printTable(SymbolTable table) {
        System.out.println(table);
    }

    public static Instruction literalHelper(Literal e) {
        // returns correct instruction when fed a Literal
        if (e.type.equals("int")) {
            return new PUSHINT(Integer.parseInt(e.toString()));
        } else if (e.type.equals("float")) {
            return new PUSHFLOAT(Float.parseFloat(e.toString()));
        } else if (e.type.equals("bool")) {
            return new PUSHBOOL(Boolean.parseBoolean(e.toString()));
        } else if (e.type.equals("string")) {
            // THIS WILL FAIL: get correct string literal 
            return new PUSHSTRING(0);
        } else {
            return new PUSHNULL();
        }
            

    }
}


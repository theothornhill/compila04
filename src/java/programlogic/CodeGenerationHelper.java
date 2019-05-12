import bytecode.*;
import bytecode.type.*;
import bytecode.instructions.*;
import java.util.LinkedList;

public class CodeGenerationHelper {
    public static void exprHelper(CodeProcedure proc,
                                  Object node,
                                  SymbolTable table,
                                  Object scope) throws Exception {
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
            throw new Exception ("Bug in codegenerationhelper");
        
    }

    public static Instruction instructionHelper(CodeProcedure proc, Object op) throws Exception {
        if (op.toString().equals("+"))
            return new ADD();
        else if (op.toString().equals("-"))
            return new SUB();
        else if (op.toString().equals("/"))
            return new DIV();
        else if (op.toString().equals("*"))
            return new MUL();
        else if (op.toString().equals("<"))
            return new LT();
        else if (op.toString().equals("<="))
            return new LTEQ();
        else if (op.toString().equals(">"))
            return new GT();
        else if (op.toString().equals(">="))
            return new GTEQ();
        else if (op.toString().equals("<>"))
            return new NEQ();
        else if (op.toString().equals("||"))
            return new OR();
        else if (op.toString().equals("&&"))
            return new AND();
        else
            throw new Exception("Wrong operand type");
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
            : type.equals("readint")
            ? IntType.TYPE
            : type.equals("printline")
            ? StringType.TYPE
            : type.equals("printstr")
            ? StringType.TYPE
            : returntypeIsUserDefined(type)
            ? StringType.TYPE
            : VoidType.TYPE;    // TODO: Literals can never be void. Fix
    }

    public static CodeProcedure newProc(String name, String type, CodeFile codeFile) throws Exception {
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
                                     Object scope) throws Exception {
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
                                     Object scope) throws Exception {
        // Generalize this completely
        if (sl != null) {
            for (Stmt stmt : sl) {
                if (stmt instanceof Assign) {
                    ((Assign) stmt).generateCode(proc, table, scope);
                } else if (stmt instanceof If) {
                    ((If) stmt).generateCode(proc, table, scope);
                } else if (stmt instanceof While) {
                    ((While) stmt).generateCode(codeFile, proc, table, scope);
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
                                     CodeFile codeFile) throws Exception {
        if (dl != null) {
            for (Decl decl : dl) {
                decl.generateCode(codeFile);
            }            
        }
    }

    public static void declTraverser(LinkedList<Decl> dl,
                                     CodeProcedure proc) throws Exception {
        if (dl != null) {
            for (Decl decl : dl) {
                decl.generateCode(proc);
            }            
        }
    }

    public static void returnHelper(CodeProcedure proc, SymbolTable table, Object scope) {
        
    }

    public static boolean isLibraryProcedure(String name) {
        return TypeCheckHelper.isLibraryProcedure(name);
    }

    public static void printTable(SymbolTable table) {
        System.out.println(table);
    }

    public static Instruction literalHelper(Literal e, CodeProcedure proc) {
        // returns correct instruction when fed a Literal
        if (e.type.equals("int")) {
            return new PUSHINT(Integer.parseInt(e.toString()));
        } else if (e.type.equals("float")) {
            return new PUSHFLOAT(Float.parseFloat(e.toString()));
        } else if (e.type.equals("bool")) {
            return new PUSHBOOL(Boolean.parseBoolean(e.toString()));
        } else if (e.type.equals("string")) {
            int constant = proc.getCodeFile().addStringConstant(e.toString());
            return new PUSHSTRING(constant);
        } else {
            return new PUSHNULL();
        }
    }


    public static void generateRecordGetField(Object ex, CodeProcedure proc,
                                              SymbolTable table, Object scope) {
        String varName = "";
        String fieldName = "";
        String type = "";
        if (ex instanceof Var) {
            varName = ((Var)ex).expr.toString();
            fieldName = ((Var)ex).toString();
            if (table.lookup(scope, varName) instanceof Param)
                type = ((Param)table.lookup(scope, varName)).type.toString();
            else 
                type = ((VarDecl)table.lookup(scope, varName)).type.toString();
        }
        proc.addInstruction(new LOADLOCAL(proc.variableNumber(varName)));
        proc.addInstruction(new GETFIELD(proc.fieldNumber(type, fieldName),
                                         proc.structNumber(type)));        
    }
    
    public static boolean returntypeIsUserDefined(String type) {
        return !(type.equals("string") ||
                 type.equals("float")  ||
                 type.equals("int")    ||
                 type.equals("bool")   ||
                 type.equals("null"));
    }
}


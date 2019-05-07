import bytecode.*;
import bytecode.type.*;
import bytecode.instructions.*;

public class BinaryExpr extends Expr {
    Object e1;
    Object e2;
    Object op;
    Instruction operator;
    // Booleans for type-checker. In example we need our type checker to know
    // when expressions are boolean
    public boolean isLogical;
    public boolean isArit;
    public boolean isBoolean;

    public BinaryExpr(Object e1, Object op, Object e2) throws Exception {
        this.e1 = e1;
        this.op = op;
        this.e2 = e2;
        setOperationType(op.toString());
        // System.out.println(((Literal)e1).type);
        // System.out.println(((Var)e2).type);
        setExprType(((Expr)e1).type.toString(),
                    ((Expr)e2).type.toString());
    }

    // This is not proper type checking. Think through this.
    public void setOperationType(String op) {
        if (op.equals("||") || op.equals("&&"))
            isLogical = true;
        else if (op.equals("<")
                 || op.equals("<=")
                 || op.equals(">")
                 || op.equals(">=")
                 || op.equals("=")
                 || op.equals("<>"))
            isBoolean = true;
        else
            isArit = true;
    }

    public void setExprType(String e1type, String e2type) throws Exception {
        // System.out.println(e1type);
        if (e1type == "int" && e2type == "int")
            this.type = new Type("int");
        else if (e1type == "int" && e2type == "float" ||
            e1type == "float" && e2type == "int" ||
            e1type == "float" && e2type == "float")
            this.type = new Type("float");
        else
            throw new Exception("Arguments of operation not correct type");

    }

    public void typeCheck() throws Exception {
        if (!((Expr)e1).type.equals(((Expr)e2).type))
            throw new Exception("Operands in binary expr not the same type");
        if (e1 instanceof Var && e2 instanceof Var) {
            Var v1 = (Var)e1;
            Object e = table.lookup(this, v1.name);
            Var v2 = (Var)e2;
            Object ee = table.lookup(this, v2.name);
            if (e == null || ee == null)
                throw new Exception("Variable not declared");
        }
    }

    public void typeCheck(SymbolTable table, Object scope) throws Exception {

    }

    public void addToSymbolTable(SymbolTable table) {
        // table.insert("Name", this.getClass());
        // table.insert("Operation", op);
        // table.insert("Expr1", CodeGenerationHelper.getTable(e1));
        // table.insert("Expr2", CodeGenerationHelper.getTable(e2));
    }

    public void generateCode(CodeProcedure proc) {
        CodeGenerationHelper.exprHelper(proc, e1);
        proc.addInstruction(CodeGenerationHelper.instructionHelper(proc, op));
        CodeGenerationHelper.exprHelper(proc, e2);
    }

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(BINARY_OPERATION " + op.toString());
        sb.append(PrintHelper.newlineAndIndentWithHelper(e1, indentLevel+1));
        sb.append(PrintHelper.newlineAndIndentWithHelper(e2, indentLevel+1));
        sb.append(PrintHelper.endWithParen(indentLevel));
        return sb.toString();
    }
}

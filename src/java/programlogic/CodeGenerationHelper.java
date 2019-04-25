import bytecode.*;
import bytecode.type.*;
import bytecode.instructions.*;

// The idea of this helper class is to act like a factory for exprs. The problem
// with the expressions is that they can be a statement or expression. Therefore
// I need an elegant way to assign the values. 
public class CodeGenerationHelper {
    public static void exprHelper(CodeProcedure proc, Object node) {
        if (node instanceof Expr)
            ((Expr) node).generateCode(proc);
        // else if (node instanceof Call)
        //     ((Call) node).generateCode(proc);
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
        // Make this safer, don't use equals, and also dont default to
        // multiplication, wtf is that :P
        return op.toString().equals("+")
            ? new ADD()
            : op.toString().equals("-")
            ? new SUB()
            : op.toString().equals("/")
            ? new DIV()
            // : op.toString().equals("*")
            : new MUL();
    }

}

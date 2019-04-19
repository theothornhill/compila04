import bytecode.*;
import bytecode.type.*;
import bytecode.instructions.*;

public class CodeGenerationHelper {
    public static void exprHelper(CodeProcedure proc, Object node) {
        if (node instanceof BinaryExpr)
            ((BinaryExpr) node).generateCode(proc);
        // else if (node instanceof Call)
        //     ((Call) node).generateCode(proc);
        else if (node instanceof Literal)
            ((Literal) node).generateCode(proc);
        else
            System.out.println("Bug in Codegenerationhelper");
    }
}

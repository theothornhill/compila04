import bytecode.*;
import bytecode.type.*;

public class New extends Expr {
    String name;
    public New(String name) {
        this.name = name;
    }

    public void generateCode(CodeFile codeFile) {
        
    }

    public void generateCode(CodeProcedure proc) {
        
    }

    public String printAst(int indentLevel) {
        return "(NEW " + PrintHelper.astHelper(name, indentLevel) + ")";
    }
}

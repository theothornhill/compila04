import bytecode.*;
import bytecode.type.*;

public class VarDecl extends Decl {
    Type type;
    CodeType t;
    
    public VarDecl(String name, Type type) {
        this.name = name;
        this.type = type;
        this.t = type.setCodeType(type.toString());
    }

    public void generateCode(CodeFile codeFile) {
        codeFile.addVariable(this.name);
        codeFile.updateVariable(this.name, t);
    }
    
    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(VAR ");
        sb.append(type.printAst(indentLevel+1));
        sb.append(PrintHelper.printName(name));
        sb.append(")");
        return sb.toString();
    }
}

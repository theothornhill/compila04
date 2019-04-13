import bytecode.*;

public class VarDecl extends Decl {
    Type type;
    public VarDecl(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public void generateCode(CodeFile codeFile) {
        
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

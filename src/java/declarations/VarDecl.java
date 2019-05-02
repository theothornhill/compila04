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

    public void addToSymbolTable(SymbolTable table, int scope) {
        table.insert(scope, name);
        table.insert(scope, type);
    }

    // Adds a global variable. This is because vardecl when called from Program
    // adds directly to the codefiles namespace.
    public void generateCode(CodeFile codeFile) {
        codeFile.addVariable(this.name);
        codeFile.updateVariable(this.name, t);
    }

    // This is a local variable for the procedure. VarDecls are allowed in
    // Programs and ProcDecls, but only procedures need the local variables
    public void generateCode(CodeProcedure proc) {
        proc.addLocalVariable(this.name, this.t);
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

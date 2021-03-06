import bytecode.*;
import bytecode.type.*;

public class VarDecl extends Decl {
    CodeType t;
    
    public VarDecl(String name, Type type) {
        this.name = name;
        this.type = type;
        this.t = type.setCodeType(type.toString());
    }

    public void typeChecker(SymbolTable table) throws Exception {
        if (table.lookup(this.getCreatedBy(), this.name) != null)
            throw new Exception("Symbol " + this.name + " already declared");
    }

    public void typeCheck() throws Exception {
    }


    public Object getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Object node) {
        this.createdBy = node;
    }

    public void setCreatorOf() {
        type.setCreatedBy(this);
    }

    public void addToSymbolTable() throws Exception {
        table.insert(name);
        table.insert(type);
    }

    public void generateCode(CodeFile codeFile) {
        codeFile.addVariable(this.name);
        codeFile.updateVariable(this.name, t);
    }

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

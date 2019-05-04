import bytecode.*;
import bytecode.type.*;

public class Assign extends Stmt {
    public Assign(Object name, Object e) {
        this.e2 = name;
        this.e = e;
    }

    public void typeCheck() {
    }

    public Object getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Object node) {
        this.createdBy = node;
    }

    public void setCreatorOf() {
        // Does not do anything yet
    }

    public void setLexicalScopeLevel(int scope) {
        this.lexicalScopeLevel = scope;
    }

    public void addToSymbolTable(SymbolTable table) {
        table.insert("assign");
        table.insert(name);
        table.insert(e);
    }

    public void generateCode(CodeFile codeFile) {
        codeFile.addStringConstant("Added constant here asshole");
    }

    public void generateCode(CodeProcedure proc) {

    }

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(ASSIGN_STMT");
        sb.append(PrintHelper.newlineAndIndentWithHelper(e2, indentLevel+1));
        sb.append(PrintHelper.newlineAndIndentWithHelper(e, indentLevel+1));
        sb.append(PrintHelper.endWithParen(indentLevel));
        return sb.toString();
    }
}

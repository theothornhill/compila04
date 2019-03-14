import java.util.*;

public class RecDecl extends Decl {
    LinkedList<Param> pl;
    public RecDecl(String name, LinkedList<Param> pl) {
        this.name = name;
        this.pl = pl;
    }

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(STRUCT");
        sb.append(PrintHelper.printName(name));
        if (pl != null) {
            for (Param param : pl) {
                sb.append(PrintHelper.printParam(param, indentLevel+1));
            }            
        }
        sb.append(PrintHelper.endWithParen(indentLevel));
        return sb.toString();
    }
}

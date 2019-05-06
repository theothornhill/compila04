import bytecode.type.*;
import bytecode.*;

public class Type implements AttributeGrammar {
    String type;
    Type t;
    CodeType codetype;
    Object createdBy;
    int lexicalScopeLevel;

    public Type(String type) {
        this.type = type;
        // codetype = setCodeType(type);
    }

    public Type(Type t) {
        this.t = t;
        // codetype = setCodeType(type.toString());
    }

    public Object getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Object node) {
        this.createdBy = node;
    }

    public void setCreatorOf() {
        // This does not create anything
    }

    public void setLexicalScopeLevel(int scope) {
        this.lexicalScopeLevel = scope;
    }

    public String toString() {
        return this.type;
    }

    // public CodeType setCodeType(String type) {
    //     if (type.equals("float")) {
    //         return FloatType.TYPE;
    //     } else if (type.equals("int")) {
    //         return IntType.TYPE;
    //     } else if (type.equals("void")) {
    //         return VoidType.TYPE;            
    //     } else if (type.equals("bool")) {
    //         return BoolType.TYPE;
    //     } else if (type.equals("string")) {
    //         return StringType.TYPE;
    //     }
    //     return VoidType.TYPE; // TODO: not good, handle correctly

    // }

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(TYPE ");
        sb.append(type);
        sb.append(")");
        return sb.toString();
    }

    public boolean equals(String t) {
        return this.type.toString().equals(t) ? true : false;
    }

}

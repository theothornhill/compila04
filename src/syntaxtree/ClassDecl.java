package syntaxtree;  // this is just an inspirational example, probably 
                     // there will be no class ceclaration class, as 
                     // compila 19 does not support class declarations.
public class ClassDecl {

    String name;
    
    public ClassDecl (String name) {
        this.name = name;
    }

    public String printAst() {
        return "(CLASS_DECL (NAME " + name + "))";
    }
}

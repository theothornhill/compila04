public class Param {
    Type type;
    String name;
    public Param(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String printAst(int indentLevel) {
        return "(PARAM_DECL "
            + type.printAst(indentLevel)
            + " (NAME " + name +"))";
    }
}

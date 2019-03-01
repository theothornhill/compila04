public class Param {
    Object type;
    String name;
    public Param(String name, Object type) {
        this.name = name;
        this.type = type;
    }

    public String printAst(int indentLevel) {
        return "(PARAM " + name + " TYPE " + type + ")";
    }
}

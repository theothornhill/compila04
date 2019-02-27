public class Param {
    String type;
    String name;
    public Param(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String printAst(int indentLevel) {
        return "(PARAM " + name + " TYPE " + type + ")";
    }
}

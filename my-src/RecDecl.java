public class RecDecl extends Decl {
    public RecDecl(String name) {
        this.name = name;
    }

    public String toString() {
        return "(RECORD " + name + ")";
    }
}

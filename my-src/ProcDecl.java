public class ProcDecl extends Decl {
    public ProcDecl(String name) {
        this.name = name;
    }

    public String toString() {
        return "(PROCEDURE " + name + ")";
    }
}

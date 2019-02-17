import java_cup.runtime.*;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            parser p = new parser(new Lexer(new FileReader(new File("program"))));
            try {
                p.parse();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

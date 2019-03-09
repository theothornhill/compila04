import java_cup.runtime.*;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Lexer lex = null;
        parser parser = null;
        try {
            lex = new Lexer(new FileReader(args[0]));
            parser = new parser(lex);
            try {
                parser.parse();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}



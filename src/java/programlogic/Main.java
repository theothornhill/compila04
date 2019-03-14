import java_cup.runtime.*;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Lexer lex = null;
        parser parser = null;
        Symbol returnSymbol;
        // Try to parse and return the Program as a Program
        try {
            lex = new Lexer(new FileReader(args[0]));
            parser = new parser(lex);
            try {
                returnSymbol = parser.parse();
                Program program = (Program)returnSymbol.value;
                System.out.println(program.printAst());
            } catch (Exception ee) {
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}



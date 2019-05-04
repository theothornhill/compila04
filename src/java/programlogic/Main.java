import java_cup.runtime.*;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.*;

import bytecode.*;
import bytecode.type.*;
import bytecode.instructions.*;
import runtime.*;


public class Main {
    public static void main(String[] args) throws IOException {
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
                // Add some custom things after parsing - in particular main-method
                CodeFile codeFile = new CodeFile();

                // program.generateCode(codeFile);
                // program.generateCode();
                // program.printTable();

                codeFile.setMain("Main"); // This needs to go somewhere else?
                
                byte[] bytecode = codeFile.getBytecode();

                DataOutputStream stream = new DataOutputStream(new FileOutputStream("/Users/theodor/Dropbox/Studier/fag/INF5110/compila04/example.bin"));
                stream.write(bytecode);
                stream.close();
                // System.out.println(program.printAst());
                VirtualMachine vm = new VirtualMachine("/Users/theodor/Dropbox/Studier/fag/INF5110/compila04/example.bin");
                // vm.list();

            } catch (Exception ee) {
                ee.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}



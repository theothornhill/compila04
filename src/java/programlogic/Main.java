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

    public static Program compile(String[] args) throws Exception {
        Lexer lex = new Lexer(new FileReader(args[0]));
        parser parser = new parser(lex);
        Symbol returnSymbol;
        Program program = null;
        int currentLevel = 0;
        try {
            program = (Program)parser.parse().value;            
        } catch (Exception e) {
            e.printStackTrace();
        }
        program.addToSymbolTable();
        program.setCreatorOf();
        program.setLexicalScopeLevel(currentLevel+1);
        program.typeCheck();   
        return program;
    }

    public static void main(String[] args) throws IOException {
        try {
            // Add some custom things after parsing - in particular main-method
            CodeFile codeFile = new CodeFile();
            Program program = compile(args);
            program.generateCode(codeFile);

            byte[] bytecode = codeFile.getBytecode();

            DataOutputStream stream = new DataOutputStream(new FileOutputStream("/Users/theodor/Dropbox/Studier/fag/INF5110/compila04/example.bin"));
            stream.write(bytecode);
            stream.close();
            VirtualMachine vm = new VirtualMachine("/Users/theodor/Dropbox/Studier/fag/INF5110/compila04/example.bin");
            vm.list();
            vm.run();
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }
}

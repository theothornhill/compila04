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


public class Compiler {
    private String inFilename = null;
    private String binFilename = null;
    private String runList = null;

    public String syntaxError;
    public String error;
    public Program program;
    public CodeFile codeFile;
    public String[] args;
    
    public Compiler(String inFilename, String binFilename, String runList) {
        this.inFilename = inFilename;
        this.binFilename = binFilename;
        this.runList = runList;
    }

    public int compile(String[] args) throws Exception {
        Lexer lex = new Lexer(new FileReader(inFilename));
        parser parser = new parser(lex);
        program = null;
        codeFile = new CodeFile();
        try {
            program = (Program)parser.parse().value;            
        } catch (Exception e) {
            // throw e;
            syntaxError = e.getMessage();
            return 1;
        }

        try {
            program.addToSymbolTable();
            program.setCreatorOf();
            program.typeCheck();               

        } catch (Exception e) {
            error = e.getMessage();
            return 2;
        }
        generateCode(program, codeFile);
        return 0;
    }

    public void generateCode(Program program, CodeFile codeFile) throws Exception {
        program.generateCode(codeFile);
        byte[] bytecode = codeFile.getBytecode();
        
        DataOutputStream stream = new DataOutputStream(new FileOutputStream(binFilename));
        stream.write(bytecode);
        stream.close();
        VirtualMachine vm = new VirtualMachine(binFilename);
        if (!runList.equals("null")) {
            vm.list();            
        } else {
            vm.run();                    
        }
    }

    public static void main(String[] args) throws Exception {
        Compiler compiler = new Compiler(args[0], args[1], args[2]);
        int result;
        try {
            result = compiler.compile(args);
            if (result == 1) {
                System.out.println(compiler.syntaxError);
            } else if (result == 2) {
                System.out.println(compiler.error);
            }
            System.out.println("\n" + result);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

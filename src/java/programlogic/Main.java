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

            // // Testing code generation
            // CodeFile codeFile = new CodeFile();
            // codeFile.addProcedure("print_float");
            // // I:
            // codeFile.addProcedure("Main");
            // codeFile.addProcedure("test");
            // codeFile.addStruct("Complex");
            // // II:
            // CodeProcedure main = new CodeProcedure("Main", VoidType.TYPE,
            //                                        codeFile);
            // main.addInstruction(new RETURN());
            // codeFile.updateProcedure(main);

            // // IV:
            // CodeProcedure test = new CodeProcedure("test", VoidType.TYPE,
            //                                        codeFile);
            // test.addParameter("firstPar", FloatType.TYPE);
            // test.addParameter("secondPar", FloatType.TYPE);
            // test.addInstruction(new LOADLOCAL(test.variableNumber("firstPar")));
            // test.addInstruction(new CALL(test.procedureNumber("print_float")));
            // test.addInstruction(new RETURN());
            // codeFile.updateProcedure(test);

            //     // V:
            // CodeStruct complex = new CodeStruct("Complex");
            // complex.addVariable("Real", FloatType.TYPE);
            // complex.addVariable("Imag", FloatType.TYPE);
            // codeFile.updateStruct(complex);
            // // VI:
            // CodeProcedure printFloat = new CodeProcedure("print_float",
            //                                              VoidType.TYPE, codeFile);
            // test.addParameter("f", FloatType.TYPE);
            // codeFile.updateProcedure(printFloat);
            // // VII:
            // codeFile.setMain("Main");
            // byte[] bytecode = codeFile.getBytecode();

            // DataOutputStream stream = new DataOutputStream(new FileOutputStream("/Users/theodor/Dropbox/Studier/fag/INF5110/compila04/example.bin"));
            // stream.write(bytecode);
            // stream.close();
            // end of test for code generation
            try {
                returnSymbol = parser.parse();
                Program program = (Program)returnSymbol.value;
                // Add some custom things after parsing - in particular main-method
                CodeFile codeFile = new CodeFile();

                program.generateCode(codeFile);

                codeFile.setMain("Main"); // This needs to go somewhere else?
                
                byte[] bytecode = codeFile.getBytecode();

                DataOutputStream stream = new DataOutputStream(new FileOutputStream("/Users/theodor/Dropbox/Studier/fag/INF5110/compila04/example.bin"));
                stream.write(bytecode);
                stream.close();
                System.out.println(program.printAst());
                VirtualMachine vm = new VirtualMachine("/Users/theodor/Dropbox/Studier/fag/INF5110/compila04/example.bin");
                vm.list();
            } catch (Exception ee) {
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}



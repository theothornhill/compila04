init:
		java -jar java-cup-11b.jar compila.cup
		jflex compila.lex
		javac -cp java-cup-11b.jar parser.java Lexer.java sym.java 
		java -cp java-cup-11b-runtime.jar Main.java
		java -cp java-cup-11b-runtime.jar Lexer.java

clean:
		rm parser.java
		rm Lexer.java
		rm sym.java
		rm *.class

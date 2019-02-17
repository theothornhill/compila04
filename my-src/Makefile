init:
		java -jar java-cup-11b.jar compila.cup
		jflex compila.lex
		javac -cp java-cup-11b-runtime.jar Lexer.java sym.java parser.java
		javac Main.java
		java Main

clean:
		rm parser.java
		rm Lexer.java
		rm sym.java
		rm *.class

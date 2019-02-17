init:
		java -cp .:java-cup-11b.jar -jar java-cup-11b.jar compila.cup
		jflex compila.lex
		javac -cp .:java-cup-11b-runtime.jar parser.java
		javac -cp .:java-cup-11b-runtime.jar Lexer.java
		javac -cp .:java-cup-11b-runtime.jar Main.java
		java Main

clean:
		rm parser.java
		rm Lexer.java
		rm sym.java

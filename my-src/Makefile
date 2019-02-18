init:
		jflex compila.lex
		java -jar java-cup-11b.jar compila.cup
		javac -cp .:java-cup-11b.jar parser.java Lexer.java sym.java Program.java
		javac -cp .:java-cup-11b.jar Main.java
		java -cp .:java-cup-11b.jar Main compilatest.txt
clean:
		rm parser.java
		rm Lexer.java
		rm sym.java
		rm *.class

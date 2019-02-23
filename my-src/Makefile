init:
		jflex compila.lex
		java -jar java-cup-11b.jar compila.cup
		javac -cp .:java-cup-11b.jar parser.java Lexer.java sym.java Program.java
		javac -cp .:java-cup-11b.jar Main.java
		java -cp .:java-cup-11b.jar Main compilatest.txt
		java -cp .:java-cup-11b.jar Main ../src/tests/parsing/test01.cmp
		java -cp .:java-cup-11b.jar Main ../src/tests/parsing/test02.cmp
		java -cp .:java-cup-11b.jar Main ../src/tests/parsing/test03.cmp
		java -cp .:java-cup-11b.jar Main ../src/tests/parsing/test04.cmp
		java -cp .:java-cup-11b.jar Main ../src/tests/parsing/test05.cmp
#		java -cp .:java-cup-11b.jar Main ../src/tests/parsing/test06.cmp
#		java -cp .:java-cup-11b.jar Main ../src/tests/parsing/test07.cmp
		java -cp .:java-cup-11b.jar Main ../src/tests/parsing/test08.cmp

clean:
		rm parser.java
		rm Lexer.java
		rm sym.java
		rm *.class

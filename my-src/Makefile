
init:
		jflex cuplex/compila.lex -d java
		java -jar lib/java-cup-11b.jar -destdir java cuplex/compila.cup
#		java -jar lib/java-cup-11b.jar -destdir java cuplex/compila-nonambiguous.cup
		javac -cp .:lib/java-cup-11b.jar java/*.java -d classes
#		java -cp .:lib/java-cup-11b.jar:classes Main testfiles/compilatest.txt
#		java -cp .:lib/java-cup-11b.jar:classes Main ../src/tests/parsing/test01.cmp
#		java -cp .:lib/java-cup-11b.jar:classes Main ../src/tests/parsing/test02.cmp
#		java -cp .:lib/java-cup-11b.jar:classes Main ../src/tests/parsing/test03.cmp
#		java -cp .:lib/java-cup-11b.jar:classes Main ../src/tests/parsing/test04.cmp
#		java -cp .:lib/java-cup-11b.jar:classes Main ../src/tests/parsing/test05.cmp
#		java -cp .:lib/java-cup-11b.jar:classes Main ../src/tests/parsing/test06.cmp
#		java -cp .:lib/java-cup-11b.jar:classes Main ../src/tests/parsing/test07.cmp
#		java -cp .:lib/java-cup-11b.jar:classes Main ../src/tests/parsing/test08.cmp
		java -cp .:lib/java-cup-11b.jar:classes Main ../material/sample-compila-ast/complexaddition.cmp
clean:
		rm parser.java
		rm Lexer.java
		rm sym.java
		rm *.class

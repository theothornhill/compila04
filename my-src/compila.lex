import java_cup.runtime.*;

%%
%class Lexer
%unicode
%cup
%line
%column

 /* Whitespaces */
LineTerminator  = \n|\r|\r\n
WhiteSpace      = \s

/* Identifiers */
NAME            = [:jletter:][:jletterdigit:]*
INT_LITERAL     = [0-9]+
FLOAT_LITERAL   = [0-9]+\.[0-9]+
STRING_LITERAL  = \"([^\\\"]|\\.)*\"

/* Comments */
Comment         = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}
TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment     = "//" [^\r\n]* {LineTerminator}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/\*] )*

%%
{LineTerminator}          { /* Ignore*/}
{WhiteSpace}              { /* Ignore*/}

/* Keywords*/
<YYINITIAL> { 
/* initial */
  "program"               { System.out.printf("found program\n"); }
  "end"                   { System.out.printf("found end\n"); }
/* declarations */
  "struct"                 { System.out.printf("found struct\n"); }
  "proc"                 { System.out.printf("found proc\n"); }
  "var"                 { System.out.printf("found var\n"); }
  "new"                 { System.out.printf("found new keyword\n"); }
/* program logic */
  "if"                   { System.out.printf("found if\n"); }
  "fi"                   { System.out.printf("found if\n"); }
  "while"                   { System.out.printf("found while\n"); }
  "return"                   { System.out.printf("found return\n"); }
  "ref"                   { System.out.printf("found reference\n"); }
  "deref"                   { System.out.printf("found dereference\n"); }             
}

 /* Types and identifiers */
<YYINITIAL> {
 {NAME}_                 { System.out.printf("Error on line %s\n", yyline+1); System.exit(1); }
 {NAME}                  { System.out.printf("found name: %s\n", yytext()); }
 {INT_LITERAL}"."          { System.out.printf("Error on line %s\n", yyline+1); System.exit(1); }
 {INT_LITERAL}          { System.out.printf("found int: %s\n", yytext()); }
 {STRING_LITERAL}        { System.out.printf("found string: %s\n", yytext()); }
 {FLOAT_LITERAL}         { System.out.printf("found float: %s\n", yytext()); }
}

/* Comments */
{Comment}               { System.out.printf("comment\n"); }              
<YYINITIAL> {
  /* Relational Operators */
  "<"|">"|">="|"<="|"="|"<>" { System.out.printf("Relational operator: %s\n", yytext());}
/* Arithmetic Operators */
  "+"|"-"|"*"|"/"|"^"        { System.out.printf("Arithmetic operators: %s\n", yytext());}
/* Logical Operators */
  "&&"|"||"        { System.out.printf("Logical operators: %s\n", yytext());}
/* Dot operator*/
  "."              { System.out.printf("Dot operator\n"); }
/* Parameter identificator */
  ":"                     { System.out.printf("found parameter identificator"); }
}

/* Catch all */
.                       {  }

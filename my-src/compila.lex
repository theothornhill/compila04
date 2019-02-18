import java_cup.runtime.*;

%%
%class Lexer
%unicode
%cup
%line
%column
%eofval{
  return new Symbol(sym.EOF);
%eofval}
%eof{
  System.out.println("Reached end of file!");
%eof}
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
             "program"               { return new Symbol(sym.PROGRAM); }
             "begin"                 { return new Symbol(sym.BEGIN); }
             "end"                   { return new Symbol(sym.END); }
             /* declarations */
             "struct"                { return new Symbol(sym.STRUCT); }
             "proc"                  { return new Symbol(sym.PROC); }
             "var"                   { return new Symbol(sym.VAR); }
             "new"                   { return new Symbol(sym.NEW); }
             /* program logic */
             "if"                    { return new Symbol(sym.IF); }
             "fi"                    { return new Symbol(sym.FI); }
             "while"                 { return new Symbol(sym.WHILE); }
             "return"                { return new Symbol(sym.RETURN); }
             "ref"                   { return new Symbol(sym.REF); }
             "deref"                 { return new Symbol(sym.DEREF); }
}

 /* Types and identifiers */
<YYINITIAL> {
             {NAME}_                 { System.exit(4763); }
             {NAME}                  { return new Symbol(sym.NAME, yytext()); }
             {INT_LITERAL}"."        { System.exit(4763); }
             {INT_LITERAL}           { return new Symbol(sym.INT_LITERAL); }
             {STRING_LITERAL}        { return new Symbol(sym.STRING_LITERAL); }
             {FLOAT_LITERAL}         { return new Symbol(sym.FLOAT_LITERAL); }
}

/* Comments */
{Comment}                               { return new Symbol(sym.COMMENT); }
<YYINITIAL> {
             /* Relational Operators */
             "<"|">"|">="|"<="|"="|"<>" { return new Symbol(sym.RELOP); }
             /* Arithmetic Operators */
             "+"|"-"|"*"|"/"|"^"        { return new Symbol(sym.ARIT); }
             /* Logical Operators */
             "&&"|"||"                  { return new Symbol(sym.LOGIC); } 
             /* Dot operator*/
             "."                        { return new Symbol(sym.DOT); }
             /* Parameter identificator */
             ":"                        { return new Symbol(sym.PARAM_ID); }
}

/* Catch all */
.                                       {  }

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
{LineTerminator}          { /* Ignore */}
{WhiteSpace}              { /* Ignore */}
{Comment}                 { /* Ignore */}

/* Keywords*/
<YYINITIAL> { 
             /* initial */
             "program"               { return new Symbol(sym.PROGRAM); }
             "begin"                 { return new Symbol(sym.BEGIN); }
             "in"                    { return new Symbol(sym.IN); }             
             "end"                   { return new Symbol(sym.END); }
             /* declarations */
             "record"                { return new Symbol(sym.REC_DECL); }
             "proc"                  { return new Symbol(sym.PROC_DECL); }
             "var"                   { return new Symbol(sym.VAR_DECL); }
             "new"                   { return new Symbol(sym.NEW); }
             /* program logic */
             "if"                    { return new Symbol(sym.IF); }
             "fi"                    { return new Symbol(sym.FI); }
             "then"                  { return new Symbol(sym.THEN); }
             "else"                  { return new Symbol(sym.ELSE); }
             "while"                 { return new Symbol(sym.WHILE); }
             "do"                    { return new Symbol(sym.DO); }
             "od"                    { return new Symbol(sym.OD); }
             "return"                { return new Symbol(sym.RETURN); }
             "ref"                   { return new Symbol(sym.REF); }
             "deref"                 { return new Symbol(sym.DEREF); }
             /* Negation */
             "not"                   { return new Symbol(sym.NOT); }
             "true"                  { return new Symbol(sym.TRUE); }
             "false"                 { return new Symbol(sym.FALSE); }
             "null"                  { return new Symbol(sym.NULL); }
             /* types */
             "float"                 { return new Symbol(sym.FLOAT); }
             "int"                   { return new Symbol(sym.INT); }             
             "string"                { return new Symbol(sym.STRING); }
             "bool"                  { return new Symbol(sym.BOOL); }             
}

<YYINITIAL> {
             /* Relational Operators */
             "<"                        { return new Symbol(sym.LT); }
             "<="                       { return new Symbol(sym.LTEQ); }
             ">"                        { return new Symbol(sym.GT); }
             ">="                       { return new Symbol(sym.GTEQ); }
             "="                        { return new Symbol(sym.EQ); }
             "<>"                       { return new Symbol(sym.NEQ); }
             /* Arithmetic Operators */
             "+"                        { return new Symbol(sym.ADD); }
             "-"                        { return new Symbol(sym.MINUS); }
             "*"                        { return new Symbol(sym.MULT); }
             "/"                        { return new Symbol(sym.DIVIDE); }
             "^"                        { return new Symbol(sym.EXPONENT); }
             /* Logical Operators */
             "&&"                       { return new Symbol(sym.AND); }
             "||"                       { return new Symbol(sym.OR); } 
             /* Dot operator*/
             "."                        { return new Symbol(sym.DOT); }
             /* Assignment operator */  
             ":="                       { return new Symbol(sym.ASSIGN_OP); }
             /* Parameter identificator */
             ":"                        { return new Symbol(sym.PARAM_ID); }
             ";"                        { return new Symbol(sym.SEMI); }
             ","                        { return new Symbol(sym.COMMA); }
             /*             "()"|"("{WhiteSpace}")"    { return new Symbol(sym.EMPTY); }*/
             "("                        { return new Symbol(sym.LPAR); }
             ")"                        { return new Symbol(sym.RPAR); }
             "{"                        { return new Symbol(sym.LCURLY); }
             "}"                        { return new Symbol(sym.RCURLY); }             
}

 /* Types and identifiers */
<YYINITIAL> {
             {NAME}_                 { System.exit(4763); }
             {NAME}                  { return new Symbol(sym.NAME, yytext()); }
             {INT_LITERAL}"."        { System.exit(4763); }
             {INT_LITERAL}           { return new Symbol(sym.INT_LITERAL, yytext()); }
             {STRING_LITERAL}        { return new Symbol(sym.STRING_LITERAL, yytext()); }
             {FLOAT_LITERAL}         { return new Symbol(sym.FLOAT_LITERAL, yytext()); }
}

/* Catch all */
.                                       {  }

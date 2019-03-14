import java_cup.runtime.*;

%%
%class Lexer
%unicode
%cup
%line
%column

%{
  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }

  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }
%}

%eofval{
  return new Symbol(sym.EOF);
%eofval}

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
             "program"               { return symbol(sym.PROGRAM, yytext()); }
             "begin"                 { return symbol(sym.BEGIN, yytext()); }
             "in"                    { return symbol(sym.IN, yytext()); }             
             "end"                   { return symbol(sym.END, yytext()); }
             /* declarations */
             "struct"                { return symbol(sym.REC_DECL, yytext()); }
             "proc"                  { return symbol(sym.PROC_DECL, yytext()); }
             "var"                   { return symbol(sym.VAR_DECL, yytext()); }
             "new"                   { return symbol(sym.NEW, yytext()); }
             /* program logic */
             "if"                    { return symbol(sym.IF, yytext()); }
             "fi"                    { return symbol(sym.FI, yytext()); }
             "then"                  { return symbol(sym.THEN, yytext()); }
             "else"                  { return symbol(sym.ELSE, yytext()); }
             "while"                 { return symbol(sym.WHILE, yytext()); }
             "do"                    { return symbol(sym.DO, yytext()); }
             "od"                    { return symbol(sym.OD, yytext()); }
             "return"                { return symbol(sym.RETURN, yytext()); }
             "ref"                   { return symbol(sym.REF, yytext()); }
             "deref"                 { return symbol(sym.DEREF, yytext()); }
             /* Negation */
             "not"                   { return symbol(sym.NOT, yytext()); }
             "true"                  { return symbol(sym.TRUE, yytext()); }
             "false"                 { return symbol(sym.FALSE, yytext()); }
             "null"                  { return symbol(sym.NULL, yytext()); }
             /* types */
             "float"                 { return symbol(sym.FLOAT, yytext()); }
             "int"                   { return symbol(sym.INT, yytext()); }             
             "string"                { return symbol(sym.STRING, yytext()); }
             "bool"                  { return symbol(sym.BOOL, yytext()); }
}

<YYINITIAL> {
             /* Relational Operators */
             "<"                        { return symbol(sym.LT, yytext()); }
             "<="                       { return symbol(sym.LTEQ, yytext()); }
             ">"                        { return symbol(sym.GT, yytext()); }
             ">="                       { return symbol(sym.GTEQ, yytext()); }
             "="                        { return symbol(sym.EQ, yytext()); }
             "<>"                       { return symbol(sym.NEQ, yytext()); }
             /* Arithmetic Operators */
             "+"                        { return symbol(sym.ADD, yytext()); }
             "-"                        { return symbol(sym.MINUS, yytext()); }
             "*"                        { return symbol(sym.MULT, yytext()); }
             "/"                        { return symbol(sym.DIVIDE, yytext()); }
             "^"                        { return symbol(sym.EXPONENT, yytext()); }
             /* Logical Operators */
             "&&"                       { return symbol(sym.AND, yytext()); }
             "||"                       { return symbol(sym.OR, yytext()); } 
             /* Dot operator*/
             "."                        { return symbol(sym.DOT, yytext()); }
             /* Assignment operator */  
             ":="                       { return symbol(sym.ASSIGN_OP, yytext()); }
             /* Parameter identificator */
             ":"                        { return symbol(sym.COLON, yytext()); }
             ";"                        { return symbol(sym.SEMI, yytext()); }
             ","                        { return symbol(sym.COMMA, yytext()); }
             "("                        { return symbol(sym.LPAR, yytext()); }
             ")"                        { return symbol(sym.RPAR, yytext()); }
             "{"                        { return symbol(sym.LCURLY, yytext()); }
             "}"                        { return symbol(sym.RCURLY, yytext()); }             
}

 /* Types and identifiers */
<YYINITIAL> {
             {NAME}_                 { System.out.printf("Syntax error on line: %s, [%s]\n", 
                                                         yyline, yytext());
                                      System.exit(4763); }
             {NAME}                  { return symbol(sym.NAME, yytext()); }
             {INT_LITERAL}"."        { System.out.printf("Syntax error on line: %s, [%s]\n", 
                                                         yyline, yytext());
                                      System.exit(4763); }
             {INT_LITERAL}           { return symbol(sym.INT_LITERAL, yytext()); }
             {STRING_LITERAL}        { return symbol(sym.STRING_LITERAL, yytext()); }
             {FLOAT_LITERAL}         { return symbol(sym.FLOAT_LITERAL, yytext()); }
}

/* Catch all */
.                                       {  }

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
             "program"               { return symbol(sym.PROGRAM); }
             "begin"                 { return symbol(sym.BEGIN); }
             "in"                    { return symbol(sym.IN); }             
             "end"                   { return symbol(sym.END); }
             /* declarations */
             "struct"                { return symbol(sym.REC_DECL); }
             "proc"                  { return symbol(sym.PROC_DECL); }
             "var"                   { return symbol(sym.VAR_DECL); }
             "new"                   { return symbol(sym.NEW); }
             /* program logic */
             "if"                    { return symbol(sym.IF); }
             "fi"                    { return symbol(sym.FI); }
             "then"                  { return symbol(sym.THEN); }
             "else"                  { return symbol(sym.ELSE); }
             "while"                 { return symbol(sym.WHILE); }
             "do"                    { return symbol(sym.DO); }
             "od"                    { return symbol(sym.OD); }
             "return"                { return symbol(sym.RETURN); }
             "ref"                   { return symbol(sym.REF); }
             "deref"                 { return symbol(sym.DEREF); }
             /* Negation */
             "not"                   { return symbol(sym.NOT); }
             "true"                  { return symbol(sym.TRUE); }
             "false"                 { return symbol(sym.FALSE); }
             "null"                  { return symbol(sym.NULL); }
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
             ":"                        { return symbol(sym.PARAM_ID, yytext()); }
             ";"                        { return symbol(sym.SEMI, yytext()); }
             ","                        { return symbol(sym.COMMA, yytext()); }
             "("                        { return symbol(sym.LPAR); }
             ")"                        { return symbol(sym.RPAR); }
             "{"                        { return symbol(sym.LCURLY); }
             "}"                        { return symbol(sym.RCURLY); }             
}

 /* Types and identifiers */
<YYINITIAL> {
             {NAME}_                 { System.exit(4763); }
             {NAME}                  { return symbol(sym.NAME, yytext()); }
             {INT_LITERAL}"."        { System.exit(4763); }
             {INT_LITERAL}           { return symbol(sym.INT_LITERAL, yytext()); }
             {STRING_LITERAL}        { return symbol(sym.STRING_LITERAL, yytext()); }
             {FLOAT_LITERAL}         { return symbol(sym.FLOAT_LITERAL, yytext()); }
}

/* Catch all */
.                                       {  }

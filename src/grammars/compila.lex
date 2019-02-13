package parser;
import java_cup.runtime.*;
%%

%class Lexer
%unicode
%cup
%line
%column
%public
%{
 StringBuffer string = new StringBuffer();

  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }

%}
LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]
Identifier = [:jletter:] [:jletterdigit:]*
%%
<YYINITIAL>{
        {WhiteSpace}                    {}
        "program"                       { return symbol(sym.PROGRAM); }
        "class"                         { return symbol(sym.CLASS); }
        "begin"                         { return symbol(sym.BEGIN); }
        "end"                           { return symbol(sym.END); }
        "("                             { return symbol(sym.LPAR); }
        ")"                             { return symbol(sym.RPAR); }
        ";"                             { return symbol(sym.SEMI); }
        {Identifier}                    { return symbol(sym.ID,yytext()); }
}

.                           { throw new Error("Illegal character '" + yytext() + "' at line " + yyline + ", column " + yycolumn + "."); }

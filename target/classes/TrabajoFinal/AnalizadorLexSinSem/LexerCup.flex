package TrabajoFinal.AnalizadorLexSinSem;

import java_cup.runtime.Symbol;

%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t\r,\n]+
%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
%}
%%

/* Espacios en blanco */
{espacio} { /*Ignore*/ }

/* Comentarios */
( "//".* ) { /*Ignore*/ }

/* Tipos de datos */
( "var" | "const" | "let" ) { return new Symbol(sym.Var, yychar, yyline, yytext()); }

/* Palabras reservadas */
( "if" ) { return new Symbol(sym.If, yychar, yyline, yytext()); }
( "else" ) { return new Symbol(sym.Else, yychar, yyline, yytext()); }
( "while" ) { return new Symbol(sym.While, yychar, yyline, yytext()); }
( "for" ) { return new Symbol(sym.For, yychar, yyline, yytext()); }
( "function" ) { return new Symbol(sym.Function, yychar, yyline, yytext()); }
( "return" ) { return new Symbol(sym.Return, yychar, yyline, yytext()); }

/* Operadores */
( "=" ) { return new Symbol(sym.Igual, yychar, yyline, yytext()); }
( "+" ) { return new Symbol(sym.Suma, yychar, yyline, yytext()); }
( "-" ) { return new Symbol(sym.Resta, yychar, yyline, yytext()); }
( "*" ) { return new Symbol(sym.Multiplicacion, yychar, yyline, yytext()); }
( "/" ) { return new Symbol(sym.Division, yychar, yyline, yytext()); }

/* Paréntesis y llaves */
( "(" ) { return new Symbol(sym.Parentesis_a, yychar, yyline, yytext()); }
( ")" ) { return new Symbol(sym.Parentesis_c, yychar, yyline, yytext()); }
( "{" ) { return new Symbol(sym.Llave_a, yychar, yyline, yytext()); }
( "}" ) { return new Symbol(sym.Llave_c, yychar, yyline, yytext()); }
( ";" ) { return new Symbol(sym.P_coma, yychar, yyline, yytext()); }

/* Identificadores */
{L}({L}|{D})* { return new Symbol(sym.Identificador, yychar, yyline, yytext()); }

/* Números */
{D}+ { return new Symbol(sym.Numero, yychar, yyline, yytext()); }

/* Error de análisis */
. { return new Symbol(sym.ERROR, yychar, yyline, yytext()); }
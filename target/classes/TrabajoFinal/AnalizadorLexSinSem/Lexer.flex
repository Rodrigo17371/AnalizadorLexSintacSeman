package TrabajoFinal.AnalizadorLexSinSem;

import static TrabajoFinal.AnalizadorLexSinSem.Tokens.*;
import java_cup.runtime.Symbol;

%%
%class Lexer
%type Tokens
%{
    public String lexeme;
%}
%%

/* Espacios en blanco */
[ \t\r\n] { /*Ignore*/ }

/* Comentarios */
"//"(.)* { /*Ignore*/ }

/* Salto de línea */
"\n" { return Linea; }

/* Tipos de datos */
"var" { lexeme=yytext(); return Var; }
"const" { lexeme=yytext(); return Const; }
"let" { lexeme=yytext(); return Let; }

/* Palabras reservadas */
"if" { lexeme=yytext(); return If; }
"else" { lexeme=yytext(); return Else; }
"while" { lexeme=yytext(); return While; }
"for" { lexeme=yytext(); return For; }
"function" { lexeme=yytext(); return Function; }
"return" { lexeme=yytext(); return Return; }

/* Operadores */
"=" { lexeme=yytext(); return Igual; }
"+" { lexeme=yytext(); return Suma; }
"-" { lexeme=yytext(); return Resta; }
"*" { lexeme=yytext(); return Multiplicacion; }
"/" { lexeme=yytext(); return Division; }

/* Paréntesis y llaves */
"(" { lexeme=yytext(); return Parentesis_a; }
")" { lexeme=yytext(); return Parentesis_c; }
"{" { lexeme=yytext(); return Llave_a; }
"}" { lexeme=yytext(); return Llave_c; }
";" { lexeme=yytext(); return P_coma; }

/* Identificadores */
[a-zA-Z_][a-zA-Z0-9_]* { lexeme=yytext(); return Identificador; }

/* Números */
[0-9]+ { lexeme=yytext(); return Numero; }

/* Error de análisis */
. { return ERROR; }


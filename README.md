# Recursive-Descent-Parser---Java
 
Consider the following EBNF grammar for a very simple programming language:
program ::= P {declare} B {statmt} E ;
declare ::= ident {, ident} : V ; 
statmt ::= assnmt | ifstmt | loop | read | output 
assnmt ::= ident ~ exprsn ;
ifstmt ::= I comprsn @ {statmt} [% {statmt}] &
loop ::= W comprsn L {statmt} T
read ::= R ident {, ident } ;
output ::= O ident {, ident } ; 
comprsn ::= ( oprnd opratr oprnd ) 
exprsn ::= factor {+ factor} 
factor ::= oprnd {* oprnd}
oprnd ::= integer | ident | ( exprsn )
opratr ::= < | = | > | !
ident ::= letter {char}
char ::= letter | digit
integer ::= digit {digit}
letter ::= X | Y | Z
digit ::= 0 | 1

Develop a recursive descent parser for following EBNF Grammar
<exp> ::= <atom> | <list>
<atom> ::= <digit> | <string>
<list> ::= ( <exprlist> )
<exprlist> ::= { <exp> }
<digit> ::= 0 | 1
<string> ::= a | b
  
 Implement a recursive-descent recognizer:
   - Prompt the user for an input stream.
   - The user enters an input stream of legal tokens, followed by a $.
   - You can assume:
           the user enters no whitespace,
           the user only enters legal tokens listed above,
           the user enters one and only one $, at the end.
   - The start symbol is *block* (as defined above)
   - Your program should output "legal" or "errors found" (not both!).
           You can report additional information as well, if you want.
           For example, knowing where your program finds an error could
           be helpful for me to assign partial credit if it's wrong.
    - Assume the input stream is the TOKEN stream.  Assume that any
           whitespace has already been stripped out by the lexical scanner.
           (i.e., each token is one character - lexical scanning has been completed)
  

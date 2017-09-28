//NAME : JIDNYASA DHAVALE
//RECURSIVE DESCENT PARSER 
 
import java.io.*;
import java.util.Scanner;
//--------------------------------------------
/* program ::= P {declare} B {statmt} E ;
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
*/
//--------------------------------------------
/*  
    Valid Input : 
	PBE;$
    PX:V;BY~1*1;E;$         //assnmt statement
	PX:V;BI(1>0)@Y~1;&E;$   //ifstatmt

	// to run on Athena (linux) -
	// save as: RDescentParser.java
	// compile: javac RDescentParser.java
	// execute: java RDescentParser
  */

public class RDescentParser
{
 static String inputString;
 static int index = 0;
 static int errorflag = 0;
 
 private char token()
 { return(inputString.charAt(index)); }
 
 private void advancePtr()
 { if (index < (inputString.length()-1)) index++; }
 
 private void match(char T)
 { if (T == token()) advancePtr(); //else error(); 
 
 }
 
 private void error(String S)
 {
 System.out.println("error at position: " + index);
 System.out.println("\n" + S);
 errorflag = 1;
 advancePtr();
 }

 //----------------------
 private void program()
 { 
	 
	 if(token() == 'P') 
	 {	match(token());	
		 if (token() == 'X'|| token() == 'Y' || token() == 'Z')
		 {	while (token() == 'X'|| token() == 'Y' || token() == 'Z')
			 declare();
		 }
	 }
	 else error("Expecting P");
	 if(token() == 'B')
	 {	 match(token());	
		 if (token() == 'X'|| token() == 'Y' || token() == 'Z'||token() == 'I' || token() == 'W' || token() == 'R' || token() == 'O')
		 {
			while(token() == 'X'|| token() == 'Y' || token() == 'Z'||token() == 'I'  || token() == 'W' || token() == 'R' || token() == 'O')
			 statmt();
		 }	
	 }
	 else error("Expecting B");
	 if(token() == 'E')		  match('E');
	  else error("Expecting E");
	 if(token() == ';')		  match(';');
	  else error("Expecting ;");
	 
	 
}
 private void declare()
 {
	  if (token() == 'X'|| token() == 'Y' || token() == 'Z')
	  		  ident();
	  else error("Expecting ident(X|Y|Z)");
	  if(token()==',')  
	  {
		  while(token()==',')
		  {
			 match(token());
			 if (token() == 'X'|| token() == 'Y' || token() == 'Z')
			  	  ident();
			 else error("Expecting ident(X|Y|Z)");
		  }
	  }
	  if(token()==':')    match(':');
	   else error("Expecting : ");
	  if(token()=='V')    match('V');
	   else error("Expecting V ");
	  if(token() == ';')  match(';');
	   else error("Expecting ; ");
	  
	 
 }
 
 
 private void statmt()
 { //statmt ::= assnmt | ifstmt | loop | read | output 
	 if (token() == 'X'|| token() == 'Y' || token() == 'Z')
		 assnmt();
	 else if (token() == 'I')
		 ifstmt();
	 else if (token() == 'W')
		 loop();
	 else if (token() == 'R')
		 read();
	 else if (token() == 'O')
		 output();
	 else error("Not a valid statmt type");
	 
 }
 
 
 private void assnmt()
 {//ident~exprsn;
	 if ((token() == 'X') || (token() == 'Y') || (token() == 'Z') )
		 ident();
	 else error("Expecting ident(X|Y|Z)");
	 if (token() == '~')
		 match(token());
	 else error("Expecting ~ ");
	 if ((token() == 'X') || (token() == 'Y') || (token() == 'Z')||
			 (token() == '0') || (token() == '1') || (token() == '(' ))
			 exprsn();
	 else error("Expecting exprsn( X|Y|Z|0|1|( )");
	 if (token() == ';')
		 match(token());
	 else error("Expecting ; ");
 }
 private void ifstmt()
 {	//I comprsn @ {statmt} [% {statmt}] &
	 if (token() == 'I')	    match(token());
	 else error("Expecting I ");
	 if(token() == '(')    
	 {	 
		 match(token());
		 comprsn();
		 
	 }
	 
	 else error("Expecting ( ");
	 
	 if(token() == '@')    match(token());
	 else error("Expecting @ ");
	 if(token() == 'X'|| token() == 'Y' || token() == 'Z'||token() == 'I'  || token() == 'W' || token() == 'R' || token() == 'O')
	 {
		 while(token() == 'X'|| token() == 'Y' || token() == 'Z'||token() == 'I'  || token() == 'W' || token() == 'R' || token() == 'O')
		 statmt();
	 }
	 else error("Not a valid statmt type");
	 if(token() == '%')	match(token());
	 
	 if(token() == 'X'|| token() == 'Y' || token() == 'Z' ||token() == 'I' || token() == 'W' || token() == 'R' || token() == 'O')
	 {
		 while(token() == 'X'|| token() == 'Y' || token() == 'Z'||token() == 'I'  || token() == 'W' || token() == 'R' || token() == 'O')
		 statmt();
	 }
	 if(token() == '&')    match(token());
	 else error("Expecting & ");
	 
 }
 private void loop()
 {	//W comprsn L {statmt} T
	 if (token() == 'W') 	    match(token());
	 else error("Expecting w ");
	 if (token() == '(')   
	 {	 match(token()); comprsn();}
	 else error("Expecting ( ");
	 if (token() == 'L') 	match(token());
	 else error("Expecting L ");
	 if(token() == 'X'|| token() == 'Y' || token() == 'Z'||token() == 'I'  || token() == 'W' || token() == 'R' || token() == 'O')
	 {
		 while(token() == 'X'|| token() == 'Y' || token() == 'Z'||token() == 'I'  || token() == 'W' || token() == 'R' || token() == 'O')
		 statmt();
	 }
	 if (token() == 'T') 	match(token());
	 else error("Expecting T ");
 }
 private void read()
 {// R ident {, ident } ;
	 if (token() == 'R')
		 match(token());
	 else error("Expecting R ");
	 if (token() == 'X'|| token() == 'Y' || token() == 'Z')
	  	  ident();
	 else error("Expecting Ident(X|Y|Z) ");
	 if(token()==',')
	 {
		 while(token()==',')
		 {
			 match(token());
			 if (token() == 'X'|| token() == 'Y' || token() == 'Z')
				 ident();
			 else error("Expecting Ident(X|Y|Z) ");
		 }
	 }
	 if (token() == ';')
		 match(token());
	 else error("Expecting ; ");
	
 }
 
 private void output()
 { //O ident {, ident } ;
	 if (token() == 'O')   	 match(token());
	 else error("Expecting O ");
	 if (token() == 'X'|| token() == 'Y' || token() == 'Z')
	  	  ident();
	 else error("Expecting Ident(X|Y|Z) ");
	 if(token()==',')
	 {
		 while(token()==',')
		 {
			 match(token());
			 if (token() == 'X'|| token() == 'Y' || token() == 'Z')
				 ident();
			 else error("Expecting Ident(X|Y|Z) ");
		 }
	 }
	 if (token() == ';')
		 match(token());
	 else error("Expecting ; ");
	 
	
 }
 private void comprsn()
 {//comprsn ::= ( oprnd opratr oprnd ) 
	 if ((token() == 'X') || (token() == 'Y') || (token() == 'Z')||
			 (token() == '0') || (token() == '1') || (token() == '(' ))
	 oprnd();
	 else error("Not a valid oprnd type ");
	 if ((token() == '<') || (token() == '=') || (token() == '>') || (token() == '!')) 
	 opratr();
	 
	 if ((token() == 'X') || (token() == 'Y') || (token() == 'Z')||
			 (token() == '0') || (token() == '1') || (token() == '(' ))
	 oprnd();
	 else error("Not a valid oprnd type ");
	 
	 
 }
 private void exprsn()
 {//exprsn ::= factor {+ factor}
	 if ((token() == 'X') || (token() == 'Y') || (token() == 'Z')||
			 (token() == '0') || (token() == '1') || (token() == '(' ))
			 factor();
	 
	 if(token()=='+')
	 {
		 while(token()=='+')
		 {
			 match(token());
			 if ((token() == 'X') || (token() == 'Y') || (token() == 'Z')||
			 (token() == '0') || (token() == '1') || (token() == '(' ))
			 factor();
			 else error("Not a valid factor type ");
		 }
	 }
 }
 private void factor()
 {//factor ::= oprnd {* oprnd}
	 if ((token() == 'X') || (token() == 'Y') || (token() == 'Z')||
			 (token() == '0') || (token() == '1') || (token() == '(' ))
			 oprnd();
	 else error("Not a valid oprnd type ");
	 if (token()=='*')
	 {
		 while(token()=='*')
		 {
			 match(token()); 
			 if ((token() == 'X') || (token() == 'Y') || (token() == 'Z')||
				 (token() == '0') || (token() == '1') || (token() == '(' ))
				 oprnd();
			 else error("Not a valid oprnd type ");
		 }
	 }
 }
 private void oprnd()
 {//oprnd ::= integer | ident | ( exprsn )
	 if ((token() == '0') || (token() == '1')) integer();
	 
	 if (token() == 'X'|| token() == 'Y' || token() == 'Z')
	  	  ident();
	 if (token() == '(')
	 {
		  match(token());
		  exprsn();
	 }
	 if (token() == ')')
		 match(token());
	 
 }
 private void opratr()
 {//opratr ::= < | = | > | !
	 if ((token() == '<') || (token() == '=') || (token() == '>') || (token() == '!')) match(token()); 
	 else error("Not a valid opratr type ");;
 }
 

 private void ident()
 {//letter{char}
	 if (token() == 'X'|| token() == 'Y' || token() == 'Z')
	    letter();
	 else if(token() == 'X'|| token() == 'Y' || token() == 'Z'|| token() == '0' || token() == '1')
	 {
		 while(token() == 'X'|| token() == 'Y' || token() == 'Z'|| token() == '0' || token() == '1')
			 character();
	 }
	 
 }
 private void character()
 {//letter/digit
	 if ((token() == 'X') || (token() == 'Y') || (token() == 'Z') )
		 letter();
	 else if ((token() == '0') || (token() == '1')) 
		 digit();
	 
 }
 
 private void integer()
 { //integer ::= digit {digit}
	
	 if ((token() == '0') || (token() == '1')) 
	 {
		 while ((token() == '0') || (token() == '1')) digit();
	 }	 
	 
}
 
 private void letter()
 {
	 if ((token() == 'X') || (token() == 'Y') || (token() == 'Z') ) match(token()); 
	 else error("Not a valid letter type "); 
 }
 private void digit()
 { if ((token() == '0') || (token() == '1')) match(token()); else error("Not a valid digit type ");
 }
 
 
 //----------------------
 private void start()
 {
 program();
 if(token()=='$')
 match('$');
 else
	 error("Expecting $ at end");
 if (errorflag == 0)
 System.out.println("legal." + "\n");
 else
 System.out.println("errors found." + "\n");
 }
//----------------------
 public static void main (String[] args) throws IOException
 {
 RDescentParser rec = new RDescentParser();
 Scanner input = new Scanner(System.in);
 System.out.print("\n" + "enter an expression: ");
 inputString = input.nextLine();
 rec.start();
 }
}
/**

This class is a top-down, recursive-descent parser for the following syntactic categories:

<assignment list> --> { <assignment> }+
<assignment> --> <id> = <E> ";"
<E> --> <term> { (+|-) <term> }
<term> --> <primary> { (*|/) <primary> }
<primary> --> <id> | <int> | <float> | <floatE> | "(" <E> ")"

Note: The binary operators +, -, *, / associate to left.

The definitions of the tokens are given in the lexical analyzer class file "LexArithArray.java". 

The following variables/functions of "IO"/"LexArithArray" classes are used:

static void display(String s)
static void displayln(String s)
static void setIO(String inFile, String outFile)
static void closeIO()

static void setLex()
static String t // holds an extracted token
static State state // the current state of the finite automaton
static int getToken() // extracts the next token

An explicit parse tree is constructed in the form of nested class objects.
 
The main function will display the parse tree in linearly indented form.
Each syntactic category name labeling a node is displayed on a separate line, 
prefixed with the integer i representing the node's depth and indented by i blanks.

All iterations of the form { ... } and { ... }+ are implemented by java.util.LinkedList.
Function call list.add(x) appends element x to the end of list. 

**/

import java.util.*;

public abstract class Parser extends LexArith
{
	static boolean errorFound = false;

 public static void statement(String indent)
 { 
	 displayln(indent+indent.length()+"<statement>");
      indent = indent+" ";
      if(state==State.Id)
    	  assignment(indent);
      else if (state==State.Keyword_if)
    	  cond(indent);
      else if (state==State.Keyword_switch)
    	  switchs(indent);
      else if(state==State.Keyword_while)
    	  whileloop(indent);
      else if(state==State.Keyword_do)
    	  doloop(indent);
      else if(state==State.Keyword_for)
    	  forloop(indent);
      else if (state==State.Keyword_print)
    	  print(indent);
      else if (state==State.LBrace)
    	  block(indent);
 }
      
    	  
	
	public static void assignment(String indent)
	
	// <assignment> --> <id> = <expr> ";"
	
	{
		displayln(indent+indent.length()+"<assignment>");
		indent = indent+" ";
		if ( state == State.Id )
		{  	
			displayln(" "+t); 
			getToken();
			if ( state == State.Assign )
			{	
				displayln(" "+t); 
				getToken();
				expr(indent);
				if ( state == State.Semicolon )
				{  displayln(" "+t); 
					getToken();
				}
				else
					errorMsg(4);
			}
			else
				errorMsg(3);
		}
		else
			errorMsg(5);
	}

	
	
	public static void cond(String indent)

// ⟨cond⟩ → "if" "(" ⟨expr⟩ ")" ⟨statement⟩ [ "else" ⟨statement⟩ ] 

	{
		display(indent+indent.length()+"<cond>");
	    indent = indent+" ";
	    
	    getToken();
	    if(state==State.LParen){
	    	getToken();
	    	expr(indent);
	    if(state==State.RParen)
	    {
	      getToken();
	      statement(indent);
	      
	    }
	    else errorMsg(1);
	    }
	    else errorMsg(6);
	    
	    if(state==State.Keyword_else)
	    {
			displayln(indent+indent.length()+" "+t);
	    	getToken();
	    	statement(indent);
	    
	    }
		
}
	public static void switchs(String indent){
//⟨switch⟩ → "switch" "(" ⟨expr⟩ ")" "{" ⟨case list⟩ "}" 

		display(indent+indent.length()+"<switch>");
	    indent = indent+" ";
	    getToken();
	    if(state==State.LParen){
   		 getToken();
   		 expr(indent);
   		if(state==State.RParen)
   		{ 
   			getToken();
   		if(state==State.LBrace)
   		{
   			getToken();
   			caselist(indent);
   		if (state==State.RBrace)
   		{ 
   			getToken();
   		}
   		else errorMsg(8);
   		}
   		else errorMsg(7);
   		}
   		else errorMsg(1);
   	 }
   	 else errorMsg(6);
    }
	public static void caselist(String indent){
		//⟨case list⟩ → { ⟨case⟩ }+ 
		display(indent+indent.length()+"<caselist>");
	    indent = indent+" ";
	    cases(indent);
	    while(state==State.Keyword_case||state==State.Keyword_default)
	    { displayln(indent+indent.length()+" "+t);
		 getToken();
		 cases(indent);
	    }
	    	
	}
	public static void cases(String indent){
		//⟨case⟩ → "case" ⟨label⟩ ":" ⟨s list⟩ | "default" ":" ⟨s list⟩ 
		display(indent+indent.length()+"case"+":");
	    indent = indent+" ";
		if(state==State.Keyword_case)
		  {	
			  getToken();
			  label(indent);
			  if(state==State.Colon)
			  { 
				  getToken();
				slist(indent);
			  }
			  else  errorMsg(4);
	       }
		  else if(state==State.Keyword_default){
			  getToken();
			  if(state==State.Colon)
			  {
				  getToken();
				  slist(indent);
			  }
			  else  errorMsg(4);
		  }
		
	}
	public static void label(String indent){
		display(indent+indent.length()+"<label>");
	    indent = indent+" ";
	    if(state==State.Int)
	    {
	    	getToken();
	    }
		
	}
	public static void whileloop(String indent){
		//⟨while loop⟩ → "while" "(" ⟨expr⟩ ")" ⟨statement⟩ 
		display(indent+indent.length()+"<while loop>");
	    indent = indent+" ";
        getToken();
        if(state==State.LParen){
			   getToken();
			   expr(indent);
			   if(state==State.LParen)
			   {
				 getToken();
				 statement(indent);
			   }
			   else errorMsg(1);
		   }
		   else errorMsg(2);
	   }        
	public static void doloop(String indent){
	//⟨do loop⟩ → "do" ⟨statement⟩ "while" "(" ⟨expr⟩ ")" ";" 
		display(indent+indent.length()+"<do loop>");
	    indent = indent+" ";
	    getToken();
		   statement(indent);
		  if(state==State.Keyword_while)
		  {
			  getToken();
			  if(state==State.LBrace)
			  {
				getToken();
				expr(indent);
				if(state==State.RBrace){
					getToken();
					if(state==State.Semicolon)
					{
						getToken();
				  } 
				else errorMsg(4);
			  }
				else errorMsg(1);
		  }
			  else  errorMsg(6);
		   
	     }

	}
	public static void forloop(String indent){

	//⟨for loop⟩ → "for" "(" ⟨assign⟩ ";" ⟨expr⟩ ";" ⟨assign⟩ ")" ⟨statement⟩ 
		display(indent+indent.length()+"<for loop>");
	    indent = indent+" ";
	    getToken();
	    if(state==State.LParen)
	    {
	     getToken();
	     assign(indent);
	     if(state==State.Semicolon)
	     { 
	    	getToken();
	    	expr(indent);
	    	getToken();
	    	assign(indent);
	    	if(state==State.LParen)
	    	{
	    	  getToken();
	    	  statement(indent);
	    	}
	    	else errorMsg(1);
	     }
	     else errorMsg(4);
	    }
	    else errorMsg(6);
	 }
	public static void assign(String indent){
		//⟨assign⟩ → ⟨id⟩ "=" ⟨expr⟩ 
		display(indent+indent.length()+"<assign>");
	    indent = indent+" ";
	    if ( state == State.Id )
		{
			getToken();
			if ( state == State.Assign )
			{
				getToken();
				expr(indent);
	       }
		}


	}
	public static void print(String indent){

	//⟨print⟩ → "print" ⟨expr⟩ ";" 
		display(indent+indent.length()+"<print>");
	    indent = indent+" ";
	    if(state==State.Keyword_print)
		  { 
			  getToken();
			  expr(indent);
		   if(state==State.Semicolon)
		   {
		      getToken();
		   }
		   else errorMsg(4);
	      }
		  else errorMsg(5);
	}
	public static void block(String indent){

	//⟨block⟩ → "{" ⟨s list⟩ "}" 
		display(indent+indent.length()+"<block>");
	    indent = indent+" ";
	    if(state==State.LBrace)
		   {   displayln("");

			   getToken();
			   slist(indent);
			  if(state==State.RBrace)
			  {
	            getToken();
			  }
			  else errorMsg(1);
		   }
		   else errorMsg(6);
	}
	public static void slist(String indent){
		display(indent+indent.length()+"<s list>");
	    indent = indent+" ";
	//⟨s list⟩ → { ⟨statement⟩ } 
	    while(state==State.Id||state==State.Keyword_if||state==State.Keyword_switch||
	    		state==State.Keyword_while||state==State.Keyword_do||
	    		state==State.Keyword_for||state==State.Keyword_print||
	    		state==State.LBrace)
	    	
	    {
	    	displayln(indent+indent.length()+" "+t);
	    	statement(indent);
	    	getToken();
	    }
	}
	public static void expr(String indent)
	{
	
		display(indent+indent.length()+"<expr>");
	    indent = indent+" ";
	//⟨expr⟩ → ⟨boolTerm⟩ { "||" ⟨boolTerm⟩ } 
	    boolTerm(indent);
	    while(state==State.Or)
	    {
	    	displayln(indent+indent.length()+" "+t);
			getToken();
			boolTerm(indent);
	    }
	}
	public static void boolTerm(String indent){
	//⟨boolTerm⟩ → ⟨boolPrimary⟩ { "&&" ⟨boolPrimary⟩ } 
		display(indent+indent.length()+"<bool Term>");
	    indent = indent+" ";
	    boolPrimary(indent);
	    while(state==State.And)
	    {
	    	displayln(indent+indent.length()+" "+t);
			getToken();
			boolPrimary(indent);
	    }
	    	
	}
	public static void boolLiteral(String indent){
		//⟨boolLiteral⟩ → "false" | "true" 

		display(indent+indent.length()+"<bool literal>");
	    indent = indent+" ";
	    if(state==State.Keyword_false||state==State.Keyword_true)
	    	getToken();
	   
	    
	}

	public static void boolPrimary(String indent){
	//⟨boolPrimary⟩ → ⟨E⟩ [ ⟨rel op⟩ ⟨E⟩ ] 
		display(indent+indent.length()+"<bool primary>");
	    indent = indent+" ";
		E(indent);
		if(state==State.Lt||state==State.Le||state==State.Gt||state==State.Ge||
				state==State.Eq||state==State.Neq){
			getToken();
           E(indent);
	   }
	}
	
	
	public static void E(String indent)

	// <E> --> <term> { (+|-) <term> }

	{
		displayln(indent+indent.length()+" <E>");
		indent = indent+" ";

		term(indent);
		while ( state == State.Plus || state == State.Minus )
		{
			displayln(indent+indent.length()+" "+t);
			getToken();
			term(indent);
		}
	}

	public static void term(String indent)

	// <term> --> <primary> { (*|/) <primary> }

	{
		
		displayln(indent+indent.length()+" <term>");
		indent = indent+" ";

		primary(indent);
		while ( state == State.Times || state == State.Div )
		{
			displayln(indent+indent.length()+" "+t);
			getToken();
			primary(indent);
		}
	}
	public static void primary(String indent)

	//primary⟩ → ⟨id⟩ | ⟨int⟩ | ⟨float⟩ | ⟨floatE⟩ | ⟨boolLiteral⟩ | "(" ⟨expr⟩ ")" | − ⟨primary⟩ | ! ⟨primary⟩ 

	{
		display(indent+indent.length()+" <primary>");
	    indent = indent+" ";
	if ( state.compareTo(State.Id) >= 0 && state.compareTo(State.FloatE) <= 0 )

	// state == Id, Int, Float, or FloatE

	{
		displayln(" "+t); 

	     	getToken();
	}
	else if (state==State.Minus)
	{
		displayln(" ");
		getToken();
	    primary(indent);
     }
	else if (state==State.Inv){
		displayln(" ");
		getToken();
		primary(indent);
	}
	else if ( state == State.LParen )
	{
		displayln("");

		getToken();
		expr(indent);
		if ( state == State.RParen )
			getToken();
		else
			errorMsg(1);
		
	 }
	else
		errorMsg(2);
	
		
	
}
	public static void errorMsg(int i)
	{
		errorFound = true;
		
		display(t + " : Syntax Error, unexpected symbol where");

		switch( i )
		{
		case 1:	displayln(" arith op or ) expected"); return;
		case 2: displayln(" id, int, float, or ( expected"); return;
		case 3:	displayln(" = expected"); return;
		case 4:	displayln(" ; expected"); return;
		case 5:	displayln(" id expected"); return;
		case 6:	displayln(" ) expected"); return;	
		case 7:	displayln(" { expected"); return;	
		case 8:	displayln(" } expected"); return;	



		
		}
	}

	public static void main(String argv[])
	{
		// argv[0]: input file containing an assignment list
		// argv[1]: output file displaying the parse tree
		
		setIO( argv[0], argv[1] );
		String indent = "";

		getToken();

		statement(indent); // build a parse tree
		if ( ! t.isEmpty() )
			displayln(t + " : Syntax Error, unexpected symbol");
		 // print the parse tree in linearly indented form in argv[1] file

		closeIO();
	}
}

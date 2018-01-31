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
import java.util.LinkedList;


public abstract class Parser extends LexArith
{
	static boolean errorFound = false;


		
	
	
//⟨statement⟩ → ⟨assignment⟩ | ⟨cond⟩ | ⟨switch⟩ | ⟨while loop⟩ | ⟨do loop⟩ | ⟨for loop⟩ | ⟨print⟩ | ⟨block⟩ 
	
	 /*
		switch ( state )
		{
		case Id:
			return assignment();
		case Keyword_cond:
		     return cond();
		case Keyword_switch:
			return switchs();
		case Keyword_while:
		    return whileloop();
		case Keyword_do:
		    return doloop();
		case Keyword_for:
		return forloop();
		case Keyword_print:
		return print();
		case Keyword_block:
		return block();
		
	default:
			return null;
		*/
		public static Statement statement()
		 { 
			 
		      if(state==State.Id)
		    	   assignment();
		      else if (state==State.Keyword_if)
		    	  cond();
		      else if (state==State.Keyword_switch)
		    	  switchs();
		      else if(state==State.Keyword_while)
		    	  whileloop();
		      else if(state==State.Keyword_do)
		    	  doloop();
		      else if(state==State.Keyword_for)
		    	  forloop();
		      else if (state==State.Keyword_print)
		    	  print();
		      else if (state==State.LBrace)
		    	  block();

			return null;
		 }
		      
										
				
		
	

	public static Assignment assignment()
	
	// <assignment> --> <id> = <E> ";"
	
	{
		if ( state == State.Id )
		{
			String id = t;
			getToken();
			if ( state == State.Assign )
			{
				getToken();
				E e = E();
				if ( state == State.Semicolon )
				{
					getToken();
					return new Assignment(id, e);
				}
				else
					errorMsg(4);
			}
			else
				errorMsg(3);
		}
		else
			errorMsg(5);
		return null;
	}
	public static Cond cond()
	{
//		⟨cond⟩ → "if" "(" ⟨expr⟩ ")" ⟨statement⟩ [ "else" ⟨statement⟩ ] 

		if (state==State.Keyword_if){
			getToken();
			if(state==State.LParen){
				getToken();
				Expr expr=expr();
			if(state==State.RParen){
				getToken();
				Statement statement=statement();
				return new Cond(expr,statement);

			}
			else errorMsg(1);
			}
			else errorMsg(2);
				
			}
		if (state==State.Keyword_else){
			getToken();
			Statement statement=statement();
			return new Cond(null,statement);
		}
		return null;
		
		
	}
	public static Switch switchs()
	{
	//⟨switch⟩ → "switch" "(" ⟨expr⟩ ")" "{" ⟨case list⟩ "}" 
	

     if (state==State.Keyword_switch)
     {
    	 getToken();
    	 if(state==State.LParen){
    		 getToken();
    		 Expr expr=expr();
    		if(state==State.RParen)
    		{ 
    			getToken();
    		if(state==State.LBrace)
    		{
    			getToken();
    		Caselist caselist=caselist();
    		if (state==State.RBrace)
    		{ 
    			getToken();
    		return new Switch(expr,caselist);
    		}
    		else errorMsg(1);
    		}
    		else errorMsg(2);
    		}
    		else errorMsg(3);
    	 }
    	 else errorMsg(4);
     }
	return null;	
    		
  	}	 
    	 
     
	
   public static Caselist caselist()
   //⟨case list⟩ → { ⟨case⟩ }+ 
   { 
	   LinkedList<Case> caselist = new LinkedList<Case>();
	   Case cases=cases();
	   caselist.add(cases);
	   while(state==State.Keyword_case){
		   cases=cases();
		   caselist.add(cases);
	   }
	   return new Caselist(caselist);	   
   }
   
   public static Case cases()
   //⟨case⟩ → "case" ⟨label⟩ ":" ⟨s list⟩ | "default" ":" ⟨s list⟩
   { 
	  if(state==State.Keyword_case)
	  {
		  getToken();
		  Label label=label();
		  if(state==State.Colon)
		  {
		   Slist slist=slist();
		   return new Case(label,slist);
		  }
		  else  errorMsg(4);
       }
	  else if(state==State.Keyword_default){
		  getToken();
		  if(state==State.Colon)
		  {
			  getToken();
			  Slist slist=slist();
			  return new Case(null,slist);
		  }
		  else  errorMsg(4);
	  }
	return null;
   }
   public static Label label()
   //⟨label⟩ → ⟨int⟩ 
   {
	   
	if(state==State.Int)
		getToken();
	return null;
   
   }
   
   public static Whileloop whileloop()
   //⟨while loop⟩ → "while" "(" ⟨expr⟩ ")" ⟨statement⟩ 
   {
	   if(state==State.Keyword_while){
		   getToken();
		   if(state==State.LParen){
			   getToken();
			   Expr expr=expr();
			   if(state==State.LParen)
			   {
				 getToken();
				 Statement statement= statement();
				 return new Whileloop(expr,statement);
			   }
			   else errorMsg(1);
		   }
		   else errorMsg(2);
	   }
	return null;
	   
   }
   public static Doloop doloop()
   //⟨do loop⟩ → "do" ⟨statement⟩ "while" "(" ⟨expr⟩ ")" ";" 
   {
	   if(state==State.Keyword_do)
	   {
		   getToken();
		   Statement statement=statement();
		  if(state==State.Keyword_while)
		  {
			  getToken();
			  if(state==State.LBrace)
			  {
				getToken();
				Expr expr=expr();
				if(state==State.RBrace){
					getToken();
					if(state==State.Semicolon)
					{
						getToken();
					return new Doloop(statement,expr);
				  } 
				else errorMsg(4);
			  }
				else errorMsg(1);
		  }
			  else  errorMsg(6);
		   
	     }
	   }
	return null;
   }
   public static Forloop forloop()
   //⟨for loop⟩ → "for" "(" ⟨assign⟩ ";" ⟨expr⟩ ";" ⟨assign⟩ ")" ⟨statement⟩ 
   {
	 if(state==State.Keyword_for)
	 {
	     getToken();
	    if(state==State.LParen)
	    {
	     getToken();
	     Assign assign1=assign();
	     if(state==State.Semicolon)
	     { 
	    	getToken();
	    	Expr expr=expr();
	    	getToken();
	    	Assign assign2=assign();
	    	if(state==State.LParen)
	    	{
	    	  getToken();
	    	  Statement statement=statement();
	    	  return new Forloop(assign1,expr,assign2,statement);
	    	}
	    	else errorMsg(1);
	     }
	     else errorMsg(4);
	    }
	    else errorMsg(6);
	 }
		return null;

   }   
   public static Assign assign()
   //⟨assign⟩ → ⟨id⟩ "=" ⟨expr⟩ 
   {
	  if(state==State.Id)
	  {
		  String id = t;
			getToken();
			if ( state == State.Assign )
			{
				getToken();
			  Expr expr= expr();
				return new Assign(id, expr);
			}
			else errorMsg(3);
	  }
	  else errorMsg(5);
	  return null;
   }
   public static Print print()
   //⟨print⟩ → "print" ⟨expr⟩ ";" 
   {
	  if(state==State.Keyword_print)
	  { 
		  getToken();
		  Expr expr=expr();
	   if(state==State.Semicolon)
	   {
	      getToken();
		  return new Print(expr);

	   }
	   else errorMsg(4);
	  }
	  return null;
   }
   public static Block block()
   //⟨block⟩ → "{" ⟨s list⟩ "}" 
   {
	   if(state==State.LBrace)
	   { 
		   getToken();
		   Slist slist=slist();
		  if(state==State.RBrace)
		  {
            getToken();
            return new Block(slist);
		  }
		  else errorMsg(1);
	   }
	   else errorMsg(6);
	   return null;
   }
   public static Slist slist()
   //⟨s list⟩ → { ⟨statement⟩ } 
   {
	   LinkedList<Statement> slist = new LinkedList<Statement>();
	   Statement statement=statement();
	   slist.add(statement);
	 while(state==State.Id||state==State.Keyword_if)
	   {
		   statement=statement();
		   slist.add(statement);
	   }
	   return new Slist(slist);

   }
   public static Expr expr()
   //⟨expr⟩ → ⟨boolTerm⟩ { "||" ⟨boolTerm⟩ } 
   {
	  LinkedList<BoolTerm> booltermlist= new LinkedList<BoolTerm>();
	  BoolTerm boolterm=boolterm();
	  //booltermlist.add(new SingleBoolterm(boolterm));
	  
	  while(state==State.Or)
	  {
			State op = state;
			getToken();
			}
	 return null;
	   
   }
   public static BoolTerm boolterm()
   {
	   //⟨boolTerm⟩ → ⟨boolPrimary⟩ { "&&" ⟨boolPrimary⟩ } 
  LinkedList<BoolPrimary> boolprimary= new LinkedList<BoolPrimary>();

	  BoolPrimary boolPrimary=boolPrimary();
	  boolprimary.add(boolPrimary);
	   while (state==State.And)
	   {
		getToken();
		//return new BoolTerm(boolPrimary);
	   }
	   return null;
   }
   public static BoolPrimary boolPrimary()
   {
	   E E=E();
	   if(state==State.Lt||state==State.Le||state==State.Gt||state==State.Ge||
				state==State.Eq||state==State.Neq)
	   {
			getToken();
	// return new BoolPrimary(E);
	   }
	   return null;
   }
   
	public static E E()

	// <E> --> <term> { (+|-) <term> }

	{
		LinkedList<TermItem> termItemList = new LinkedList<TermItem>();

		Term term = term();
		termItemList.add(new SingleTermItem(term));
		while ( state == State.Plus | state == State.Minus )
		{
			State op = state;
			getToken();
			term = term();
			if ( op == State.Plus )
				termItemList.add(new AddTermItem(term));
			else // op == State.Minus
				termItemList.add(new SubTermItem(term));
		}
		return new E(termItemList);
	}

	public static Term term()

	// <term> --> <primary> { (*|/) <primary> }

	{
		LinkedList<PrimaryItem> primaryItemList = new LinkedList<PrimaryItem>();

		Primary primary = primary();
		primaryItemList.add(new SinglePrimaryItem(primary));
		while ( state == State.Times | state == State.Div )
		{
			State op = state;
			getToken();
			primary = primary();
			if ( op == State.Times )
				primaryItemList.add(new MulPrimaryItem(primary));
			else // op == State.Div
				primaryItemList.add(new DivPrimaryItem(primary));
		}
		return new Term(primaryItemList);
	}

	public static Primary primary()

	// <primary> --> <id> | <int> | <float> | <floatE> | "(" <E> ")"

	{
		switch ( state )
		{
			case Id:
										
				Id id = new Id(t);
				getToken();
				return id;
				
			case Int:

				Int intElem = new Int(Integer.parseInt(t));
				getToken();
				return intElem;

			case Float: case FloatE:

				Floatp floatElem = new Floatp(Float.parseFloat(t));
				getToken();
				return floatElem;
			case Keyword_true: case Keyword_false:
				BoolLiteral boolLiteral=boolLiteral();
			
				
				

			case LParen:
				
				getToken();
				E e = E();
				if ( state == State.RParen )
				{
					getToken();
					Parenthesized paren = new Parenthesized(e);
					return paren;
				}
				else
				{
					errorMsg(1);
					return null;
				}
			case Div:case Inv:
				getToken();
				Primary primary=primary();
				return primary;
			
				
				
				

			default:

				errorMsg(2);
				return null;
		}
	}
	public static BoolLiteral boolLiteral()
	{
		if(state==State.Keyword_false||state==State.Keyword_false)
			getToken();
		return boolLiteral();
		
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
		case 6:	displayln(" ( expected"); return;

		}
	}

	public static void main(String argv[])
	{
		// argv[0]: input file containing an assignment list
		// argv[1]: output file displaying the parse tree
		
		setIO( argv[0], argv[1] );
		
		getToken();
		Statement statement=statement();
		if ( ! t.isEmpty() )
			errorMsg(5);
		else if ( ! errorFound )
			statement.printParseTree(""); // print the parse tree in linearly indented form in argv[1] file

		closeIO();
	}
}

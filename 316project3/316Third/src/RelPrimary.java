import java.util.HashMap;

class RelPrimary extends BoolPrimary
{
	static String[] relop_st = { "<", "<=", ">", ">=", "==", "!=" };
	static String[] relop_instruction = { "lt", "le", "gt", "ge", "eq", "neq" };

	E e1;
	E e2;
	State relop;

	RelPrimary(E e_1, E e_2, State rel)
	{
		e1 = e_1;
		e2 = e_2;
		relop = rel;
	}
	
	void printParseTree(String indent)
	{
		String indent1 = indent + " ";

		super.printParseTree(indent);
		e1.printParseTree(indent1);
		IO.displayln(indent1 + indent1.length() + " " + relop_st[relop.ordinal()-7]);
		e2.printParseTree(indent1);
	}

	@Override
	Val Eval(HashMap<String, Val> state, Val boolPrimaryVal) {
		// TODO Auto-generated method stub
		/*
		Val e1Val = e1.Eval(state);
		Val e2Val=e2.Eval(state);
		if(e2Val!=null)
			state.put(e1Val,e2Val);
		*/
		return null;
		
	}

	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		if(relop==State.Lt){
			e1.emitInstructions();
			 e2.emitInstructions();
			 IO.displayln("lt");
		}
		else if (relop==State.Le){
			e1.emitInstructions();
			 e2.emitInstructions();
			 IO.displayln("le");
		}
		else if(relop==State.Gt){
			e1.emitInstructions();
			 e2.emitInstructions();
			 IO.displayln("gt");	
		}
		else if(relop==State.Ge)
		{
			e1.emitInstructions();
			 e2.emitInstructions();
			 IO.displayln("ge");
		}
		else if(relop==State.Eq)
		{
			e1.emitInstructions();
			 e2.emitInstructions();
			 IO.displayln("eq");
		}
		else{
			e1.emitInstructions();
		 e2.emitInstructions();
		 IO.displayln("neq");
	
	}
	
}
//less than op
class LtE extends  RelPrimary{
	
	LtE(E e_1, E e_2, State rel) {
		super(e_1, e_2, rel);
		// TODO Auto-generated constructor stub
	}

	
	Val Eval(HashMap<String,Val> state)
	{
		Val e1Val = e1.Eval(state);
		Val e2Val = e2.Eval(state);
		if ( e1Val == null || e2Val == null )
			return null;
		
		if ( e1Val.isNumber() && e2Val.isNumber() )
		{
			if ( e1Val.getClass() == IntVal.class && e2Val.getClass() == IntVal.class )
				return new BoolVal( ((IntVal)e1Val).val < ((IntVal)e2Val).val );
			else
				return new BoolVal( e1Val.floatVal() < e2Val.floatVal() );
		}
		else
		{
			System.out.println( "Error: < operator cannot be applied to [ " + e1Val.toString() + " , " + e2Val.toString() + " ]" );
			return null;
		}
		
	}
	void emitInstructions() {
		// TODO Auto-generated method stub
	 e1.emitInstructions();
	 e2.emitInstructions();
	 IO.displayln("lt");
	}
	
}
//less equal op
class LeE extends RelPrimary
{	
	LeE(E e_1, E e_2, State rel) {
		super(e_1, e_2, rel);
		// TODO Auto-generated constructor stub
	}

	
	
	Val Eval(HashMap<String,Val> state)
	{
		Val e1Val = e1.Eval(state);
		Val e2Val = e2.Eval(state);
		if ( e1Val == null || e2Val == null )
			return null;
				
		if ( e1Val.isNumber() && e2Val.isNumber() )
		{
			if ( e1Val.getClass() == IntVal.class && e2Val.getClass() == IntVal.class )
				return new BoolVal( ((IntVal)e1Val).val <= ((IntVal)e2Val).val );
			else
				return new BoolVal( e1Val.floatVal() <= e2Val.floatVal() );
		}
		else
		{
			System.out.println( "Error: <= operator cannot be applied to [ " + e1Val.toString() + " , " + e2Val.toString() + " ]" );
			return null;
		}
	}
	void emitInstructions() {
		// TODO Auto-generated method stub
	 e1.emitInstructions();
	 e2.emitInstructions();
	 IO.displayln("le");// less equal
	}
}
//greater op
class GtE extends RelPrimary
{	
	GtE(E e_1, E e_2, State rel) {
		super(e_1, e_2, rel);
		// TODO Auto-generated constructor stub
	}

	

	Val Eval(HashMap<String,Val> state)
	{
		Val e1Val = e1.Eval(state);
		Val e2Val = e2.Eval(state);
		if ( e1Val == null || e2Val == null )
			return null;
		
		if ( e1Val.isNumber() && e2Val.isNumber() )
		{
			if ( e1Val.getClass() == IntVal.class && e2Val.getClass() == IntVal.class )
				return new BoolVal( ((IntVal)e1Val).val > ((IntVal)e2Val).val );
			else
				return new BoolVal( e1Val.floatVal() > e2Val.floatVal() );
		}
		else
		{
			System.out.println( "Error: > operator cannot be applied to [ " + e1Val.toString() + " , " + e2Val.toString() + " ]" );
			return null;
		}
	}
	void emitInstructions() {
		// TODO Auto-generated method stub
	 e1.emitInstructions();
	 e2.emitInstructions();
	 IO.displayln("gt");//greater than
	}
}
// greater than equal op
class GeE extends RelPrimary
{	
	GeE(E e_1, E e_2, State rel) {
		super(e_1, e_2, rel);
		// TODO Auto-generated constructor stub
	}

	
	
	Val Eval(HashMap<String,Val> state)
	{
		Val e1Val = e1.Eval(state);
		Val e2Val = e2.Eval(state);
		if ( e1Val == null || e2Val == null )
			return null;
		
		if ( e1Val.isNumber() && e2Val.isNumber() )
		{
			if ( e1Val.getClass() == IntVal.class && e2Val.getClass() == IntVal.class )
				return new BoolVal( ((IntVal)e1Val).val >= ((IntVal)e2Val).val );
			else
				return new BoolVal( e1Val.floatVal() >= e2Val.floatVal() );
		}
		else
		{
			System.out.println( "Error: >= operator cannot be applied to [ " + e1Val.toString() + " , " + e2Val.toString() + " ]" );
			return null;
		}
	}
	void emitInstructions() {
		// TODO Auto-generated method stub
	 e1.emitInstructions();
	 e2.emitInstructions();
	 IO.displayln("ge");//greater than equal
	}
}
//equal op
class EqE extends RelPrimary
{	
	EqE(E e_1, E e_2, State rel) {
		super(e_1, e_2, rel);
		// TODO Auto-generated constructor stub
	}

	
	
	Val Eval(HashMap<String,Val> state)
	{
		Val e1Val = e1.Eval(state);
		Val e2Val = e2.Eval(state);
		if ( e1Val == null || e2Val == null )
			return null;
		
		if ( e1Val.isNumber() && e2Val.isNumber() )
		{
			if ( e1Val.getClass() == IntVal.class && e2Val.getClass() == IntVal.class )
				return new BoolVal( ((IntVal)e1Val).val == ((IntVal)e2Val).val );
			else
				return new BoolVal( e1Val.floatVal() == e2Val.floatVal() );
		}
		else
		{
			System.out.println( "Error: == operator cannot be applied to [ " + e1Val.toString() + " , " + e2Val.toString() + " ]" );
			return null;
		}
	}
	void emitInstructions() {
		// TODO Auto-generated method stub
	 e1.emitInstructions();
	 e2.emitInstructions();
	 IO.displayln("eq");//equal
	}
}
// not equal op
class NeqE extends RelPrimary
{	
	NeqE(E e_1, E e_2, State rel) {
		super(e_1, e_2, rel);
		// TODO Auto-generated constructor stub
	}

	
	
	Val Eval(HashMap<String,Val> state)
	{
		Val e1Val = e1.Eval(state);
		Val e2Val = e2.Eval(state);
		if ( e1Val == null || e2Val == null )
			return null;
		
		if ( e1Val.isNumber() && e2Val.isNumber() )
		{
			if ( e1Val.getClass() == IntVal.class && e2Val.getClass() == IntVal.class )
				return new BoolVal( ((IntVal)e1Val).val != ((IntVal)e2Val).val );
			else
				return new BoolVal( e1Val.floatVal() != e2Val.floatVal() );
		}
		else
		{
			System.out.println( "Error: != operator cannot be applied to [ " + e1Val.toString() + " , " + e2Val.toString() + " ]" );
			return null;
		}
	}
	void emitInstructions() {
		// TODO Auto-generated method stub
	 e1.emitInstructions();
	 e2.emitInstructions();
	 IO.displayln("neq");//not equal
	}
	
}
}



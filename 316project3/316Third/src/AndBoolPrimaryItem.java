import java.util.HashMap;

class AndBoolPrimaryItem extends BoolPrimaryItem

// Represents "&& <boolPrimary>"

{
	// BoolPrimary boolPrimary; inherited from BoolPrimaryItem

	AndBoolPrimaryItem(BoolPrimary bp)
	{
		boolPrimary = bp;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " &&");
		boolPrimary.printParseTree(indent);
	}

	@Override
	Val Eval(HashMap<String, Val> state, Val booltermVal) {
		
		//not sure come back to this
		Val boolPrimaryVal=boolPrimary.Eval(state, booltermVal);
		if(booltermVal==null||boolPrimaryVal==null)
		return null;
		Class    booltermClass =    booltermVal.getClass();
		Class boolPrimaryClass = boolPrimaryVal.getClass();

		if ( booltermClass == BoolVal.class && boolPrimaryClass == BoolVal.class )
		{
			((BoolVal)booltermVal).val = ((BoolVal)booltermVal).val &&((BoolVal)boolPrimaryVal).val;
			return booltermVal;
		}
		/*
		else if ( booltermClass == BoolVal.class ) // boolPrimaryClass == FloatVal.class
		{
			((BoolVal)boolPrimaryVal).val = ((BoolVal)booltermVal).val && ((BoolVal)boolPrimaryVal).val;
			return booltermVal;
		}
		else 
		{
			((BoolVal)booltermVal).val = ((BoolVal) booltermVal).floatVal() && ((BoolVal) boolPrimaryVal).floatVal();
			return booltermVal;
		}
		*/else
		{
			System.out.println( "Error: and operator cannot be applied to [ " + boolPrimaryVal.toString() + "]" );
			return null;
		}

	}

	void emitInstructions()
	{
		boolPrimary.emitInstructions();
		IO.displayln("and");
	}
}
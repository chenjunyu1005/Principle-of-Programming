import java.util.HashMap;

class OrBoolTermItem extends BoolTermItem

// Represents "|| <term>"

{
	// BoolTerm boolTerm; inherited from BoolTermItem

	OrBoolTermItem(BoolTerm bt)
	{
		boolTerm = bt;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " ||");
		boolTerm.printParseTree(indent);
	}

	@Override
	Val Eval(HashMap<String, Val> state, Val exprVal) {
		// TODO Auto-generated method stub
		Val boolTermVal=boolTerm.Eval(state);
		if(exprVal==null||boolTermVal==null)
		return null;
		Class    exprClass =    exprVal.getClass();
		Class boolTermClass = boolTermVal.getClass();

		if ( exprClass == BoolVal.class && boolTermClass == BoolVal.class )
		{
			((BoolVal)exprVal).val = ((BoolVal)exprVal).val ||((BoolVal)boolTermVal).val;
			return exprVal;
		}
		else
		{
			System.out.println( "Error: Or operator cannot be applied to [ " + boolTermVal.toString() + "]" );
			return null;
		}
	}

	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		boolTerm.emitInstructions();
		IO.displayln(Compiler.indent + "or");
		Compiler.varlabel++;
	}
}
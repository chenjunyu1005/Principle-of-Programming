import java.util.HashMap;

class SingleBoolPrimaryItem extends BoolPrimaryItem

// Represents the first <boolPrimary> in <boolTerm>

{
	// BoolPrimary boolPrimary; inherited from BoolPrimaryItem

	SingleBoolPrimaryItem(BoolPrimary bp)
	{
		boolPrimary = bp;
	}

	void printParseTree(String indent)
	{
		boolPrimary.printParseTree(indent);
	}

	@Override
	Val Eval(HashMap<String, Val> state, Val booltermVal) {
		// TODO Auto-generated method stub
		//two argument not sure
		booltermVal=boolPrimary.Eval(state, booltermVal);
		return booltermVal;
	}

	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		boolPrimary.emitInstructions();
	}
}
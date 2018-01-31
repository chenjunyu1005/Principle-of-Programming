import java.util.HashMap;

class SinglePrimaryItem extends PrimaryItem

// Represents the first <primary> in <term>

{
	// Primary primary; inherited from PrimaryItem

	SinglePrimaryItem(Primary p)
	{
		primary = p;
	}

	void printParseTree(String indent)
	{
		primary.printParseTree(indent);
	}

	@Override
	Val Eval(HashMap<String, Val> state, Val termVal) {
		// TODO Auto-generated method stub

		termVal = primary.Eval(state);
		return termVal;

	}

	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		primary.emitInstructions();

	}
}
import java.util.HashMap;

class SingleTermItem extends TermItem

// Represents the first <term> in <E>

{
	// Term term; inherited from TermItem

	SingleTermItem(Term t)
	{
		term = t;
	}

	void printParseTree(String indent)
	{
		term.printParseTree(indent);
	}

	@Override
	Val Eval(HashMap<String, Val> state, Val eVal) {
		// TODO Auto-generated method stub
		eVal = term.Eval(state);
		return eVal;

	}

	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		term.emitInstructions();
	}
}
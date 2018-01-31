import java.util.HashMap;

class SingleBoolTermItem extends BoolTermItem

// Represents the first <boolTerm> in <Expr>

{
	// BoolTerm boolTerm; inherited from BoolTermItem

	SingleBoolTermItem(BoolTerm bt)
	{
		boolTerm = bt;
	}

	void printParseTree(String indent)
	{
		boolTerm.printParseTree(indent);
	}

	@Override
	Val Eval(HashMap<String, Val> state, Val exprVal) {
		// TODO Auto-generated method stub
		exprVal=boolTerm.Eval(state);
		return exprVal;
	}

	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		boolTerm.emitInstructions();
	}
}
import java.util.HashMap;

class SingleE extends BoolPrimary
{
	E e;

	SingleE(E e_)
	{
		e = e_;
	}
	
	void printParseTree(String indent)
	{
		super.printParseTree(indent);
		e.printParseTree(indent+" ");
	}
	Val Eval(HashMap<String, Val> state, Val boolPrimaryVal) {
		// TODO Auto-generated method stub
		boolPrimaryVal=e.Eval(state);
		return boolPrimaryVal;
	}

	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		e.emitInstructions();
	}
	
}
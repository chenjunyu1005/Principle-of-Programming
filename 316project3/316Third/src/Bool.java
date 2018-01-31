import java.util.HashMap;

class Bool extends Primary
{
	boolean val;

	Bool(boolean b)
	{
		val = b;
	}
	
	void printParseTree(String indent)
	{
		super.printParseTree(indent);
		IO.displayln(" " + val);
	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return new BoolVal(val);
	}

	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		IO.displayln("push "+ val);
	}
}
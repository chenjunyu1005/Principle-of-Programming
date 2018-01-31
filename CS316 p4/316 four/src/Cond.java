import java.util.HashMap;

class Cond extends Statement
{
	void printParseTree(String indent)
	{
		String indent1 = indent + " ";

		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <cond>");
	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		
		
	}
}

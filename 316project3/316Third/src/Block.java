import java.util.HashMap;

class Block extends Statement
{
	SList slist;

	Block(SList s)
	{
		slist = s;
	}
	
	void printParseTree(String indent)
	{
		String indent1 = indent + " ";
		
		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <block>");
		slist.printParseTree(indent1+" ");
	}

	
	Val Eval(HashMap<String, Val> state) {
		
		// TODO Auto-generated method stub
		return null;//slist.Eval(state);
		
	}

	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		slist.emitInstruction();
	}
}

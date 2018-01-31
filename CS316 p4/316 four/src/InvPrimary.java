import java.util.HashMap;
//finished  
class InvPrimary extends Primary
{
	Primary primary;

	InvPrimary(Primary p)
	{
		primary = p;
	}
	
	
	
	void printParseTree(String indent)
	{
		super.printParseTree(indent);
		IO.displayln("");
		IO.displayln(indent + indent.length() + " !");
		primary.printParseTree(indent+" ");
	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		Val primaryVal=primary.Eval(state);
		if(primaryVal==null)
			return null;
		if(primaryVal.getClass()==BoolVal.class){
			((BoolVal)primaryVal).val = ! ((BoolVal)primaryVal).val;
			return primaryVal;
		}
		else
		{
			System.out.println( "Error: ! operator cannot applied: " + primaryVal.toString() );
		return null;
		}
	}

	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		primary.emitInstructions();
		IO.displayln(Compiler.indent + "inv");
	}
}
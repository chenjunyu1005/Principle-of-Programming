import java.util.HashMap;
//need to work on
abstract class BoolPrimary
{
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <boolPrimary>");
	}

	abstract Val Eval(HashMap<String, Val> state, Val booltermVal) ;
	abstract void emitInstructions();
}
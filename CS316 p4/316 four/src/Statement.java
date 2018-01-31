import java.util.*;
abstract class Statement
{
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <statement>");
	}
	abstract Val Eval(HashMap<String,Val> state); 
	abstract void emitInstructions();

}

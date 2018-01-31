import java.util.HashMap;

class Floatp extends Primary
{
	float val;

	Floatp(float f)
	{
		val = f;
	}

	void printParseTree(String indent)
	{
		super.printParseTree(indent);
		IO.displayln(" " + val);
	}
	Val Eval(HashMap<String,Val> state)
	{
		return new FloatVal(val);
	}
	
	void emitInstructions()
	{
		IO.displayln("push " + val);
	}

}
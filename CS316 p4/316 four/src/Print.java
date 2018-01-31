import java.util.HashMap;

class Print extends Statement
{
	Expr expr;

	Print(Expr e)
	{
		expr = e;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent + " ";

		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <print>");
		expr.printParseTree(indent1+" ");
	}

	
	Val Eval(HashMap<String, Val> state) {	
		// TODO Auto-generated method stub
		Val exprVal = expr.Eval(state);
		return exprVal;
		
	}

	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		expr.emitInstructions();
		IO.displayln(Compiler.indent + "print");
	}
}
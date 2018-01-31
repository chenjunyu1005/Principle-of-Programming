import java.util.HashMap;

class Assignment extends Statement
{
	String id; // variable on the left side of the assignment
	Expr expr; // expression on the right side of the assignment
	Assignment(String s, Expr e)
	{
		id = s;
		expr = e;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent + " ";
		String indent2 = indent + "  ";

		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <assignment>");
		IO.displayln(indent2 + indent2.length() + " " + id);
		IO.displayln(indent2 + indent2.length() + " =");
		expr.printParseTree(indent2);
	}


	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		Val exprVal = expr.Eval(state); // evaluate expression expr
		if ( exprVal != null )
		  state.put(id, exprVal);// assign the value exprVal to id
		return exprVal;
	}

	
	void emitInstructions() {
		// TODO Auto-generated method stub
		expr.emitInstructions();
		Integer varNum = Compiler.varMap.get(id);
		if ( varNum == null ) // This is the first time "id" is seen.
		{
			varNum = Compiler.varNum++; // generate a new sequential number
			Compiler.varMap.put(id, varNum);
		}		
		IO.displayln(Compiler.indent +"pop #" + varNum);
	}

	
	
}
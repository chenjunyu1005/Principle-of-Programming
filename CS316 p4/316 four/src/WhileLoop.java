import java.util.HashMap;

class WhileLoop extends Statement
{
	Expr expr;
	Statement statement;

	WhileLoop(Expr e, Statement s)
	{
		expr = e;
		statement = s;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent + " ";
		String indent2 = indent + "  ";
		
		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <while loop>");
		IO.displayln(indent2 + indent2.length() + " while");
		expr.printParseTree(indent2);
		statement.printParseTree(indent2);
	}

	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		Compiler.varlabel++;
		IO.displayln(Compiler.varlabel+" :");
		
		expr.emitInstructions();
		IO.displayln(Compiler.indent + "ifF goto"+Compiler.varlabel);
		statement.emitInstructions();
		IO.displayln(Compiler.indent +"goto "+Compiler.varlabel);

		IO.displayln(Compiler.varlabel+" :");

	}
}


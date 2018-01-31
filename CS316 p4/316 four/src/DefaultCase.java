class DefaultCase extends Case
{
	// SList sList; inherited from Case

	DefaultCase(SList sl)
	{
		sList = sl;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " default");
		sList.printParseTree(indent+" ");
	}

	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		//IO.displayln()
		Compiler.varlabel++;
		sList.emitInstruction();
		IO.displayln(Compiler.varlabel+" :");

		IO.displayln(Compiler.indent +"ifF goto "+Compiler.varlabel);

	}
}
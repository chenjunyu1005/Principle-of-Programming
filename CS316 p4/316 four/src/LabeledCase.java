class LabeledCase extends Case
{
	static Label label;
	// SList sList; inherited from Case

	LabeledCase(Label l, SList sl)
	{
		label = l;
		sList = sl;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent + " ";

		IO.display(indent + indent.length() + " case :");
		label.printParseTree();
		sList.printParseTree(indent1);
	}

	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		Compiler.varlabel++;
		label.emitInstruction();
		
		sList.emitInstruction();
		IO.displayln(Compiler.indent +"ifF goto "+Compiler.varlabel);

		
	}
}
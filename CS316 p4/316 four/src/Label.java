class Label
{
	int val;

	Label(int i)
	{
		val = i;
	}

	void printParseTree()
	{
		IO.displayln(" " + val);
	}
//need to work on 
	 void emitInstruction() {
		// TODO Auto-generated method stub
		/*Integer varNum = Compiler.varMap.get(val);
		if ( varNum == null ) // This is the first time "id" is seen.
		{
			varNum = Compiler.varNum++; // generate a new sequential number
		}
		*/
		IO.displayln(val+":");
	}
	
}
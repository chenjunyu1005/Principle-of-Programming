import java.util.HashMap;

class If2 extends Cond
{
	Expr expr;
	Statement statement1;
	Statement statement2;
    //public static LinkedList<Integer> list  = new LinkedList<Integer>();
//    public static int[] a = new int[10];

	If2(Expr e, Statement s1, Statement s2)
	{
		expr = e;
		statement1 = s1;
		statement2 = s2;
	}

	void printParseTree(String indent)
	{
		String indent2 = indent + "  ";

		super.printParseTree(indent);
		IO.displayln(indent2 + indent2.length() + " if");
		expr.printParseTree(indent2);
		statement1.printParseTree(indent2);
		IO.displayln(indent2 + indent2.length() + " else");
		statement2.printParseTree(indent2);
	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		Compiler.varlabel++;	
		expr.emitInstructions();
		
		IO.displayln(Compiler.indent + "ifF goto "+Compiler.varlabel);
		Compiler.list.add(Compiler.varlabel+1);
		
		int n = ++Compiler.varlabel;		
		statement1.emitInstructions();	
		
		IO.displayln(Compiler.indent +"goto "+ n);
		IO.displayln((n-1)+" :");
		

      	statement2.emitInstructions();
      	
		for(int i=Compiler.list.size(); i >0 ;i--){
			IO.displayln(Compiler.list.pop()+" :");
		
		 }
		
 

	}
}


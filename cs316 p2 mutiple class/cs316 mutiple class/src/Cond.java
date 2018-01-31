
class Cond {
	Expr expr;
	Statement statement;

   Cond(Expr exp,Statement states)
   {
	   expr = exp;
	   statement =states;
   
   }
   void printParseTree(String indent)
	{
		String indent1 = indent + " ";
				
		Parser.displayln(indent + indent.length() + " <cond>");
		Parser.displayln(indent1 + indent1.length() + " (" + expr );
		Parser.displayln(indent1 + indent1.length() + " )");
		statement.printParseTree(indent1);
	}
	

}


public class Print {
	Expr expr;
	Print(Expr exp)
	{
		expr=exp;
	}
	void printParseTree(String indent)
	{
				
		Parser.displayln(indent + indent.length() + "");
		indent = indent+" ";
	}
}

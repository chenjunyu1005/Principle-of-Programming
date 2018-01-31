
public class Doloop {
	Statement statement;
	Expr expr;
	Doloop(Statement state,Expr exp)
	{
	statement=state;
	expr=exp;
	}
	void printParseTree(String indent)
	{
		IO.display(indent + indent.length() + " <do loop>");
		indent = indent+" ";
	}
}

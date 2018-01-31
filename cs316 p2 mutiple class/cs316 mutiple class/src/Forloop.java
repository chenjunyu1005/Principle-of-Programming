
public class Forloop {
	Assign assign1;
	Expr expr;
	Assign assign2;
	Statement statement;
	Forloop(Assign assig1, Expr exp ,Assign assig2, Statement state )
	{
		assign1 =assig1;
		expr=exp;
		assign2=assig2;
		statement=state;
		
	}
	void printParseTree(String indent)
	{
		IO.display(indent + indent.length() + " <for loop>");
	}

}

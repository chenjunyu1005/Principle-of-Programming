import java.util.LinkedList;

class BoolTerm {
	LinkedList<BoolTerm> boolterm;

	BoolTerm(LinkedList<BoolTerm> boollist)
	{
		boolterm = boollist;
	}
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + "<bool term>");
		for ( BoolTerm b : boolterm )
			b.printParseTree(indent+" ");
	}

}

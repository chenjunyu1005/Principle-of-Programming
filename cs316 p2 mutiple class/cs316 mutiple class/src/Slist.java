import java.util.*;

class Slist
{
	LinkedList<Statement> slist;

	Slist(LinkedList<Statement> al)
	{
		slist = al;
	}

	void printParseTree(String indent)
	{
		for ( Statement a : slist )
			a.printParseTree(indent);
	}

}

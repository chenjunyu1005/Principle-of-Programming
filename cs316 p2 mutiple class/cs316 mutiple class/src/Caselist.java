import java.util.LinkedList;

public class Caselist {
	LinkedList<Case> caselist;

	Caselist(LinkedList<Case> al)
	{
		caselist = al;
	}

	void printParseTree(String indent)
	{
		for ( Case a : caselist )
			a.printParseTree(indent);
	}

}

	





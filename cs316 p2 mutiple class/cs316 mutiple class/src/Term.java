import java.util.*;

class Term
{
	LinkedList<PrimaryItem> primaryItemList;

	Term(LinkedList<PrimaryItem> pItemList)
	{
		primaryItemList = pItemList;
	}
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <term>");
		for ( PrimaryItem p : primaryItemList )
			p.printParseTree(indent+" ");
	}



	// Evaluate a sequence of primaries operated by * or / using left associativity

	

	
}
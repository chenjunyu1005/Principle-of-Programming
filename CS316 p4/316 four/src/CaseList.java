import java.util.*;

class CaseList
{
	LinkedList<Case> caseList;

	CaseList(LinkedList<Case> cl)
	{
		caseList = cl;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <case list>");
		for ( Case c : caseList )
			c.printParseTree(indent+" ");
	}

	public void emitInstructions() {
		// TODO Auto-generated method stub
		for(Case c:caseList)
			c.emitInstructions();
	}
}
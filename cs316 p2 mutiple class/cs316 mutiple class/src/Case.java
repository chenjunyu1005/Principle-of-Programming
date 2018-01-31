import java.util.*;

public class Case { 
	Label label;
	Slist slist;
	Case(Label lb, Slist sl)
	{
		label=lb;
		slist=sl;
	}
	void printParseTree(String indent)
	{
		IO.display(indent + indent.length() + "case");
		indent = indent+" ";
	}

	
}


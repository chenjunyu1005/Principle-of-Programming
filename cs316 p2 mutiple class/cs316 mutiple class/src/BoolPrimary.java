import java.util.LinkedList;

public class BoolPrimary {
	LinkedList<BoolPrimary> boolprimary;

	BoolPrimary(LinkedList<BoolPrimary> boolprim)
	{
		boolprimary = boolprim;
	}
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + "<bool primary>");
		for ( BoolPrimary p : boolprimary )
			p.printParseTree(indent+" ");
	}

}


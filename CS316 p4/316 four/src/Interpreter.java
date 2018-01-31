/*
import java.util.*;

public abstract class Interpreter extends Parser
{
	public static HashMap<String, Val> varState = new HashMap<String, Val>(); 
	              // program state, i.e., virtual memory for variables
		
	
	public static void main(String argv[])
	{
		// argv[0]: input file containing an assignment list
		// argv[1]: output file displaying the parse tree
		
		setIO( argv[0], argv[1] );
		setLex();

		getToken();
		Statement statement = statement(); // build a parse tree
		if ( ! t.isEmpty() )
			displayln(t + " : Syntax Error, unexpected symbol where id expected");
		else if ( ! syntaxErrorFound )
		{
			statement.printParseTree("");       // print the parse tree in linearly indented form in argv[1] file
			//statement.M(varState);              // interpret the assignment list
			System.out.println(varState.toString()); // print the program state on the terminal
		}

		closeIO();
	}
}
*/
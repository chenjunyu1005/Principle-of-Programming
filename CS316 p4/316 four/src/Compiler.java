import java.util.HashMap;
import java.util.LinkedList;

public abstract class Compiler extends Parser
{
	public static final String indent = "\t";
	public static int varNum = 0; // sequential number of variables
	public static HashMap<String,Integer> varMap = new HashMap<String,Integer>(); // stores sequential numbers of variables
    public static int varlabel=0;
    public static LinkedList<Integer> list  = new LinkedList<Integer>();
    
    /*public static void findlabel(){
    	while()varlabel++;
    	if ()
       add.
    }
    */
	public static void main(String argv[])
	{
		// argv[0]: input file containing an assignment list
		// argv[1]: output file containing the compiled instruction stream
		
		setIO( argv[0], argv[1] );
		setLex();

		getToken();
		Statement statement = statement(); // build a parse tree
		if ( ! t.isEmpty() )
			displayln(t + " : Syntax Error, unexpected symbol where id expected");
		else if ( ! syntaxErrorFound  )
		{
			statement.emitInstructions();
		}

		closeIO();
	}
}

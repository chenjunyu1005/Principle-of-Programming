import java.util.*;

class Switch extends Statement{
	Expr expr;
	Caselist caselist;
	Switch(Expr exp,Caselist caselists)
	   {
		   expr = exp;
		   caselist =caselists;
	   
	   }
	
	public void printParseTree(String indent) 
	{
		Parser.displayln(indent + indent.length() + " <switch>");
}
}
import java.util.*;

class Expr extends Primary
{
	LinkedList<BoolTermItem> boolTermItemList;

	Expr(LinkedList<BoolTermItem> btItemList)
	{
		boolTermItemList = btItemList;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <Expr>");
		for ( BoolTermItem bt : boolTermItemList )
			bt.printParseTree(indent+" ");
	}

	 Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		Val exprVal=null;
		for ( BoolTermItem bt : boolTermItemList )		
		    exprVal=bt.Eval(state,exprVal);
		    return exprVal;
		
	}
	 void emitInstructions(){
		 for ( BoolTermItem bt : boolTermItemList )
			 bt.emitInstructions();
	 }
}

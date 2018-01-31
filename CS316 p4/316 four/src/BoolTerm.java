import java.util.*;

class BoolTerm 
{
	LinkedList<BoolPrimaryItem> boolPrimaryItemList;

	BoolTerm(LinkedList<BoolPrimaryItem> bpItemList)
	{
		boolPrimaryItemList = bpItemList;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <boolTerm>");
		for ( BoolPrimaryItem bp : boolPrimaryItemList )
			bp.printParseTree(indent+" ");
	}
	Val Eval(HashMap<String,Val> state){
// Evaluate a sequence of terms operated using"&& "left associativity
      Val booltermVal=null;
      for ( BoolPrimaryItem bp : boolPrimaryItemList )
    	 booltermVal=bp.Eval(state,booltermVal);
      return booltermVal;
	}

	void emitInstructions(){
		for ( BoolPrimaryItem bp : boolPrimaryItemList )
        bp.emitInstructions();
}
}


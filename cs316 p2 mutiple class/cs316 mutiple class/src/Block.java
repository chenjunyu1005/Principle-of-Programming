class Block {
	Slist slist;
	Block(Slist slis){
		slist=slis;
	}
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + "<s list>");
	}
}


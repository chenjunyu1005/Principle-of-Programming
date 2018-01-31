
class Whileloop {
Expr expr;
Statement statement;
Whileloop(Expr exp,Statement state){
expr=exp;
statement=state;
}
void printParseTree(String indent)
{
	IO.display(indent + indent.length() + " <while loop>");
}
}

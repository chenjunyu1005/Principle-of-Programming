 class BoolVal extends Val {
  boolean val;
  BoolVal(boolean b){
	  val=b;
  }
	@Override
	Val cloneVal() {
		// TODO Auto-generated method stub
		return new BoolVal(val); 
	}

	@Override
	float floatVal() {
		// TODO Auto-generated method stub
		if ( val )
			return 1.0f;
		else
			return 0.0f;
	}

	@Override
	boolean isZero() {
		// TODO Auto-generated method stub
		return false;
	}
	boolean isNumber()
	{
		return false;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return val+"";
	}

}

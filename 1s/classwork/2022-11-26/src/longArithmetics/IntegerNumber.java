class IntegerNumber extends Number {
	private boolean isNegative;
	public IntegerNumber(String num){
		super(num.substring(1));
		isNegative = (num.charAt(0) == '-') ? true : false;
	}
	public IntegerNumber(int[] num, int size) {
		IntegerNumber(num, size, false);
	}
	public IntegerNumber(int[] num, int size, boolean sign) {
		super(num, size);
		isNegative = sign;
	}
}

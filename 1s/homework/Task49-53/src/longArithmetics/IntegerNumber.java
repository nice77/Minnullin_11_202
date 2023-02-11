package longArithmetics;

class IntegerNumber extends Number {
	private final boolean isNegative;
	public IntegerNumber(String num){
		super(num.substring(1));
		isNegative = num.charAt(0) == '-';
	}
	public IntegerNumber(int[] num, int size, boolean sign) {
		super(num, size);
		isNegative = sign;
	}
	public IntegerNumber add(IntegerNumber other) {
		NaturalNumber first = new NaturalNumber(this.number, this.size);
		boolean firstSign = this.isNegative;
		NaturalNumber second = new NaturalNumber(other.number, other.size);
		boolean secondSign = other.isNegative;
		if (this.isNegative == other.isNegative) {
			NaturalNumber temp = first.add(second);
			return new IntegerNumber(temp.number, temp.size, this.isNegative);
		}
		NaturalNumber[] temp = NaturalNumber.sort(first, second);
		NaturalNumber maximum = temp[1];
		boolean maximumSign = (first == maximum) ? firstSign : secondSign;
		NaturalNumber minimum = temp[0];
		boolean minimumSign = !maximumSign;
		maximum = maximum.sub(minimum);
		return new IntegerNumber(maximum.number, maximum.size, maximumSign);
	}

	public boolean getNegative() {
		return isNegative;
	}
}

public RationalComplex {
	private RationalFraction a, b;
	public RationalComplex() {
		this(new RationalFraction(), new RationalFraction());
	}
	public RationalComplex(RationalFraction a, RationalFraction b) {
		this.a = a;
		this.b = b;
	}
	public RationalComplex add(RationalComplex other) {
		RationalComplex temp = new RationalComplex();
		temp.setRe(this.a.add(other.getRe()));
		temp.setIm(this.b.add(other.getIm()));
		return temp;
	}
	public RationalComplex sub(RationalComplex other) {
		RationalComplex temp = new RationalComplex();
		temp.setRe(this.a.sub(other.getRe()));
		temp.setIm(this.b.sub(other.getIm()));
		return temp;
	}
	public RationalComplex mul(RationalComplex other) {
		RationalComplex temp = new RationalComplex();
		temp.setRe(this.a * other.getRe() - this.b * other.getIm());
		temp.setIm(this.a * other.getIm() + other.getRe() * this.b);
		return temp;
	}
	public String toString() {
		return this.a + (this.b == 0 ? '' : ((this.b > 0) ? " + " + this.b : " - " + this.b));
	}
	public RationalFraction getRe() {
		return this.a;
	}
	public RationalFraction getIm() {
		return this.b;
	}
	public void setRe(RationalFraction val) {
		this.a = val;
	}
	public void setIm(RationalFraction val) {
		this.b = val;
	}
}

public class RationalComplexVector2D {
	private RationalComplex x, y;
	public RationalComplexVector2D() {
		this(new RationalComplex(), new RationalComplex());
	}
	public RationalComplexVector2D(RationalComplex a, RationalComplex b) {
		this.x = new RationalComplex(a.getRe(), a.getIm());
		this.y = new RationalComplex(b.getRe(), b.getIm());
	}
	public RationalComplexVector2D add(RationalComplexVector2D other) {
		RationalComplexVector2D temp = new RationalComplexVector2D();
		temp.setX(this.x.add(other.x));
		temp.setY(this.y.add(other.y));
		return temp;
	}
	public String toString() {
		return "(" + this.x.toString() + "; " + this.y.toString() + ")";
	}
	public RationalComplex scalarProduct(RationalComplexVector2D other) {
		RationalComplex temp = this.x.mul(other.x).add(this.y.mul(other.y));
		return temp;
	}
	public RationalComplex getX() {
		return this.x;
	}
	public RationalComplex getY() {
		return this.y;
	}
	public void setX(RationalComplex val) {
		this.x = val;
	}
	public void setY(RationalComplex val) {
		this.y = val;
	}
}

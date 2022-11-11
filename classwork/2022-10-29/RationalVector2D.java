public class RationalVector2D {
	private RationalFraction x, y;
	public RationalVector2D () {
		this(new RationalFraction(0, 1), new RationalFraction(0, 1));
	}
	public RationalVector2D (RationalFraction x, RationalFraction y) {
		this.x = x;
		this.y = y;
	}
	public RationalVector2D add (RationalVector2D other) {
		RationalVector2D temp = new RationalVector2D(this.x, this.y);
		temp.setX(temp.getX.add(other.getX()));
		temp.setY(temp.getY.add(other.getY()));
		return temp;
	}
	public String toString() {
		return this.x.toString() + " " + this.y.toString();
	}
	public double length() {
		RaionalFraction temp = this.x.sub(this.x).add(this.y.sub(this.y)));
		temp.setX(Math.pow(temp.getX, 0.5));
		temp.setY(Math.pow(temp.getY, 0.5));
		return temp.getX() / temp.getY();
	}
	public RationalFraction scalarProduct(RationalVector2D other) {
		RationalVector2D temp = new RationalVector2D(this.getX(), this.getY());
		temp.setX(this.x.mul(other.getX()));
		temp.setY(this.y.mul(other.getY()));
		return temp.getX.add(temp.getY());
	}
	public boolean equals(RationalVector2D other) {
		return this.x.equals(other.getX()) && this.y.equals(other.getY());
	}
	public RationalFraction getX() {
		return this.x;
	}
	public void setX(RationalFraction val) {
		this.x = val;
	}
	public void setY(RationalFraction val) {
		this.y = val;
	}
	public RationalFraction getY() {
		return this.y;
	}
}

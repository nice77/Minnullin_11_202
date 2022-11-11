public class ComplexVector2D {
	Complex x, y;
	public ComplexVector2D() {
		this(new Complex(), new Complex());
	}
	public ComplexVector2D(Complex x, Complex y) {
		this.x = x;
		this.y = y;
	}
	public ComplexVector2D add(ComplexVector2D other) {
		ComplexVector2D temp = new ComplexVector2D(this.x, this.y);
		temp.setX(temp.getX.add(other.getX));
		temp.setY(temp.getY.add(other.getY));
		return temp;
	}
	public String toString() {
		return this.x.toString() + " " + this.y.toString();
	}
	public Complex scalarProduct(ComplexVector2D other) {
		Complex x = this.x.mul(other.getX());
		Complex y = this.y.mul(other.getY());
		Complex temp = x.add(y);
		return temp;
	}
	public boolean equals(ComplexVector2D other) {
		return this.x.equals(other.getX()) && this.y.equals(other.getY());
	}
	public Complex getX() {
		return this.x;
	}
	public void setX(Complex other) {
		this.x = other;
	}
	public Complex getY() {
		return this.y;
	}
	public void setY(Complex other) {
		this.y = other;
	}
}

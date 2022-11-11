public class Complex {
	private double a, b;
	public Complex() {
		this(0, 0);
	}
	public Complex(double a, double b) {
		this.a = a;
		this.b = b;
	}

	public Complex add(Complex other) {
		double a = this.a + other.a;
		double b = this.b + other.b;
		return new Complex(a, b);
	}
	public void add2(Complex other) {
		this.a += other.a;
		this.b += other.b;
	}

	public Complex sub(Complex other) {
		double a = this.a - other.a;
		double b = this.b - other.b;
		return new Complex(a, b);
	}

	public void sub2(Complex other) {
		this.a -= other.a;
		this.b -= other.b;
	}
	public Complex mulNumber(double other) {
		double a = this.a * other;
		double b = this.b * other;
		return new Complex(a, b);
	}
	public void mulNumber2(double other) {
		this.a *= other;
		this.b *= other;
	}
	public Complex mul(Complex other) {
		double a = this.a * other.a - this.b * other.b;
		double b = this.a * other.b + other.a * this.b;
		return new Complex(a, b);
	}
	public void mul2(Complex other) {
		double tempA = this.a, tempB = this.b;
		this.a = tempA * other.a - tempB * other.b;
		this.b = tempA * other.b + other.a * tempB;
	}
	public Complex div(Complex other) {
		double tempA, tempB;
		tempA = (this.a * other.a + this.b * other.b) / (other.a * other.a + other.b * other.b);
		tempB = (other.a * this.b - this.a * other.b) / (other.a * other.a + other.b * other.b);
		return Complex(tempA, tempB);
	}
	public void div2(Complex other) {
		this.a = (this.a * other.a + this.b * other.b) / (other.a * other.a + other.b * other.b);
		this.b = (other.a * this.b - this.a * other.b) / (other.a * other.a + other.b * other.b);
	}
	public double length() {
		return Math.sqrt(this.x * this.x + this.y * this.y);
	}
	public double arg() {
		return Math.atan(b / a);
	}
	public ComplexNumber pow(double other) {
		double tempR = Math.pow(this.length(), other);
		double tempA = Math.cos(other * this.arg());
		double tempB = Math.sin(other * this.arg());
		return new Complex(tempR * tempA, tempR * tempB);
	}
	public boolean equals(Complex other) {
		return this.a == other.a && this.b == other.b;
	}

	public void setRe(double a) {
		this.a = a;
	}
	public void setIm(double b) {
		this.b = b;
	}
	public double getRe() {
		return this.a;
	}
	public double getIm() {
		return this.b;
	}
	public String toString() {
		return this.a + ((this.b > 0) ? " + " : " - ") + this.b + " * i";
	}
}

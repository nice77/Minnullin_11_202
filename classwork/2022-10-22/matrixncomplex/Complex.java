public class Complex {
	private double a, b;
	public Complex(double a, double b) {
		this.a = a;
		this.b = b;
	}

	public Complex add(Complex other) {
		double a = this.a + other.a;
		double b = this.b + other.b;
		return new Complex(a, b);
	}

	public Complex add(double other) {
		double a = this.a + other;
		return new Complex(a, this.b);
	}

	public Complex sub(Complex other) {
		double a = this.a - other.a;
		double b = this.b - other.b;
		return new Complex(a, b);
	}

	public Complex sub(double other) {
		double a = this.a - other;
		return new Complex(a, this.b);
	}

	public Complex mul(Complex other) {
		double a = this.a * other.a - this.b * other.b;
		double b = this.a * other.b + other.a * this.b;
		return new Complex(a, b);
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
		return this.a + " + " + this.b + " * i";
	}
}

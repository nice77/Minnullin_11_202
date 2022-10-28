public class Complex {
	private double a, b;
	public Complex(double a, double b) {
		this.a = a;
		this.b = b;
	}

	public void add(Complex other) {
		a += other.getRe();
		b += other.getIm();
	}
	public void add(int other) {
		b += other;
	}
	public void add(double other) {
		b += other;
	}

	public void sub(Complex other) {
		a -= other.getRe();
		b -= other.getIm();
	}

	public mul(Complex b) {}

	public getRe() {
		return this.a;
	}
	public getIm() {
		return this.b;
	}
}

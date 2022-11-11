public class Vector2D {
	private double x, y;
	public static final double EPS = 1e-7;
	public Vector2D() {
		this(0, 0);
	}
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vector2D add(Vector2D other) {
		return new Vector2D(this.x + other.x, this.y + other.y);
	}
	public void add2(Vector2D other) {
		this.x += other.x;
		this.y += other.y;
	}
	public Vector2D sub(Vector2D other) {
		return new Vector2D(this.x - other.x, this.y - other.y);
	}
	public void sub2(Vector2D other) {
		this.x -= other.x;
		this.y -= other.y;
	}
	public Vector2D mul(double delt) {
		return new Vector2D(this.x * delt, this.y * delt);
	}
	public void mul2(double delt) {
		this.x *= delt;
		this.y *= delt;
	}
	public String toString() {
		return "Vector's X & Y: " + this.x + ", " + this.y;
	}
	public double length() {
		return Math.sqrt(this.x * this.x + this.y * this.y);
	}
	public double scalarProduct(Vector2D other) {
		return (this.x * other.x) + (this.y * other.y);
	}
	public double cos(Vector2D other) {
		return this.scalarProduct(other) / (this.length() * other.length());
	}
	public boolean equals(Vector2D other) {
		return ((this.x - other.x < eps) && (this.y - other.y < eps));
	}
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
}

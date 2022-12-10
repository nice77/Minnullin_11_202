package control;

class Line {
	private double x1, x2, y1, y2;
	private final double EPS = 1e-7;
	public Line(double x1, double x2, double y1, double y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	public double getLength() {
		return Math.pow((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1), 0.5);
	}
	public double getRectPl() {
		return Math.abs(x2 - x1) * Math.abs(y2 - y1);
	}
	public boolean cross(Line other) {
		double k1 = (this.y2 - this.y1) / (this.x2 - this.x1);
		double b1 = this.y1 / (this.x1 * k1);
		double k2 = (other.y2 - other.y1) / (other.x2 - other.x1);
		double b2 = this.y2 / (this.x2 * k2);
		k1 -= k2;
		b2 -= b1;
		double xTemp = b2 / k1;
		if (xTemp < Math.min(this.x1, this.x2) || xTemp > Math.max(this.x1, this.x2) || xTemp < Math.min(other.x1, other.x2) || xTemp > Math.max(this.x1, this.x2)) {
			return false;
		}
		double yTemp = k1 * xTemp + b1;
		if (yTemp < Math.min(this.y1, this.y2) || yTemp > Math.max(this.y1, this.y2) || yTemp < Math.min(other.y1, other.y2) || yTemp > Math.max(this.y1, this.y2)) {
			return false;
		}
		return true;
	}
	public boolean equals(Line other) {
		return (this.x1 - other.x1 < EPS && this.x2 - other.x2 < EPS && this.y1 - other.y1 < EPS && this.y2 - other.y2 < EPS);
	}
	public String toString() {
		return "x1: " + this.x1 + " y1: " + this.y1 + " x2: " + this.x2 + " y2: " + this.y2;
	}
}

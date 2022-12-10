public class RationalFraction {
	private int x, y;
	public RationalFraction() {
		this(0, 1);
	}
	public RationalFraction(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public void reduce() {
		int tempA = this.x, tempB = this.y;
		while (tempA != 0 && tempB != 0) {
			if (tempA > tempB) {
				tempA %= tempB;
			}
			else {
				tempB %= tempA;
			}
		}
		this.x /= (tempA + tempB);
		this.y /= (tempA + tempB);
	}
	public RationalFraction add(RationalFraction other) {
		RationalFraction temp = new RationalFraction();
		if (this.y != other.y) {
			temp.x = this.x * other.y + other.x * this.y;
			temp.y = this.y * other.y;
		}
		else {
			temp.x = this.x + other.x;
		}
		temp.reduce();
		return temp;
	}
	public void add2(RationalFraction other) {
		if (this.y != other.y) {
			this.x = this.x * other.y + other.y * this.x;
			this.y = this.y * other.y;
		}
		else {
			this.x += other.x;
		}
	}
	public RationalFraction sub(RationalFraction other) {
		RationalFraction temp = new RationalFraction();
		if (this.y != other.y) {
			temp.x = this.x * other.y - other.x * this.y;
			temp.y = this.y * other.y;
		} else {
			temp.x = this.x - other.x;
		}
		temp.reduce();
		return temp;
	}
	public void sub2(RationalFraction other) {
		if (this.y != other.y) {
			this.x = this.x * other.y - other.x * this.y;
			this.y = this.y * other.y;
		}
        else {
			this.x -= other.x;
		}
		this.reduce();
	}
	public RationalFraction mul(RationalFraction other) {
		RationalFraction temp = new RationalFraction();
		temp.x = this.x * other.x;
		temp.y = this.y * other.y;
		temp.reduce();
		return temp;
	}
	public void mul2(RationalFraction other) {
		this.x *= other.x;
		this.y *= other.y;
	}
	public RationalFraction div(RationalFraction other) {
		RationalFraction temp = new RationalFraction();
		temp.x = this.x * other.y;
		temp.y = this.y * other.x;
		temp.reduce();
		return temp;
	}
	public void div2(RationalFraction other) {
		this.x = this.x * other.y;
		this.y = this.y * other.x;
	}
	public String toString() {
		return this.x + " / " + this.y;
	}
	public double value() {
		return (double) this.x / (double) this.y;
	}
	public boolean equals(RationalFraction other) {
		RationalFraction tempA = new RationalFraction(this.x, this.y);
		RationalFraction tempB = new RationalFraction(other.x, other.y);
		tempA.reduce();
		tempB.reduce();
		return tempA.x == tempB.x && tempA.y == tempB.y;
	}
	public int numberPart() {
		return this.x / this.y;
	}
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	public void setX(int val) {
		this.x = val;
	}
	public void setY(int val) {
		this.y = val;
	}
}

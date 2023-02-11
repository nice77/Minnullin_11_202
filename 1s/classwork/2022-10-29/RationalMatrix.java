import java.util.Arrays;

public class RationalMatrix {
	private RationalFraction[][] matrix = new RationalFraction[2][2];
	public RationalMatrix() {
		this(new RationalFraction(0, 0), new RationalFraction(0, 0), new RationalFraction(0, 0), new RationalFraction(0, 0));
	}
	public RationalMatrix(RationalFraction other) {
		this(other, other, other, other);
	}
	public RationalMatrix(RationalFraction uno, RationalFraction dos, RationalFraction tres, RationalFraction quatro) {
		this.matrix[0][0] = new RationalFraction(uno.getX, uno.getY);
		this.matrix[0][1] = new RationalFraction(dos.getX, dos.getY);
		this.matrix[1][0] = new RationalFraction(tres.getX, tres.getY);
		this.matrix[1][1] = new RationalFraction(quatro.getX, quatro.getY);
	}
	public RationalMatrix add(RationalMatrix other) {
		RationalMatrix temp = new RationalMatrix(Arrays.deepCopyOf(this.matrix));
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				temp.matrix[i][j].add2(other.getMatrix[i][j]);
			}
		}
		return temp;
	}
	public RationalMatrix mul(RationalMatrix other) {
		RationalMatrix temp = new RationalMatrix();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					temp.getMatrix()[i][j].add2(this.matrix[i][k].mul(other.getMatrix()[k][j]));
				}
			}
		}
		return temp;
	}
	public RationalFraction det() {
		return this.matrix[0][0].mul(this.matrix[1][1]).sub(this.matrix[0][1].mul(this.matrix[1][0]));
	}
	public RationalVector2D multVector(RationalVector2D other) {
		RationalVector2D temp = new RationalVector2D();
		temp.setX(this.matrix[0][0].mul(other.getX()) + this.matrix[0][1].mul(other.getY()));
		temp.setY(this.matrix[1][0].mul(other.getX()) + this.matrix[1][1].mul(other.getY()));
		return temp;
	}
	public RationalFraction[][] getMatrix() {
		return this.matrix;
	}
}

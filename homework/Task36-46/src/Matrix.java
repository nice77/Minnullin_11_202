import java.util.Arrays;

public class Matrix {
	private double[][] matrix = new double[2][2];

	public Matrix() {
		this(0);
	}
	public Matrix(double elem) {
		for (int i = 0; i < 4; i++) {
			this.matrix[i / 2][i % 2] = elem;
		}
	}
	public Matrix(double uno, double dos, double tres, double quatro) {
		this.matrix[0][0] = uno;
		this.matrix[0][1] = dos;
		this.matrix[1][0] = tres;
		this.matrix[1][1] = quatro;
	}
	public Matrix(double[] arr) {
		for (int i = 0; i < 4; i++) {
			this.matrix[i / 2][i % 2] = arr[i];
		}
	}
	public Matrix(double[][] arr) {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				this.matrix[i][j] = arr[i][j];
			}
		}
	}

	public Matrix add(Matrix other) {
		Matrix m = new Matrix();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				m.matrix[i][j] = this.matrix[i][j] + other.matrix[i][j];
			}
		}
		return m;
	}
	public void add2(Matrix other) {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				this.matrix[i][j] += other.matrix[i][j];
			}
		}
	}

	public Matrix sub(Matrix other) {
		Matrix m = new Matrix();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				m.matrix[i][j] = this.matrix[i][j] - other.matrix[i][j];
			}
		}
		return m;
	}
	public void sub2(Matrix other) {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				this.matrix[i][j] -= other.matrix[i][j];
			}
		}
	}

	public Matrix mulNumber(double other) {
		Matrix m = new Matrix();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				m.matrix[i][j] = this.matrix[i][j] * other;
			}
		}
		return m;
	}
	public void mulNumber2(double other) {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				this.matrix[i][j] *= other;
			}
		}
	}

	private double[][] mulHelper(Matrix other) {
		double[][] newMatrix = new double[2][2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				newMatrix[i][j] = 0;
			}
		}

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					newMatrix[i][j] += this.matrix[i][k] * other.matrix[k][j];
				}
			}
		}
		return newMatrix;
	}

	public Matrix mul(Matrix other) {
		return new Matrix(this.mulHelper(other));
	}

	public void mul2(Matrix other) {
		double[][] temp = this.mulHelper(other);
		for (int i = 0; i < 2; i++) {
			this.matrix[i] = Arrays.copyOf(temp[i], 2);
		}
	}

	public double det() {
		return this.matrix[0][0] * this.matrix[1][1] - this.matrix[0][1] * this.matrix[1][0];
	}
	public void transpon() {
		double[][] temp = new double[2][2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				temp[i][j] = this.matrix[j][i];
			}
		}
		this.matrix = temp;
	}
	public Matrix inverseMatrix() {
		Matrix tempM = new Matrix(this.matrix);
		double d = tempM.det();
		if (d == 0) {
			System.out.println("Determinant equals to zero!");
			return new Matrix();
		}
		tempM.transpon();
		double[][] temp = new double[2][2];
		for (int i = 0; i < 2; i++) {
			temp[i] = Arrays.copyOf(tempM.matrix[i], 2);
		}
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				tempM.matrix[i][j] = temp[(i + 1) % 2][(j + 1) % 2] / d;
			}
		}
		return tempM;
	}
	public Matrix equivalentDiagonal() {
		Matrix temp = new Matrix(this.matrix);
		temp.toDiag();
		temp.transpon();
		temp.toDiag();
		temp.transpon();
		return temp;
	}
	public void toDiag() {
		double tempUno = this.matrix[0][0], tempDos = this.matrix[1][0];
		for (int i = 0; i < 2; i++) {
			this.matrix[1][i] -= matrix[0][i] * tempDos / tempUno;
		}
	}
	public Vector2D multVector(Vector2D vctr) {
		Vector2D temp = new Vector2D();
		temp.setX(this.matrix[0][0] * vctr.getX() + this.matrix[0][1] * vctr.getY());
		temp.setY(this.matrix[1][0] * vctr.getX() + this.matrix[1][1] * vctr.getY());
		return temp;
	}
	public String toString() {
		String s = "";
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				s += this.matrix[i][j] + " ";
			}
			s += "\n";
		}
		return s;
	}
}

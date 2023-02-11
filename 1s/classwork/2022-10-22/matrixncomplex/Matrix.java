public class Matrix {
	private double[][] matrix = new double[2][2];

	public Matrix() {}
	public Matrix(double[] arr) {
		for (int i = 0; i < 4; i++) {
			this.matrix[i / 2][i % 2] = arr[i];
		}
	}

	public void add(Matrix other) {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				this.matrix[i][j] += other.matrix[i][j];
			}
		}
	}

	public void mul(Matrix other) {
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
		this.matrix = newMatrix;
	}

	public double det() {
		return this.matrix[0][0] * this.matrix[1][1] - this.matrix[0][1] * this.matrix[1][0];
	}

	public void toDiag() {
		double tempUno = this.matrix[0][0], tempDos = this.matrix[1][0];
		for (int i = 0; i < 2; i++) {
			this.matrix[1][i] -= matrix[0][i] * tempDos / tempUno;
		}
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

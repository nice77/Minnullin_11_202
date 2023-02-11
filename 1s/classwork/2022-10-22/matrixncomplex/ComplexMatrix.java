public class ComplexMatrix {
	private Complex[][] matrix = new Complex[2][2];
	public ComplexMatrix (Complex[] arr) {
		for (int i = 0; i < 4; i++) {
                        this.matrix[i / 2][i % 2] = arr[i];
                }
	}

	public void add(ComplexMatrix other) {
                for (int i = 0; i < 2; i++) {
                        for (int j = 0; j < 2; j++) {
                                this.matrix[i][j].add(other.matrix[i][j]);
                        }
                }
        }

        public Complex[][] mul(ComplexMatrix other) {
                Complex[][] newMatrix = new Complex[2][2];
                for (int i = 0; i < 2; i++) {
                        for (int j = 0; j < 2; j++) {
                                newMatrix[i][j] = new Complex(0, 0);
                        }
                }

                for (int i = 0; i < 2; i++) {
                        for (int j = 0; j < 2; j++) {
                                for (int k = 0; k < 2; k++) {
                                        newMatrix[i][j].add(this.matrix[i][k].mul(other.matrix[k][j]));
                                }
                        }
                }
		return newMatrix;
        }

        public Complex det() {
                return this.matrix[0][0].mul(this.matrix[1][1]).sub(this.matrix[0][1].mul(this.matrix[1][0]));
        }

	public void setMatrix (Complex[][] arr) {
		this.matrix = arr;
	}
	public String toString() {
                String s = "";
                for (int i = 0; i < 2; i++) {
                        for (int j = 0; j < 2; j++) {
                                s += this.matrix[i][j].toString() + " ";
                        }
                        s += "\n";
                }
                return s;
        }

}

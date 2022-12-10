class ComplexMatrix {
	private Complex[][] matrix = new Complex[2][2];
	public ComplexMatrix() {
		this(new Complex(0, 0), new Complex(0, 0), new Complex(0, 0), new Complex(0, 0));
	}
	public ComplexMatrix(Complex val) {
		this(val, val, val, val);
	}
	public ComplexMatrix(Complex uno, Complex dos, Complex tres, Complex quatro) {
		matrix[0][0] = new Complex(uno.getRe(), uno.getIm());
		matrix[0][1] = new Complex(dos.getRe(), dos.getIm());
		matrix[1][0] = new Complex(tres.getRe(), tres.getIm());
		matrix[1][1] = new Complex(quatro.getRe(), quatro.getIm());
	}
	public ComplexMatrix add(ComplexMatrix other) {
		ComplexMatrix temp = new ComplexMatrix();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				temp.getMatrix()[i][j] = this.matrix[i][j].add(other.getMatrix()[i][j]);
			}
		}
		return temp;
	}
	public ComplexMatrix mul(ComplexMatrix other) {
		ComplexMatrix temp = new COmplexMatrix();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					temp.getMatrix()[i][j].add2(this.matrix[i][k].mul(other.getMatrix()[k][j]));
				}
			}
		}
		return temp;
	}
	public Complex[][] getMatrix() {
		return this.matrix;
	}
}

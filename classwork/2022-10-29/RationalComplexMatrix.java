public RationalComplexMatrix {
	private RationalComplex[][] matrix = new RationalComplex[2][2];
	public RationalComplexMatrix() {
		RationalComplex zero = new RationalComplex();
		this(zero, zero, zero, zero);
	}
	public RationalComplexMatrix(RationalComplex val) {
		this(val, val, val, val)
	}
	public RationalComplexMatrix(RationalComplex uno, RationalComplex dos, RationalComplex tres, RationalComplex quatro) {
		matrix[0][0] = new RationalComplex(uno.getRe(), uno.getIm());
		matrix[0][1] = new RationalComplex(dos.getRe(), dos.getIm());
		matrix[1][0] = new RationalComplex(tres.getRe(), tres.getIm());
		matrix[1][1] = new RationalComplex(quatro.getRe(), quatro.getIm());
	}
	public RationalComplexMatrix add(RationalComplexMatrix other) {
		RationalComplexMatrix temp = new RationalComplexMatrix();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				temp.getMatrix()[i][j].add2(this.matrix[i][j].add(other.getMatrix()[i][j]));
			}
		}
		return temp;
	}
	public RationalComplexMatrix mul(RationalComplexMatrix other) {
		RationalComplexMatrix temp = new RatonalComplexMatrix();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					temp.getMatrix()[i][j].add2(this.matrix[i][k].mul(other.getMatrix()[k][j]));
				}
			}
		}
		return temp;
	}
	public RationalComplex det() {
		return this.matrix[0][0].mul(this.matrix[1][1]).sub(this.matrix[1][0].mul(this.matrix[0][1]));
	}
	public RationalComplexVector2D multVector(RationalComplexVector2D other) {
		RationalComplexVector2D temp = new RationalComplexVector2D();
		temp.setX(this.matrix[0][0].mul(other.getX()) + this.matrix[0][1].mul(other.getY()));
		temp.setY(this.matrix[1][0].mul(other.getX()) + this.matrix[1][1].mul(other.getY()));
		return temp;
	}
	public RationalComplex[][] getMatrix() {
		return this.matrix;
	}
}

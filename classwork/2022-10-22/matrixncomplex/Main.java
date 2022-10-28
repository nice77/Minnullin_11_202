public class Main {
	public static void main(String[] args) {
		Complex compUno = new Complex(4, 4.5);
		Complex compDos = new Complex(7, 7);
		System.out.println(compUno.toString());
		System.out.println(compDos.toString());
		compUno.mul(compDos);
		System.out.println(compUno.toString());

		double[] data = {1, 2, 3, 4};
		Matrix m = new Matrix(data);
		System.out.println(m.toString());
		Matrix n = new Matrix(data);
		m.mul(n);
		System.out.println(m.toString());
		n.toDiag();
		System.out.println(n.toString());

		Complex[] dataDos = {new Complex(1, 0), new Complex(2, 0), new Complex(3, 0), new Complex(4, 0)};
		ComplexMatrix cmUno = new ComplexMatrix(dataDos);
		ComplexMatrix cmDos = new ComplexMatrix(dataDos);
		System.out.println(cmUno.toString());
		cmUno.setMatrix(cmUno.mul(cmDos));
		System.out.println(cmUno.toString());
	}
}

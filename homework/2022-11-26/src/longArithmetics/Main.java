package longArithmetics;

public class Main {
	public static void main(String[] args) {
		NaturalNumber a = new NaturalNumber("13749");
		System.out.println(a);
		NaturalNumber b = new NaturalNumber("1124");
		System.out.println(b);
		System.out.println(a.mul(b));
	}
}

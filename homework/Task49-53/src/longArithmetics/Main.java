package longArithmetics;

public class Main {
	public static void main(String[] args) {
		NaturalNumber a = new NaturalNumber("55555");
		System.out.println(a);
		NaturalNumber b = new NaturalNumber("11385");
		System.out.println(b);
		System.out.println(a.mul(b));
	}
}

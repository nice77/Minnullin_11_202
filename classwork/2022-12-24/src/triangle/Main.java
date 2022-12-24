package triangle;

public class Main {
    public static void main(String[] args) {
        Triangle uno = new Triangle(0, 0, 4, 0, 2, 6);
        System.out.println(uno.perimeter());
        System.out.println(uno.baseCalc());
        System.out.println(uno.plosh());
        Triangle dos = new Triangle(0, 0, 4, 0, 2, - 6);
        System.out.println(uno.equals(dos));

        Triangle[] triangles = new Triangle[3];
        triangles[0] = uno;
        triangles[1] = dos;
        triangles[2] = new Triangle(0, 0, 6, 0, 3, 4);
        System.out.println(TriangleMassive.plosh(triangles));
        System.out.println(TriangleMassive.atLeastTwoEquals(triangles));
    }
}

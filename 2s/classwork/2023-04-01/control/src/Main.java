import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[] {1, 5, 0, 3, 2, 0, 66, 0, 0, 0};
        VectorCode code = new VectorCode(arr);
        System.out.println(code.getList());
        System.out.println(Arrays.toString(code.decode()));
        code.insert(33, 5);
        System.out.println(code.getList());
        System.out.println(Arrays.toString(code.decode()));
        code.delete(5);
        System.out.println(code.getList());
        System.out.println(Arrays.toString(code.decode()));

        int[] vectorUno = new int[] {1, 0, 4};
        int[] vectorDos = new int[] {1, 0};
        VectorCode uno = new VectorCode(vectorUno);
        System.out.println(uno.getList());
        VectorCode dos = new VectorCode(vectorDos);
        System.out.println(dos.getList());
        System.out.println(uno.scalarProduct(dos));
        System.out.println(uno.sum(dos).getList());
        System.out.println(uno.getList());
        System.out.println(uno.vectorSum().getList());
    }
}

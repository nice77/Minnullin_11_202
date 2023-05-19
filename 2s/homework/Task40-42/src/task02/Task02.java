package classwork.task02;

public class Task02 {
    public static void main(String[] args) {
        ProxyIntArrayCollection proxyArr = new ProxyIntArrayCollection();
        for (int i = 0; i < 10; i++) {
            proxyArr.add((int) (Math.random() * 100));
        }
        System.out.println(proxyArr.size());
    }
}

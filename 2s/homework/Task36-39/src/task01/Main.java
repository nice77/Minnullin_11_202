package task01;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Scanner;

// input whole package
// !String -> java.lang.String
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String className = sc.nextLine();
        try {
            Class<?> cl = Class.forName(className);
            HashSet<String> nayms = new HashSet<>();
            for (Method m : cl.getMethods()) {
                nayms.add(m.getName());
            }
            System.out.println(nayms);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

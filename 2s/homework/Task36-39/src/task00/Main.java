package task00;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Class<AnnClass> cl = AnnClass.class;
        Author annotation = cl.getAnnotation(Author.class);
        System.out.println(annotation.val());
    }
}

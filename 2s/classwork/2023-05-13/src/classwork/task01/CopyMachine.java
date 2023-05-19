package classwork.task01;

import javafx.concurrent.Task;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class CopyMachine<T> {
    private final T obj;
    public CopyMachine(T obj) {
        this.obj = obj;
    }
    public T copy() {
        Class<T> copyClass = (Class<T>) obj.getClass();
        try {
            T instance = copyClass.newInstance();
            String neym;
            for (Method method : copyClass.getDeclaredMethods()) {
                if (method.getName().startsWith("get")) {
                    neym = "set" + method.getName().substring(3);
                    copyClass.getMethod(neym, method.getReturnType()).invoke(instance, method.invoke(this.obj));
                }
            }
            return instance;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}

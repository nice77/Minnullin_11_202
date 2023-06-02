package task03;

import task03.markers.After;
import task03.markers.Before;
import task03.markers.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestRunner {
    private Class cl;
    public TestRunner(Class cl) {
        this.cl = cl;
    }

    public void run() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        List<Method> methods = Arrays.asList(this.cl.getDeclaredMethods());


        List<Method> beforeMethods = methods.stream()
                .filter(m -> Arrays.asList(m.getDeclaredAnnotations())
                        .stream()
                        .filter(a -> a instanceof Before)
                        .collect(Collectors.toList())
                        .size() != 0)
                .collect(Collectors.toList());

        List<Method> testMethods = methods.stream()
                .filter(m -> Arrays.asList(m.getDeclaredAnnotations())
                        .stream()
                        .filter(a -> a instanceof Test)
                        .collect(Collectors.toList())
                        .size() != 0)
                .collect(Collectors.toList());

        List<Method> afterMethods = methods.stream()
                .filter(m -> Arrays.asList(m.getDeclaredAnnotations())
                        .stream()
                        .filter(a -> a instanceof After)
                        .collect(Collectors.toList())
                        .size() != 0)
                .collect(Collectors.toList());

        System.out.println("@Before methods: " + beforeMethods);
        System.out.println("@Test methods: " + testMethods);
        System.out.println("@After methods" + afterMethods);

        for (Method test : testMethods) {
            TestCases testCases = (TestCases) this.cl.newInstance();
            methodCaller(testCases, beforeMethods);
            System.out.println("Calling " + test.getName());
            try {
                test.invoke(testCases);
                System.out.println("Test succeeded!");
            } catch (InvocationTargetException | AssertionError e) {
                System.out.println("Test failed!");
            }
            methodCaller(testCases, afterMethods);
        }
    }

    private void methodCaller(TestCases tc, List<Method> methods) {
        for (Method method : methods) {
            try {
                method.invoke(tc);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

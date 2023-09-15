package com.example.demo;

public class Helper {
    public static String calculate(Integer a, Integer b, String operation) {
        String res = "";
        switch (operation) {
            case "*":
                res = String.valueOf(a * b);
                break;
            case "/":
                res = (b == 0) ? "Infinity" : String.valueOf(a / b);
                break;
            case "+":
                res = String.valueOf(a + b);
                break;
            case "-":
                res = String.valueOf(a - b);
                break;
        }
        return res;
    }
}

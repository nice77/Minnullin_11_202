package com.example.semester.utils;

public class StringService {
    public static String getClassName(String name) {
        String out;
        out = name.substring(0, name.length() - 1).replace("ie", "y");
        out = out.substring(0, 1).toUpperCase() + out.substring(1);
        return out;
    }

    // method getAttributeName is used to process class attribute name from table column
    public static String getAttributeName(String name) {
        String out;
        int foundIndexOfSpace = name.indexOf('_');
        out = name.replace(String.valueOf(name.charAt(foundIndexOfSpace)), "");
        out = out.substring(0, foundIndexOfSpace) +
                out.substring(foundIndexOfSpace, foundIndexOfSpace + 1).toUpperCase() +
                out.substring(foundIndexOfSpace + 1);
        return out;
    }

    public static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    // getColumnName is used to process column name from class attribute name
    public static String getColumnName(String name) {
        for (int i = 0; i < name.length(); i++) {
            char letter = name.charAt(i);
            if (65 <=  letter && letter <= 90) {
                name = name.replace(String.valueOf(name.charAt(i)), "_" + (char) (letter + 32));
                System.out.println("Replaced: " + name);
            }
        }
        return name;
    }


    public static String getTableName(String classPath) {
        String[] classPathSplit = classPath.split("\\.");
        String className = classPathSplit[classPathSplit.length - 1].toLowerCase();
        if (className.charAt(className.length() - 1) == 'y') {
            className = className.replace(className.charAt(className.length() - 1) + "", "ie");
        }
        className += "s";
        return className;
    }
}

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        responser();
    }

    public static void responser() {
        List<String> userJson = Converter.fromCSVtoJSON();
        List<User> userList = Converter.userObjectCreator();
        Scanner sc = new Scanner(System.in);
        String command;
        while (true) {
            command = sc.nextLine();
            if (command.startsWith("/user")){
                List<String> arr = Arrays.asList(command.split("\\?"));
                if (arr.size() == 1) {
                    System.out.println(userList);
                }
                else {
                    arr = Arrays.asList(arr.get(1).split("&"));
                    Class<User> classUser = User.class;
                    List<String[]> filters = arr.stream().map(u -> u.split("=")).collect(Collectors.toList());
                    List<User> out = new ArrayList<>();
                    if (filters.get(0)[0].equals("format")) {
                        factoryPageCreator(filters.get(0)[1], userList);
                    }
                    else {
                        for (User user : userList) {
                            try {
                                Method method = classUser.getDeclaredMethod("get" + filters.get(0)[0].substring(0, 1).toUpperCase() + filters.get(0)[0].substring(1));
                                if (method.invoke(user).toString().equals(filters.get(0)[1])) {
                                    out.add(user);
                                }
                            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        if (filters.size() != 1) {
                            String format = filters.get(1)[1];
                            factoryPageCreator(format, out);
                        }
                    }
                }
            }
            else { break; }
        }
    }

    public static String factoryPageCreator(String format, List<User> out) {
        String page = "";
        if (format.equals("console")) {
            out.forEach(System.out::println);
        }
        else if (format.equals("html")) {
            return htmlTemplate(out);
        }
        else if (format.equals("md")) {
            return mdTemplate(out);
        }
        return null;
    }

    public static String htmlTemplate(List<User> out) {
        String page = "";
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream("out.html"));
            Class<User> userClass = User.class;
            page += "<ul>\n";
            for (User user : out) {
                page += "   <li>\n";
                for (Field field : userClass.getDeclaredFields()) {
                    page += "<h4>" + field.getName() + ": ";
                    try {
                        Method m = userClass.getDeclaredMethod("get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1));
                        page += m.invoke(user) + "</h4>\n";
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
                page += "   </li>\n";
            }
            page += "</ul>";
            pw.write(page);
            pw.flush();
            return page;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static String mdTemplate(List<User> out) {
        String page = "";
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream("out.md"));
            Class<User> userClass = User.class;
            for (User user : out) {
                page += "* ";
                for (Field field : userClass.getDeclaredFields()) {
                    page += field.getName() + ": ";
                    try {
                        Method m = userClass.getDeclaredMethod("get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1));
                        page += m.invoke(user) + ";\n";
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
                page += "\n";
            }
            pw.write(page);
            pw.flush();
            return page;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

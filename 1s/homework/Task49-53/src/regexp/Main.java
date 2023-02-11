package regexp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pattern email = Pattern.compile("[a-zA-Z][a-zA-Z0-9-_.]*@g*mail\\.com");
        Pattern surname = Pattern.compile("([^аоуыеёэияюАОУЫЕЁИЯЮA-Za-z0-9]*[аоуыеёэияю]?){0,3}(ова|ина)");
        Matcher match;
        String txt;
        do {
            txt = sc.nextLine();
            match = email.matcher(txt);
            System.out.println("Input text is an email: " + match.matches());
            match = surname.matcher(txt);
            System.out.println("Input text is an surname: " + match.matches());
        } while (!txt.equals("Stop"));
    }
}

import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Task04 {
    public static void main(String[] args) {
        String url = "https://en.wikipedia.org/wiki/London", wayToFile = "assets/task04/out.html";
        Task03.copyHTML(url, wayToFile);
        findAndDownload(wayToFile);
    }

    public static void findAndDownload(String wayToFile) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(wayToFile)));
            String i;
            Pattern p = Pattern.compile("src=\".+\\.jpg\"");
            while ((i = br.readLine()) != null) {
                Arrays.stream(i.split(" "))
                        .filter(str -> p.matcher(str).matches())
                        .map(str -> str.substring(5, str.length() - 1))
                        .forEach(str -> Task02.copyPIC("https:" + str, "assets/task04/pics/" + str.substring(str.lastIndexOf('/'))));
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

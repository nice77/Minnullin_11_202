import java.io.*;
import java.net.URL;

public class Task03 {
    public static void main(String[] args) {
        copyHTML("https://en.wikipedia.org/wiki/London", "assets/task03/out.html");
    }
    public static void copyHTML(String url, String wayToFile) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader((new URL(url)).openStream()));
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(wayToFile)));
            String i;
            while ((i = br.readLine()) != null) {
                pw.write(i + "\n");
            }
            br.close();
            pw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

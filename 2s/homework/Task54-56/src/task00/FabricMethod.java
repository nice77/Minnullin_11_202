package classwork.task00;

import java.io.*;
import java.net.URL;

public class FabricMethod {
    public static File getFile(String url) {
        try {
            URL link = new URL(url);
            String[] path = url.split("/");
            String naym = "assets/" + path[path.length - 1] + ".txt";
            File f = new File(naym);
            if (!f.exists()) {
                BufferedReader br = new BufferedReader(new InputStreamReader(link.openStream()));
                PrintWriter pw = new PrintWriter(naym);
                String i;
                while ((i = br.readLine()) != null) {
                    pw.write(i + "\n");
                }
                br.close();
                pw.close();
            }
            return f;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package task04;

import java.io.*;
import java.net.URL;

public class Service {
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

    public static void copyPIC(String naym, String wayToFile) {
        try {
            URL file = new URL(naym);
            InputStream pic = file.openStream();
            FileOutputStream fos = new FileOutputStream(wayToFile);
            int i;
            while ((i = pic.read()) != -1) {
                fos.write(i);
            }
            pic.close();
            fos.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

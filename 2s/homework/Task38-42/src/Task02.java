import java.io.*;
import java.net.URL;

public class Task02 {
    public static void main(String[] args) {
        copyPIC("https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Richard_Dawkins_%282009%29.jpg/440px-Richard_Dawkins_%282009%29.jpg", "assets/task02/out.jpg");
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

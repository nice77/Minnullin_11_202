import java.io.*;

public class Task00 {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("assets/task00/cat.jpg");
            FileOutputStream fos = new FileOutputStream("assets/task00/catOut.jpg");
            int i;
            while ((i = fis.read()) != -1) {
                fos.write(i);
            }
            fis.close();
            fos.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

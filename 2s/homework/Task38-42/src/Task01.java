import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Task01 {
    public static void main(String[] args) {
        zipper();
    }
    public static void zipper() {
        try {
            Map<Character, Integer> coder = codeCreator();
            int codeSize = (int) Math.ceil(Math.log10(coder.keySet().size()) / Math.log10(2));
            int i;
            System.out.println(coder);
            while ((Math.log(codeSize) / Math.log(2)) - (int) (Math.log(codeSize) / Math.log(2)) > 1e-8) {
                codeSize++;
            }
            System.out.println("Applied codeSize: " + codeSize);

            FileInputStream fis = new FileInputStream("assets/task01/input.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            FileOutputStream fos = new FileOutputStream("assets/task01/output.compress");
            byte pack = 0;
            int steps = 0, requiredSteps = 8 / codeSize;
            while ((i = isr.read()) != -1) {
                pack <<= codeSize;
                pack += coder.get((char) i);
                steps++;
                if (steps == requiredSteps) {
                    fos.write(pack);
                    steps = 0;
                }
            }
            fos.write(pack);
            fis.close();
            isr.close();
            fos.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static Map<Character, Integer> codeCreator() {
        try {
            FileInputStream fis = new FileInputStream("assets/task01/input.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            int i;
            Map<Character, Integer> coder = new HashMap<>();
            int cnt = 0;
            while ((i = isr.read()) != -1) {
                if (!coder.containsKey((char) i)) {
                    coder.put((char) i, cnt);
                    cnt++;
                }
            }
            fis.close();
            isr.close();
            return coder;
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

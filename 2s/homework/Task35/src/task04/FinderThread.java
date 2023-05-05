package task04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Queue;
import java.util.regex.Pattern;

public class FinderThread extends Thread {
    private String url;
    private final Queue<String> addresses;

    public FinderThread(String url, Queue<String> addresses) {
        this.url = url;
        this.addresses = addresses;
    }

    public void run() {
        synchronized (this.addresses) {
            System.out.println("Started finder");
            String wayToFile = "assets/out.html";
            Service.copyHTML(this.url, wayToFile);
            finder(wayToFile);
        }
    }

    public void finder(String wayToFile) {
        synchronized (this.addresses) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(wayToFile)));
                String i;
                Pattern p = Pattern.compile("src=\".+\\.jpg\"");
                while ((i = br.readLine()) != null) {
                    Arrays.stream(i.split(" "))
                            .filter(str -> p.matcher(str).matches())
                            .map(str -> str.substring(5, str.length() - 1))
                            .forEach(str -> this.addresses.add("https:" + str));
//                    System.out.println("Added: " + this.addresses);
                    if (this.addresses.size() != 0) {
                        System.out.println("Size is not zero - notifying");
                        this.addresses.notify();
                    }
                    while (this.addresses.size() != 0) {
                        this.addresses.wait();
                    }
                }
                br.close();
                System.out.println("All pics were found - ending thread...");
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                this.addresses.add("Stop");
                this.addresses.notify();
            }
        }
    }
}

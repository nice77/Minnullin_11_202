package task04;
import java.util.Queue;

public class DownloaderThread extends Thread{
    private final Queue<String> addresses;

    public DownloaderThread(Queue<String> addresses) {
        this.addresses = addresses;
    }

    public void run() {
        synchronized (this.addresses) {
            System.out.println("Started downloader");
            System.out.println("Addresses: " + this.addresses);
            while (true) {
                while (this.addresses.size() == 0) {
                    try {
                        System.out.println("Downloader is waiting...");
                        this.addresses.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException();
                    }
                }
                String str;
                while (this.addresses.size() != 0) {
                    str = this.addresses.poll();
                    if (str.equals("Stop")) {
                        System.out.println("All pics downloaded");
                        return;
                    }
                    Service.copyPIC(str, "assets/pics/" + str.substring(str.lastIndexOf('/')));
                    System.out.println("Downloading: " + str);
                }
                this.addresses.notify();
            }
        }
    }
}

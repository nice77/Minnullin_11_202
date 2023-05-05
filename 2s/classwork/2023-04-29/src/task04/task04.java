package task04;

import java.util.LinkedList;
import java.util.Queue;

public class task04 {
    public static void main(String[] args) {
        Queue<String> addresses = new LinkedList<>();
        FinderThread finder = new FinderThread("https://en.wikipedia.org/wiki/London", addresses);
        DownloaderThread downloader = new DownloaderThread(addresses);
        downloader.start();
        finder.start();
//        Service.copyHTML("https://en.wikipedia.org/wiki/London", "assets/out.html");
    }
}

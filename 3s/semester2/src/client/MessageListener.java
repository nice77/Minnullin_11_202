package client;

import java.io.BufferedReader;
import java.io.IOException;

public class MessageListener extends Thread {

    private BufferedReader br;

    public MessageListener(BufferedReader br) {
        this.br = br;
    }

    @Override
    public void run() {
        String event;
        while (true) {
            try {
                event = br.readLine();
                System.out.println(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

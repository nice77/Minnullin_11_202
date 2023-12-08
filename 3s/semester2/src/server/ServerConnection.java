package server;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class ServerConnection extends Thread {

    private UUID uuid;
    private Socket socket;
    private BufferedReader br;
    private BufferedWriter bw;
    private SendEventToOther sendEventToOtherImpl;

    public ServerConnection(UUID uuid, Socket socket, SendEventToOther sendEventToOtherImpl) {
        this.uuid = uuid;
        this.socket = socket;
        try {
            this.br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.bw = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.sendEventToOtherImpl = sendEventToOtherImpl;
        this.start();
    }

    public void sendMessage(String message) {
        try {
            bw.write(message + "\n");
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        String event;
        while (true) {
            try {
                event = br.readLine();
                this.sendEventToOtherImpl.sendMessage(uuid, event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

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
    private CheckIfRoomIsCreated checkIfRoomIsCreated;
    private GetPositionInRoom getPositionInRoom;

    public ServerConnection(UUID uuid,
                            Socket socket,
                            SendEventToOther sendEventToOtherImpl,
                            CheckIfRoomIsCreated checkIfRoomIsCreated,
                            GetPositionInRoom getPositionInRoom) {
        this.uuid = uuid;
        this.socket = socket;
        try {
            this.br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.bw = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.sendEventToOtherImpl = sendEventToOtherImpl;
        this.checkIfRoomIsCreated = checkIfRoomIsCreated;
        this.getPositionInRoom = getPositionInRoom;
        this.start();
    }

    public void sendMessageToClient(String message) {
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
        this.checkIfRoomIsCreated.checkIfRoomIsCreated(this.uuid);
        while (true) {
            try {
                event = br.readLine();
                System.out.println("Server: received - " + event);
                if (event.split("=")[0].equals("player")) {
                    int position = this.getPositionInRoom.getPositionInRoom(this.uuid, this);
                    String message = event.replace("=", position + "=");
                    this.sendEventToOtherImpl.sendMessage(this.uuid, message);
                    bw.write("playable=" + this.getPositionInRoom.getPositionInRoom(this.uuid, this) + "\n");
                    bw.flush();
                }
                else {
                    this.sendEventToOtherImpl.sendMessage(uuid, event);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

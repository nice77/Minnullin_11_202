package server;

import client.ActionTypes;

import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.UUID;

public class ServerConnection extends Thread {
    private UUID uuid;
    private Socket socket;
    private BufferedReader br;
    private BufferedWriter bw;
    private SendEventToOther sendEventToOtherImpl;
    private CheckIfRoomIsCreated checkIfRoomIsCreated;
    private GetPositionInRoom getPositionInRoom;
    private DestroyRoom destroyRoom;

    public ServerConnection(UUID uuid,
                            Socket socket,
                            SendEventToOther sendEventToOtherImpl,
                            CheckIfRoomIsCreated checkIfRoomIsCreated,
                            GetPositionInRoom getPositionInRoom,
                            DestroyRoom destroyRoom) {
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
        this.destroyRoom = destroyRoom;
        this.setDaemon(true);
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

    public void closeSocket() {
        try {
            this.socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        String event;
        this.checkIfRoomIsCreated.checkIfRoomIsCreated(this.uuid);
        loop: while (true) {
            try {
                event = this.br.readLine();
                Map<String, String> eventParsed = Service.parser(event);
//                System.out.println(event);
//                System.out.println(eventParsed);
//                System.out.println("----------");

                ActionTypes actionType = ActionTypes.valueOf(eventParsed.get("actionType"));
                switch (actionType) {
                    case PLAYER_SELECTED:
                        int position = this.getPositionInRoom.getPositionInRoom(this.uuid, this);
                        String eventUpdated = event + "&position=" + position;
                        this.sendEventToOtherImpl.sendMessage(this.uuid, eventUpdated);
                        String eventForCurrentClient = "actionType=" + ActionTypes.PLAYER_CURRENT_SELECTED + "&playable=" + position;
                        this.sendMessageToClient(eventForCurrentClient);
                        break;
                    case GAME_OVER:
                        System.out.println(eventParsed);
                        this.sendMessageToClient(event);
                        this.closeSocket();
                        this.destroyRoom.destroyRoom(this.uuid);
                        break loop;
                    default:
                        this.sendEventToOtherImpl.sendMessage(this.uuid, event);
                        break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            System.out.println("Joining connection");
            this.join();
            System.out.println("Jonied connection");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket() {
        return socket;
    }
}

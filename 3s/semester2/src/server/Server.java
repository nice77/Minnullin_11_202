package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.*;

public class Server {

    private Map<UUID, List<ServerConnection>> roomList;
    private ServerSocket serverSocket;

    public Server() {
        try {
            this.serverSocket = new ServerSocket(8000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.roomList = new HashMap<>();
    }

    public void start() {
        try {
            while (true) {
                UUID uuid = UUID.randomUUID();
                List<ServerConnection> room =
                        createRoom (
                                new ServerConnection(
                                        uuid,
                                        serverSocket.accept(),
                                        this::sendMessage,
                                        this::checkIfRoomIsCreated,
                                        this::getPositionInRoom
                                ),
                                new ServerConnection(
                                        uuid,
                                        serverSocket.accept(),
                                        this::sendMessage,
                                        this::checkIfRoomIsCreated,
                                        this::getPositionInRoom
                                )
                        ) ;
                this.roomList.put(uuid, room);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<ServerConnection> createRoom(ServerConnection... serverConnections) {
        return new LinkedList<>(Arrays.asList(serverConnections));
    }

    public void sendMessage(UUID roomId, String message) {
        List<ServerConnection> room = this.roomList.get(roomId);
        room.forEach(item -> item.sendMessageToClient(message));
    }

    public void checkIfRoomIsCreated(UUID uuid) {
        List<ServerConnection> room = this.roomList.get(uuid);
        if (room != null) {
            room.forEach(item -> item.sendMessageToClient("created=true"));
        }
    }

    public int getPositionInRoom(UUID uuid, ServerConnection serverConnection) {
        List<ServerConnection> room = roomList.get(uuid);
        System.out.println("Server: position - " + room.indexOf(serverConnection));
        return room.indexOf(serverConnection);
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}

package client.controller.loadingScreen;

import java.net.Socket;

public class LoadingController {

    private Socket socket;

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }
}

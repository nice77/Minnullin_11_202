package client;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class InputHandler implements EventHandler<KeyEvent> {
    private boolean isPressed;
    private SendEventToServer sendEventToServer;
    public InputHandler(boolean isPressed, SendEventToServer sendEventToServer) {
        this.isPressed = isPressed;
        this.sendEventToServer = sendEventToServer;
    }
    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case D:
                this.sendEventToServer.sendEventToServer("isPressed="+this.isPressed+"&key=D");
                break;
            case A:
                this.sendEventToServer.sendEventToServer("isPressed="+this.isPressed+"&key=A");
                break;
            case E:
                this.sendEventToServer.sendEventToServer("isPressed="+this.isPressed+"&key=E");
        }
    }
}

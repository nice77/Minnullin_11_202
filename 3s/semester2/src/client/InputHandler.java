package client;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
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
        this.sendEventToServer.sendEventToServer("isPressed="+this.isPressed+"&key="+event.getCode().name());
    }
}

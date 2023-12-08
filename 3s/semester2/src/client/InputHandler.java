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
                this.sendEventToServer.sendEventToServer("Sent D");
//                System.out.println(this.isPressed + " D - input handler");
                break;
            case A:
                this.sendEventToServer.sendEventToServer("Sent A");
//                System.out.println(this.isPressed + " A - input handler");
                break;
        }
    }
}

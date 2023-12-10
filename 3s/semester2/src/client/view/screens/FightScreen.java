package client.view.screens;

import client.InputHandler;
import client.Resources;
import client.assets.characters.Characters;
import client.controller.fightScreen.FightController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

public class FightScreen extends AbstractScreen {
    public FightScreen(Stage stage) {
        this.fxmlLoader = new FXMLLoader(getClass().getResource(Resources.FIGHT_SCREEN));
        try {
            this.scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.primaryStage = stage;
        initTriggers();
    }

    @Override
    protected void initTriggers() {
        FightController fightController = this.fxmlLoader.getController();
        this.scene.setOnKeyPressed(new InputHandler(true, fightController::sendEvent));
        this.scene.setOnKeyReleased(new InputHandler(false, fightController::sendEvent));
    }

    public void setControllerSocket(Socket socket) {
        ((FightController) this.fxmlLoader.getController()).setSocket(socket);
    }
}

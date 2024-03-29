package client.view.screens;

import client.InputHandler;
import client.Resources;
import client.assets.characters.Characters;
import client.controller.fightScreen.FightController;
import client.view.ScreenFactory;
import client.view.ScreenTypes;
import javafx.application.Platform;
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
        String stylesheet = getClass().getResource(Resources.WINDOW_STYLE).toExternalForm();
        this.scene.getStylesheets().add(stylesheet);
        initTriggers();
    }

    @Override
    protected void initTriggers() {
        FightController fightController = this.fxmlLoader.getController();
        this.scene.setOnKeyPressed(new InputHandler(true, fightController::sendEvent));
        this.scene.setOnKeyReleased(new InputHandler(false, fightController::sendEvent));
        ((FightController) this.fxmlLoader.getController()).getIsGameOver().addListener((observable, oldValue, newValue) -> {
                setSelectScreen();
            }
        );
    }

    public void setControllerSocket(Socket socket) {
        ((FightController) this.fxmlLoader.getController()).setSocket(socket);
    }

    public void setSelectScreen() {
        ScreenFactory screenFactory = new ScreenFactory();
        AbstractScreen screen = screenFactory.getNewScreen(this.primaryStage, ScreenTypes.SELECT);
        Platform.runLater(() -> this.primaryStage.setScene(screen.getScene()));
    }
}

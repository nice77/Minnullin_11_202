package client.view.screens;

import client.*;
import client.controller.characterSelectScreen.CharacterSelectController;
import client.controller.fightScreen.FightController;
import client.view.ScreenFactory;
import client.view.ScreenTypes;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class SelectScreen extends AbstractScreen {

    private Socket socket;

    public SelectScreen(Stage stage) {
        this.fxmlLoader = new FXMLLoader(getClass().getResource(Resources.CHARACTER_SELECT_SCREEN));
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
        this.scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                ScreenFactory screenFactory = new ScreenFactory();
                AbstractScreen screen = screenFactory.getNewScreen(this.primaryStage, ScreenTypes.LOADING);

                try {
                    this.socket = new Socket("5.tcp.eu.ngrok.io", 16611);
                    launchMessageListener(this.socket);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("Character we selected: " + ((CharacterSelectController) this.fxmlLoader.getController()).getSelectedCharacter());

                this.primaryStage.setScene(screen.getScene());
            }
        });
    }

    public void setFightingScene(MessageListener messageListener) {
        ScreenFactory screenFactory = new ScreenFactory();
        FightScreen screen = (FightScreen) screenFactory.getNewScreen(this.primaryStage, ScreenTypes.FIGHT);

        FightController fightController = screen.getFxmlLoader().getController();

        messageListener.setHandleControllerInput(fightController::receiveInfo);
        screen.setControllerSocket(this.socket);
        Platform.runLater(() -> this.primaryStage.setScene(screen.getScene()));
    }

    public void launchMessageListener(Socket socket) {
        MessageListener messageListener;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String selectedCharacterName = ((CharacterSelectController) this.fxmlLoader.getController()).getSelectedCharacter().name();
            messageListener = new MessageListener(
                    br,
                    this::setFightingScene,
                    this::sendInfoAboutSelectedPlayer,
                    selectedCharacterName
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        messageListener.start();
    }

    public void sendInfoAboutSelectedPlayer(String character) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            String message = "actionType=" + ActionTypes.PLAYER_SELECTED + "&player=" + character;
            bw.write(message + "\n");
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

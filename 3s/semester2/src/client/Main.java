package client;

import client.view.characterSelectScreen.CharacterSelectController;
import client.view.fightScreen.FightController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

public class Main extends Application {
    private FXMLLoader menuSceneLoader;
    private Scene menuScene;
    private FXMLLoader selectSceneLoader;
    private Scene selectScene;
    private FXMLLoader fightSceneLoader;
    private Scene fightScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initLoadersAndScene();
        initMenuSceneTriggers(primaryStage);
        initSelectSceneTriggers(primaryStage);
        initFightSceneTriggers();
        initPrimaryStage(primaryStage);
    }

    public void initLoadersAndScene() {
        try {
            this.menuSceneLoader = new FXMLLoader(getClass().getResource(Resources.START_SCREEN));
            this.menuScene = new Scene(this.menuSceneLoader.load());
            this.selectSceneLoader = new FXMLLoader(getClass().getResource(Resources.CHARACTER_SELECT_SCREEN));
            this.selectScene = new Scene(this.selectSceneLoader.load());
            this.fightSceneLoader = new FXMLLoader(getClass().getResource(Resources.FIGHT_SCREEN));
            this.fightScene = new Scene(this.fightSceneLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.menuScene.getStylesheets().add(Resources.WINDOW_STYLE);
    }

    public void initMenuSceneTriggers(Stage primaryStage) {
        this.menuScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                primaryStage.setScene(this.selectScene);
            }
        });
    }

    public void initSelectSceneTriggers(Stage primaryStage) {
        this.selectScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                CharacterSelectController characterSelectController = selectSceneLoader.getController();

                characterSelectController.sendInfo();

                FightController fightController = fightSceneLoader.getController();
                try {
                    fightController.setSocket(new Socket("localhost", 8000));
                    fightController.updateReaderWriter();
                    fightController.launchListener();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                primaryStage.setScene(this.fightScene);
            }
        });
    }

    public void initFightSceneTriggers() {
        FightController fightController = this.fightSceneLoader.getController();
        this.fightScene.setOnKeyPressed(new InputHandler(true, fightController::sendEvent));
        this.fightScene.setOnKeyReleased(new InputHandler(false, fightController::sendEvent));
    }

    public void initPrimaryStage(Stage primaryStage) {
        Font.loadFont(
                getClass().getResource(Resources.DPCOMIC_FONT).toExternalForm(),
                Resources.DPCOMIC_FONT_SIZE);

        primaryStage.setTitle(Resources.WINDOW_TITLE);
        primaryStage.setScene(this.menuScene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}

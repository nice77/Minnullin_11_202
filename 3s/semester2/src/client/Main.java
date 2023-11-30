package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    private Parent menuSceneRoot;
    private Scene menuScene;
    private Parent selectSceneRoot;
    private Scene selectScene;
    private Parent fightSceneRoot;
    private Scene fightScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.menuSceneRoot = FXMLLoader.load(getClass().getResource(Resources.START_SCREEN));
        this.menuScene = new Scene(this.menuSceneRoot);
        this.selectSceneRoot = FXMLLoader.load(getClass().getResource(Resources.CHARACTER_SELECT_SCREEN));
        this.selectScene = new Scene(this.selectSceneRoot);
        this.fightSceneRoot = FXMLLoader.load(getClass().getResource(Resources.FIGHT_SCREEN));
        this.fightScene = new Scene(this.fightSceneRoot);


        this.menuScene.getStylesheets().add(Resources.WINDOW_STYLE);
        this.menuScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                primaryStage.setScene(this.selectScene);
            }
        });

        this.selectScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                primaryStage.setScene(this.fightScene);
            }
        });

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

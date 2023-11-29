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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.menuSceneRoot = FXMLLoader.load(getClass().getResource(Resources.START_SCREEN));
        this.menuScene.getStylesheets().add(Resources.START_SCREEN_STYLE);
        System.out.println(KeyCode.ENTER);
        this.menuScene.setOnKeyPressed(event -> {
            // switching scene logic
        });

        System.out.println(Font.loadFont(
                getClass().getResource(Resources.DPCOMIC_FONT).toExternalForm(),
                Resources.DPCOMIC_FONT_SIZE));

        primaryStage.setTitle(Resources.WINDOW_TITLE);
        primaryStage.setScene(this.menuScene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}

package client.view.screens;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class AbstractScreen {
    protected FXMLLoader fxmlLoader;
    protected Scene scene;
    protected Stage primaryStage;
    protected abstract void initTriggers();

    public FXMLLoader getFxmlLoader() {
        return fxmlLoader;
    }

    public Scene getScene() {
        return scene;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}

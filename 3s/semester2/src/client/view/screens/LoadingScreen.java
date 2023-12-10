package client.view.screens;

import client.Resources;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

public class LoadingScreen extends AbstractScreen {
    public LoadingScreen(Stage stage) {
        this.fxmlLoader = new FXMLLoader(getClass().getResource(Resources.LOADING_SCREEN));
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
    protected void initTriggers() {}
}

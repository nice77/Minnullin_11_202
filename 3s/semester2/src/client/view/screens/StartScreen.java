package client.view.screens;

import client.Resources;
import client.view.ScreenFactory;
import client.view.ScreenTypes;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class StartScreen extends AbstractScreen {

    public StartScreen(Stage stage) {
        this.fxmlLoader = new FXMLLoader(getClass().getResource(Resources.START_SCREEN));
        try {
            this.scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.primaryStage = stage;
        String stylesheet = getClass().getResource(Resources.WINDOW_STYLE).toExternalForm();
        scene.getStylesheets().add(stylesheet);
        initTriggers();
    }

    @Override
    protected void initTriggers() {
        ScreenFactory screenFactory = new ScreenFactory();
        this.scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                AbstractScreen screen = screenFactory.getNewScreen(this.primaryStage, ScreenTypes.SELECT);
                primaryStage.setScene(screen.getScene());
            }
        });
    }
}

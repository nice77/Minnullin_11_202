package client;

import client.view.ScreenFactory;
import client.view.ScreenTypes;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Font.loadFont(
                getClass().getResource(Resources.DPCOMIC_FONT).toExternalForm(),
                Resources.DPCOMIC_FONT_SIZE);
        primaryStage.setTitle(Resources.WINDOW_TITLE);
        ScreenFactory screenFactory = new ScreenFactory();
        primaryStage.setScene(screenFactory.getNewScreen(primaryStage, ScreenTypes.START).getScene());
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}

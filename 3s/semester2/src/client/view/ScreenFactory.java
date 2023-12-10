package client.view;

import client.view.screens.*;
import javafx.stage.Stage;

public class ScreenFactory {
    public AbstractScreen getNewScreen(Stage stage, ScreenTypes screenType) {
        switch (screenType){
            case START:
                return new StartScreen(stage);
            case SELECT:
                return new SelectScreen(stage);
            case FIGHT:
                return new FightScreen(stage);
            default:
                return new LoadingScreen(stage);
        }
    }
}

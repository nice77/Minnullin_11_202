package client.controller.fightScreen;

import client.Resources;
import client.SelectedCharacters;
import client.Service;
import client.controller.SpriteAnimation;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.Map;

public class FightController {

    @FXML private ImageView bgImageView;
    @FXML private ImageView fgImageView;
    @FXML private ImageView mainPlayerImageView;
    @FXML private ImageView otherPlayerImageView;
    @FXML private Rectangle leftHealthBarGreen;
    @FXML private Rectangle rightHealthBarGreen;
    private Socket socket;
    private BufferedWriter bw;
    private BufferedReader br;

    private PlayerAndSprite leftPlayer;
    private PlayerAndSprite rightPlayer;


    @FXML
    public void initialize() {
        this.leftPlayer = new PlayerAndSprite(
                SelectedCharacters.mainPlayer,
                new SpriteAnimation(
                        new Duration(750),
                        this.mainPlayerImageView,
                        SelectedCharacters.mainPlayer.getCharacter()
                ),
                true
        );
        this.rightPlayer = new PlayerAndSprite(
                SelectedCharacters.otherPlayer,
                new SpriteAnimation(
                        new Duration(750),
                        this.otherPlayerImageView,
                        SelectedCharacters.otherPlayer.getCharacter()
                ),
                false
        );

        this.leftPlayer.getPlayer().getHpProperty().addListener((observable, newValue, oldValue) ->
                Platform.runLater(() -> this.leftHealthBarGreen.setWidth(2 * newValue.doubleValue()))
        );
        this.rightPlayer.getPlayer().getHpProperty().addListener((observable, newValue, oldValue) ->
                Platform.runLater(() -> this.rightHealthBarGreen.setWidth(2 * newValue.doubleValue()))
        );

        bgImageView.setImage(new Image(Resources.ARENA_01));
        bgImageView.setFitWidth(Resources.WINDOW_WIDTH);
        bgImageView.setFitHeight(Resources.WINDOW_HEIGHT);
    }

    public void receiveInfo(String event) {
        Map<String, String> eventParsed = Service.parser(event);
        PlayerAndSprite selected = eventParsed.get("playable").equals("left") ?  this.leftPlayer : this.rightPlayer;
        switch (eventParsed.get("key")) {
            case "A":
                selected.checkAndChangeState(eventParsed);
                if (selected.getPlayer().getState() == States.WALK) {
                    selected.move(false);
                }
                break;
            case "D":
                selected.checkAndChangeState(eventParsed);
                if (selected.getPlayer().getState() == States.WALK) {
                    selected.move(true);
                }
                break;
            case "E":
                selected.getPlayer().setState((eventParsed.get("isPressed").equals("true")) ? States.BLOCK : States.IDLE);
                selected.updatePlayerSprite();
                break;
            case "Q":
                if (eventParsed.get("isPressed").equals("true") && selected.getPlayer().getState() != States.HIT) {
                    selected.setState(States.HIT);
                    SpriteAnimation selectedSpriteAnimation = selected.getSpriteAnimation();
                    selectedSpriteAnimation.stop();
                    selectedSpriteAnimation.setRate(3);
                    selectedSpriteAnimation.setOnFinished(e -> {
                        selectedSpriteAnimation.setRate(-3);
                        selectedSpriteAnimation.play();
                        selectedSpriteAnimation.setOnFinished(e1 -> {
                            selected.setState(States.IDLE);
                            selectedSpriteAnimation.setRate(1);
                            selectedSpriteAnimation.changeProperties(selected.getPlayer().getCharacter(), selected.getPlayer().getState().name());
                            selectedSpriteAnimation.play();
                        });
                    });
                    selectedSpriteAnimation.changeProperties(selected.getPlayer().getCharacter(), selected.getPlayer().getState().name());
                    selectedSpriteAnimation.play();

                    PlayerAndSprite nonSelected = (selected.isLeft()) ? this.rightPlayer : this.leftPlayer;
                    double intersectionVal = selected.getPlayersIntersection(nonSelected);
                    boolean isIntersecting = selected.checkIntersection(intersectionVal);
                    if (nonSelected.getPlayer().getState() != States.BLOCK && isIntersecting) {
                        selected.hit(nonSelected);
                        boolean toRight = !nonSelected.isLeft();
                        nonSelected.moveInjured(toRight);
                    }
                }
                break;
        }
    }

    public void sendEvent(String event) {
        try {
            bw.write(event + "&playable=" + SelectedCharacters.playable.getName() + "\n");
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
        updateReaderWriter();
    }

    public void updateReaderWriter() {
        try {
            this.bw = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            this.br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

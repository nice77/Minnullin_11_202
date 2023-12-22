package client.controller.fightScreen;

import client.*;
import client.controller.SpriteAnimation;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.*;
import java.net.Socket;
import java.util.Map;

public class FightController {

    @FXML private ImageView bgImageView;
    @FXML private ImageView mainPlayerImageView;
    @FXML private ImageView otherPlayerImageView;
    @FXML private Rectangle leftHealthBarGreen;
    @FXML private Rectangle rightHealthBarGreen;
    private Socket socket;
    private BufferedWriter bw;
    private BufferedReader br;

    private PlayerAndSprite leftPlayer;
    private PlayerAndSprite rightPlayer;
    private BooleanProperty isGameOver;
    private MediaPlayer soundPlayer;


    @FXML
    public void initialize() {
        this.soundPlayer = new MediaPlayer(new Media(new File(Resources.FIGHT).toURI().toString()));
        this.isGameOver = new SimpleBooleanProperty(false);

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

        this.leftPlayer.getPlayer().getHpProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> this.leftHealthBarGreen.setWidth(2 * newValue.doubleValue())));
        this.rightPlayer.getPlayer().getHpProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> this.rightHealthBarGreen.setWidth(2 * newValue.doubleValue())));

        bgImageView.setImage(new Image(Resources.ARENA_01));
        bgImageView.setFitWidth(Resources.WINDOW_WIDTH);
        bgImageView.setFitHeight(Resources.WINDOW_HEIGHT);
        soundPlayer.setOnEndOfMedia(() -> {
            this.soundPlayer = new MediaPlayer(new Media(new File(Resources.PUNCH).toURI().toString()));
        });
        soundPlayer.play();
    }

    public void receiveInfo(String event) {
        Map<String, String> eventParsed = Service.parser(event);
        if (ActionTypes.valueOf(eventParsed.get("actionType")) == ActionTypes.GAME_OVER) {
            System.out.println("Closing socket");
            this.closeSocket();
            this.isGameOver.set(true);
            return;
        }

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
                    selected.updatePlayerAnimationOnHit(true);
                    PlayerAndSprite nonSelected = (selected.isLeft()) ? this.rightPlayer : this.leftPlayer;
                    double intersectionVal = selected.getPlayersIntersection(nonSelected);
                    boolean isIntersecting = selected.checkIntersection(intersectionVal);

                    if (nonSelected.getPlayer().getState() != States.BLOCK && isIntersecting) {
                        this.soundPlayer.play();
                        this.soundPlayer.setOnEndOfMedia(() -> this.soundPlayer = new MediaPlayer(new Media(new File(Resources.PUNCH).toURI().toString())));
                        nonSelected.setState(States.INJURED);
                        nonSelected.updatePlayerAnimationOnHit(true);
                        selected.hit(nonSelected);
                        if (nonSelected.getPlayer().getHp() != 0) {
                            boolean toRight = !nonSelected.isLeft();
                            nonSelected.moveInjured(toRight);
                        }
                        else {
                            selected.setState(States.VICTORY);
                            selected.updatePlayerAnimationOnHit(false);
                            nonSelected.setState(States.DEAD);
                            nonSelected.updatePlayerAnimationOnHit(false);
                            gameOver();
                        }
                    }
                }
                break;
        }
    }

    public void gameOver() {
        try {
            PauseTransition delay = new PauseTransition(Duration.seconds(5));
            delay.setCycleCount(1);
            delay.setOnFinished(event1 -> {
                System.out.println("Sending GAME_OVER");
                this.sendEvent("actionType=" + ActionTypes.GAME_OVER);
            });
            delay.play();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sendEvent(String event) {
        try {
            System.out.println("Sending: " + event + "&playable=" + SelectedCharacters.playable.getName());
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

    public void closeSocket() {
        try {
            this.socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BooleanProperty getIsGameOver() {
        return this.isGameOver;
    }
}

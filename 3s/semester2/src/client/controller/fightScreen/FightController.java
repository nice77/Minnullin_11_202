package client.controller.fightScreen;

import client.Resources;
import client.SelectedCharacters;
import client.Service;
import client.assets.characters.Characters;
import client.controller.SpriteAnimation;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class FightController {

    @FXML private ImageView bgImageView;
    @FXML private ImageView fgImageView;
    @FXML private ImageView mainPlayerImageView;
    @FXML private ImageView otherPlayerImageView;

    private SpriteAnimation mainPlayerAnimation;
    private Player mainPlayer;
    private SpriteAnimation otherPlayerAnimation;
    private Player otherPlayer;
    private Socket socket;
    private BufferedWriter bw;
    private BufferedReader br;
    private SpriteAnimation playablePlayerAnimation;
    private Player playable;

    @FXML
    public void initialize() {

        this.mainPlayer = SelectedCharacters.mainPlayer;
        this.otherPlayer = SelectedCharacters.otherPlayer;
        this.mainPlayerImageView.setLayoutX(this.mainPlayer.getPos().get());
        this.otherPlayerImageView.setLayoutX(this.otherPlayer.getPos().get());

        System.out.println("!!!!");
        System.out.println("mainPlayerImageView position: " + this.mainPlayerImageView.getLayoutX());
        System.out.println("otherPlayerImageView position: " + this.otherPlayerImageView.getLayoutX());
        System.out.println("mainPlayer position: " + this.mainPlayer.getPos().get());
        System.out.println("otherPlayer position: " + this.otherPlayer.getPos().get());
        System.out.println("!!!!");


        this.mainPlayer.getPos().addListener((observable, newValue, oldValue) ->
                {
                    System.out.println("Changing left to " + newValue.doubleValue());
                    Platform.runLater(() -> this.mainPlayerAnimation.getImageView().setLayoutX(newValue.doubleValue()));
                }
        );
        this.otherPlayer.getPos().addListener((observable, newValue, oldValue) ->
                {
                    System.out.println("Changing right to " + newValue.doubleValue());
                    Platform.runLater(() -> this.otherPlayerAnimation.getImageView().setLayoutX(newValue.doubleValue()));
                }
        );
        this.otherPlayerImageView.setScaleX(-1);

        this.playable = SelectedCharacters.playable;
        System.out.println("Playable: " + this.playable);
        this.mainPlayerAnimation = setPlayerAnimation(this.mainPlayerImageView, this.mainPlayer);
        this.otherPlayerAnimation = setPlayerAnimation(this.otherPlayerImageView, this.otherPlayer);
        this.playablePlayerAnimation = this.playable == this.mainPlayer ? this.mainPlayerAnimation : this.otherPlayerAnimation;
        System.out.println("playable animation: " + this.playablePlayerAnimation);

        bgImageView.setImage(new Image(Resources.ARENA_01));
        bgImageView.setFitWidth(Resources.WINDOW_WIDTH);
        bgImageView.setFitHeight(Resources.WINDOW_HEIGHT);
    }

    public SpriteAnimation setPlayerAnimation(ImageView imageView, Player player) {
        imageView.setFitWidth(Resources.SPRITE_ANIMATION_WIDTH);
        imageView.setFitHeight(Resources.SPRITE_ANIMATION_HEIGHT);
        SpriteAnimation spriteAnimation = new SpriteAnimation(
                new Duration(750),
                imageView,
                player.getCharacter()
        );
        spriteAnimation.play();
        return spriteAnimation;
    }

    public void receiveInfo(String event) {
        Map<String, String> eventParsed = Service.parser(event);
        System.out.println("Parsed: " + eventParsed);

        Player selected = eventParsed.get("playable").equals("left") ?  this.mainPlayer : this.otherPlayer;
        SpriteAnimation selectedPlayerAnimation = eventParsed.get("playable").equals("left") ?
                this.mainPlayerAnimation : this.otherPlayerAnimation;

        switch (eventParsed.get("key")) {
            case "A":
                if (selected.getState() == States.WALK) {
                    selected.move(false);
                }
                checkAndChangeState(eventParsed, selected, selectedPlayerAnimation);
                break;
            case "D":
                if (selected.getState() == States.WALK) {
                    selected.move(true);
                }
                checkAndChangeState(eventParsed, selected, selectedPlayerAnimation);
                break;
            case "E":
                if (eventParsed.get("isPressed").equals("false")) {
                    selected.setState(States.IDLE);
                }
                else {
                    selected.setState(States.BLOCK);
                }
                updatePlayerSprite(selectedPlayerAnimation, selected);
                break;
        }
        System.out.println("--------------");
        System.out.println("mainPlayerImageView position: " + this.mainPlayerImageView.getLayoutX());
        System.out.println("otherPlayerImageView position: " + this.otherPlayerImageView.getLayoutX());
        System.out.println("mainPlayer position: " + this.mainPlayer.getPos().get());
        System.out.println("otherPlayer position: " + this.otherPlayer.getPos().get());
        System.out.println("--------------");
    }

    // only for WALK and IDLE!
    private void checkAndChangeState(Map<String, String> eventParsed, Player selected, SpriteAnimation selectedPlayerAnimation) {
        if (selected.getState() != States.WALK && eventParsed.get("isPressed").equals("true")) {
            selected.setState(States.WALK);
            updatePlayerSprite(selectedPlayerAnimation, selected);
        }
        else if (eventParsed.get("isPressed").equals("false")) {
            selected.setState(States.IDLE);
            updatePlayerSprite(selectedPlayerAnimation, selected);
        }
    }

    public void sendEvent(String event) {
        try {
            bw.write(event + "&playable=" + playable.getName() + "\n");
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

    public void updatePlayerSprite(SpriteAnimation spriteAnimation, Player player) {
        spriteAnimation.stop();
        spriteAnimation.changeProperties(player.getCharacter(), player.getState().name());
        spriteAnimation.play();
    }
}

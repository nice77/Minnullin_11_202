package client.view.fightScreen;

import client.MessageListener;
import client.Resources;
import client.assets.characters.Characters;
import client.view.SpriteAnimation;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.*;
import java.net.Socket;

public class FightController {

    @FXML private ImageView bgImageView;
    @FXML private ImageView fgImageView;
    @FXML private ImageView playerImageView;
    private Player player;
    private Socket socket;
    private BufferedWriter bw;
    private BufferedReader br;

    @FXML
    public void initialize() {
        this.player = new Player("Noize", 10, Characters.SCORPION);

        this.playerImageView.setFitWidth(Resources.SPRITE_ANIMATION_WIDTH);
        this.playerImageView.setFitHeight(Resources.SPRITE_ANIMATION_HEIGHT);
        SpriteAnimation spriteAnimation = new SpriteAnimation(
                new Duration(1000),
                this.playerImageView,
                this.player.getCharacter()
        );
        spriteAnimation.play();
        receiveInfo();
        bgImageView.setImage(new Image(Resources.ARENA_01));
        bgImageView.setFitWidth(Resources.WINDOW_WIDTH);
        bgImageView.setFitHeight(Resources.WINDOW_HEIGHT);
    }

    public void receiveInfo() {

    }

    public void sendEvent(String event) {
        try {
            bw.write(event + "\n");
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void updateReaderWriter() {
        try {
            this.bw = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            this.br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void launchListener() {
        MessageListener messageListener = new MessageListener(this.br);
        messageListener.start();
    }
}

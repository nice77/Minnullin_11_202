package client.controller.fightScreen;

import client.Resources;
import client.controller.SpriteAnimation;
import javafx.application.Platform;
import javafx.scene.image.ImageView;

import java.util.Map;

public class PlayerAndSprite {

    private Player player;
    private SpriteAnimation spriteAnimation;
    private boolean isLeft;

    private static final double INTERSECTION_WIDTH = 30.0;

    public PlayerAndSprite(Player player, SpriteAnimation spriteAnimation, boolean isLeft) {
        this.player = player;
        this.spriteAnimation = spriteAnimation;
        this.isLeft = isLeft;
        this.spriteAnimation.getImageView().setLayoutX(this.player.getPos().get());
        this.player.getPos().addListener((observable, newValue, oldValue) ->
                Platform.runLater(() -> this.spriteAnimation.getImageView().setLayoutX(newValue.doubleValue()))
        );
        if (!this.isLeft) {
            this.spriteAnimation.getImageView().setScaleX(-1);
        }

        ImageView imageView = this.spriteAnimation.getImageView();
        imageView.setFitWidth(Resources.SPRITE_ANIMATION_WIDTH);
        imageView.setFitHeight(Resources.SPRITE_ANIMATION_HEIGHT);
        this.spriteAnimation.play();
    }

    public void hit(PlayerAndSprite other) {
        this.player.hit(other.getPlayer());
    }

    public void updatePlayerSprite() {
        this.spriteAnimation.stop();
        this.spriteAnimation.changeProperties(this.player.getCharacter(), this.player.getState().name());
        this.spriteAnimation.play();
    }

    public void setState(States state) {
        this.player.setState(state);
    }

    public void stop() {
        Platform.runLater(() -> {
            this.spriteAnimation.stop();
        });
    }

    public void play() {
        Platform.runLater(() -> {
            this.spriteAnimation.play();
        });
    }

    public void checkAndChangeState(Map<String, String> eventParsed) {
        if (this.player.getState() != States.WALK && eventParsed.get("isPressed").equals("true")) {
            this.player.setState(States.WALK);
            if (this.player.getName().equals("left") && eventParsed.get("key").equals("A")
                    || this.player.getName().equals("right") && eventParsed.get("key").equals("D")) {
                this.spriteAnimation.setRate(-1);
            }
            else {
                this.spriteAnimation.setRate(1);
            }
            updatePlayerSprite();
        }
        else if (eventParsed.get("isPressed").equals("false")) {
            this.player.setState(States.IDLE);
            this.spriteAnimation.setRate(1);
            updatePlayerSprite();
        }
    }

    public void move(boolean toRight) {
        this.player.move(toRight);
        Platform.runLater(() -> this.spriteAnimation.getImageView().setLayoutX(this.player.getPos().get()));
    }

    public void moveInjured(boolean toRight) {
        this.player.moveInjured(toRight);
        Platform.runLater(() -> this.spriteAnimation.getImageView().setLayoutX(this.player.getPos().get()));
    }

    public void updatePlayerAnimationOnHit(boolean isReturning) {
        SpriteAnimation selectedSpriteAnimation = this.getSpriteAnimation();
        this.stop();
        if (isReturning) {
            selectedSpriteAnimation.setRate(3);
            selectedSpriteAnimation.setOnFinished(e -> {
                selectedSpriteAnimation.setRate(-3);
                selectedSpriteAnimation.play();
                selectedSpriteAnimation.setOnFinished(e1 -> {
                    this.setState(States.IDLE);
                    selectedSpriteAnimation.setRate(1);
                    selectedSpriteAnimation.changeProperties(this.player.getCharacter(), this.player.getState().name());
                    selectedSpriteAnimation.play();
                });
            });
        }
        else {
            selectedSpriteAnimation.setRate(0.7);
            selectedSpriteAnimation.setOnFinished(null);
        }
        selectedSpriteAnimation.changeProperties(this.player.getCharacter(), this.player.getState().name());
        this.play();
    }

    public double getPlayersIntersection(PlayerAndSprite other) {
        SpriteAnimation thisSpriteAnimation = this.spriteAnimation;
        SpriteAnimation otherSpriteAnimation = other.spriteAnimation;
        double thisX;
        double otherX;
        if (this.isLeft) {
            thisX = thisSpriteAnimation.getImageView().getBoundsInParent().getMaxX();
            otherX = otherSpriteAnimation.getImageView().getBoundsInParent().getMinX();
            if ((thisX - otherX - INTERSECTION_WIDTH > 1e-8)) {
                return thisX - otherX;
            }
        }
        else {
            thisX = thisSpriteAnimation.getImageView().getBoundsInParent().getMinX();
            otherX = otherSpriteAnimation.getImageView().getBoundsInParent().getMaxX();
            if (otherX - thisX - INTERSECTION_WIDTH > 1e-8) {
                return otherX - thisX;
            }
        }
        return 0;
    }

    public boolean checkIntersection(double intersection) {
        return (intersection - INTERSECTION_WIDTH) > 0;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isLeft() {
        return isLeft;
    }

    public void setLeft(boolean left) {
        isLeft = left;
    }

    public SpriteAnimation getSpriteAnimation() {
        return spriteAnimation;
    }

    public void setSpriteAnimation(SpriteAnimation spriteAnimation) {
        this.spriteAnimation = spriteAnimation;
    }
}

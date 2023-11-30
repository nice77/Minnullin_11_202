package client.view.characterSelectScreen;

import client.assets.characters.Characters;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;



public class SpriteAnimation extends Transition {
    private ImageView imageView;
    private int width;
    private int height;
    private int offsetX;
    private int offsetY;
    private int count;

    public SpriteAnimation(
            Duration duration,
            ImageView imageView,
            Characters character
    ) {
        this.imageView = imageView;
        changeProperties(character);
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
        setCycleCount(Animation.INDEFINITE);
    }

    @Override
    protected void interpolate(double frac) {
        int index = Math.min((int) Math.floor(frac * count), count - 1);
        int x = (index % this.count) * this.width + this.offsetX;
        int y = (index / this.count) * this.height + this.offsetY;
        imageView.setViewport(new Rectangle2D(x, y, this.width, this.height));
    }

    public void changeProperties(Characters character) {
        int[] props = character.getAnimationProperties().get("idle");
        this.width = props[0];
        this.height = props[1];
        this.offsetX = props[2];
        this.offsetY = props[3];
        this.count = props[4];

        this.imageView.setImage(new Image(character.getSpritePath()));
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

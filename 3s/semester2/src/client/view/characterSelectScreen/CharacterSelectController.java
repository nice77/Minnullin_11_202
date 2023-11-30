package client.view.characterSelectScreen;

import client.Resources;
import client.assets.characters.Characters;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class CharacterSelectController {
    @FXML private ImageView mainImage;
    @FXML private ImageView characterSprite;
    @FXML private AnchorPane pane;
    private Characters selectedCharacter;

    @FXML
    public void initialize() {
        Image image = new Image(Resources.CHARACTER_SELECT_SCREEN_IMAGE);
        mainImage.setImage(image);
        mainImage.setFitWidth(Resources.WINDOW_WIDTH);
        mainImage.setFitHeight(Resources.WINDOW_HEIGHT);
        characterSprite.setFitWidth(150);
        characterSprite.setFitHeight(200);

        Rectangle rectangle = initRectangle();

        mainImage.setOnMouseClicked(event -> {
            int[] rectStart = getStartCoordinates((int) event.getX(), (int) event.getY());
            if (rectStart != null) {
                SpriteAnimation spriteAnimation = new SpriteAnimation(
                        new Duration(1000),
                        this.characterSprite,
                        this.selectedCharacter
                );
                spriteAnimation.play();
                rectangle.setX(rectStart[0]);
                rectangle.setY(rectStart[1]);
                rectangle.setVisible(true);
            }
        });

        pane.getChildren().add(rectangle);
    }

    private int[] getStartCoordinates(int x, int y) {
        int startX = 70;
        int startY = 110;
        int gap = Resources.CHARACTER_IMAGE_GAP;
        int width = Resources.CHARACTER_IMAGE_WIDTH;
        int height = Resources.CHARACTER_IMAGE_HEIGHT;

        int[] out = new int[2];
        int leftX = (x - startX) / (width + gap);
        int leftY = (y - startY) / (height + gap);
        if (leftX > 4 || leftY > 1 || leftY == 1 && (leftX == 4 || leftX == 0)) {
            return null;
        }

        out[0] = startX + (gap + width) * leftX;
        out[1] = startY + (gap + height) * leftY;
        setPreviewCharacter(leftX, leftY);

        return out;
    }

    private Rectangle initRectangle() {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(Resources.CHARACTER_IMAGE_WIDTH);
        rectangle.setHeight(Resources.CHARACTER_IMAGE_HEIGHT);
        rectangle.setFill(null);
        rectangle.setStroke(Color.GREEN);
        rectangle.setStrokeWidth(Resources.CHARACTER_IMAGE_RECT_STROKE_WIDTH);
        rectangle.setVisible(false);
        return rectangle;
    }

    private void setPreviewCharacter(int x, int y) {
        switch (x + y) {
            case 0:
                this.selectedCharacter = Characters.JOHNNY_CAGE;
                break;
            case 1:
                this.selectedCharacter = Characters.KANO;
                break;
            case 2:
                this.selectedCharacter = Characters.RAIDEN;
                break;
            case 3:
                this.selectedCharacter = (y == 0) ? Characters.SUB_ZERO : Characters.LIU_KANG;
                break;
            case 4:
                this.selectedCharacter = (y == 0) ? Characters.SONYA_BLADE : Characters.SCORPION;
                break;
        }
    }
}

package client.view;

import client.Resources;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StartController {
    @FXML private ImageView mainImage;

    @FXML
    public void initialize() {
        Image image = new Image(Resources.START_SCREEN_IMAGE);
        mainImage.setImage(image);
        mainImage.setFitWidth(Resources.WINDOW_WIDTH);
        mainImage.setFitHeight(Resources.WINDOW_HEIGHT);
    }
}

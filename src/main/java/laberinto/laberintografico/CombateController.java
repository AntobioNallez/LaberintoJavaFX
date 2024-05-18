package laberinto.laberintografico;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;

public class CombateController {

    @FXML
    private ImageView bossImageView;

    @FXML
    private Button attackButton;

    @FXML
    private void handleAttack() {
        System.out.println("¡Ataque realizado!");
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package laberinto.laberintografico;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * FXML Controller class
 *
 * @author Antonio
 */
public class CombateController {

    private boolean ataque = false;

    @FXML
    private Button atack;

    @FXML
    private Button huir;

    @FXML
    private ProgressBar barraJefe;

    @FXML
    private ProgressBar barraXerneas;

    @FXML
    void ataque(ActionEvent event) {
        ataque = !ataque;
        if (ataque) {
            huir.setText("Fuerza Lunar");
            atack.setText("Megacuerno");
            barraJefe.setProgress(barraJefe.getProgress() - 0.1);
            barraXerneas.setProgress(barraXerneas.getProgress() - 0.2);
        } else {
            huir.setText("Huir");
            atack.setText("Atacar");
        }
        if (barraJefe.getProgress() <= 0) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Fin del Juego");
                alert.setHeaderText(null);
                alert.setContentText("GG");
                alert.showAndWait();
                System.exit(0);
            });
        } else if (barraXerneas.getProgress() <= 0) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Fin del Juego");
                alert.setHeaderText(null);
                alert.setContentText("Oh no perdiste el combate");
                alert.showAndWait();
                System.exit(0);
            });
        }
    }
}

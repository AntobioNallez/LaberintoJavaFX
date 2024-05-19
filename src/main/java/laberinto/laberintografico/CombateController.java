/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package laberinto.laberintografico;

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
    void huirPorPatas(ActionEvent event) {
        if (ataque) {
            barraJefe.setProgress(barraJefe.getProgress() - 0.2);
            barraXerneas.setProgress(barraXerneas.getProgress() - 0.1);
            ataque = false;
            huir.setText("Huir");
            atack.setText("Atacar");
        } else {
            App.mostrarMensajeFinal("Huiste del combate. Final 4 Salir por patas");
        }
    }

    @FXML
    void ataque(ActionEvent event) {
        if (!ataque) {
            ataque = true;
            huir.setText("Fuerza Lunar");
            atack.setText("Megacuerno");
            return;
        }
        if (ataque) {
            barraJefe.setProgress(barraJefe.getProgress() - 0.1);
            if (barraJefe.getProgress() <= 0) App.mostrarMensajeFinal("GG");
            barraXerneas.setProgress(barraXerneas.getProgress() - 0.2);
            if (barraXerneas.getProgress() <= 0) App.mostrarMensajeFinal("Oh no perdiste el combate");
            huir.setText("Huir");
            atack.setText("Atacar");
            ataque = false;
        }
    }
}

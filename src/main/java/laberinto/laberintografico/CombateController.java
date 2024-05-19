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

    Ayudante dragonite = new Ayudante(100, 134, 95);
    Ayudante xerneas = new Ayudante(100, 131, 95);
    int poderBase = 80;

    private boolean ataque = false;
    private double critico = 1;
    private double critico2 = 1;

    @FXML
    private Label label;

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
            barraJefe.setProgress(barraJefe.getProgress() - (xerneas.calcularDanio(poderBase) * critico) / 75);
            barraXerneas.setProgress(barraXerneas.getProgress() - (dragonite.calcularDanio(poderBase) * critico2) / 70);
            ataque = false;
            huir.setText("Huir");
            atack.setText("Atacar");
            label.setText("Has utilizado Fuerza Lunar");
        } else {
            App.mostrarMensajeFinal("Huiste del combate. Final 4 Salir por patas");
        }
    }

    @FXML
    void ataque(ActionEvent event) {
        calcularCriticos();
        if (!ataque) {
            ataque = true;
            huir.setText("Fuerza Lunar");
            atack.setText("Megacuerno");
            return;
        }
        if (ataque) {
            label.setText("Has utilizado Megacuerno");
            barraJefe.setProgress(barraJefe.getProgress() - (xerneas.calcularDanio(poderBase) * critico) / 70);
            if (barraJefe.getProgress() <= 0) {
                App.mostrarMensajeFinal("GG");
                return;
            }
            barraXerneas.setProgress(barraXerneas.getProgress() - (dragonite.calcularDanio(poderBase) * critico2) / 72);
            if (barraXerneas.getProgress() <= 0) {
                App.mostrarMensajeFinal("Oh no perdiste el combate");
                return;
            }
            huir.setText("Huir");
            atack.setText("Atacar");
            ataque = false;
        }
    }

    private void calcularCriticos() {
        int prob = (int) (Math.random() * 100);
        int prob2 = (int) (Math.random() * 100);
        switch (prob / 85) {
            case 0 -> critico = 1;
            case 1 -> critico = 1.5;
        }
        switch (prob2 / 85) {
            case 0 -> critico2 = 1;
            case 1 -> critico2 = 1.5;
        }
    }
}

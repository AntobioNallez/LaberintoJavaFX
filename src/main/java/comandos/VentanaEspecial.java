/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comandos;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import laberinto.laberintografico.App;

/**
 *
 * @author Antonio
 */
public class VentanaEspecial extends Stage {

    /**
     * Creacion de la ventana especial con las acciones especiales, no tuve
     * tiempo de implementar más
     *
     * @param animacion
     * @param app
     */
    public VentanaEspecial(SpriteAnimation animacion, App app) {
        setTitle("Acciones Especiales");

        Button btnAccion1 = new Button("Dance");
        Button btnAccion2 = new Button("Random Keyboard");

        btnAccion1.setOnAction((var e) -> {
            actualizarAnimacion(animacion);
            app.setSpecialist();
            close();
        });

        btnAccion2.setOnAction((var e2) -> {
            app.cambiarTeclado();
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Aviso");
                alert.setHeaderText(null);
                alert.setContentText("Cuidado esta mecanica hace que cada vez que pulse una tecla \n esta cambie su valor por otra.");
                alert.showAndWait();
            });
            close();
        });

        VBox root = new VBox(10);
        root.getChildren().addAll(btnAccion1);
        root.getChildren().addAll(btnAccion2);
        Scene specialScene = new Scene(root, 200, 150);

        setScene(specialScene);

    }

    private void actualizarAnimacion(SpriteAnimation animacion) {
        animacion.overrideParameters(448, 23, 0, 237, 47, 56, Duration.seconds(10));
    }
}

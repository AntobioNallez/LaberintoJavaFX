/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comandos;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Antonio
 */
public class VentanaEspecial extends Stage {

    public VentanaEspecial(SpriteAnimation animacion) {
        setTitle("Acciones Especiales");

        Button btnAccion1 = new Button("Dance");

        btnAccion1.setOnAction(e -> {
            actualizarAnimacion(animacion);
            close();
        });

        VBox root = new VBox(10);
        root.getChildren().addAll(btnAccion1);
        Scene specialScene = new Scene(root, 200, 150);

        setScene(specialScene);

    }

    private void actualizarAnimacion(SpriteAnimation animacion) {
        animacion.setWidth(448, 23, 0, 237, 47, 56, Duration.seconds(10));
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package laberinto.laberintografico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Antonio
 */
public class ArcadeController implements Initializable {

    @FXML
    private Button buttonDisfraz;

    @FXML
    private Button buttonObjeto;

    @FXML
    private Button buttonVolver;

    @FXML
    private Label itemPlus;

    @FXML
    private Label disfrazPlus;

    @FXML
    private ImageView imgDisf;

    @FXML
    private ImageView imgObjeto;

    private int monedas;
    private final Image imgX = new Image("/assets/cruz.png"), imgTick = new Image("/assets/tick.png");

    public ArcadeController() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("monedas.txt"));
            String linea = br.readLine();
            if (linea != null) {
                this.monedas = Integer.parseInt(linea);
            } else {
                this.monedas = 50;
            }
        } catch (IOException e) {
            monedas = 50;
        }
    }

    @FXML
    private void volverMapa() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Salir del arcade");
        alert.setHeaderText(null);
        alert.setContentText("Para salir del arcade cierra la ventana dandole click a la X de cerrar ventana");
        alert.showAndWait();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void disfrazGamble(ActionEvent event) {
        if (monedas >= 5) {
            monedas -= 5;
            imgDisf.setVisible(true);
            if (Math.random() < 0.05) {
                imgDisf.setImage(imgTick);
                disfrazPlus.setVisible(true);
            } else {
                imgDisf.setImage(imgX);
                disfrazPlus.setVisible(false);
            }
        }
    }

    @FXML
    private void itemGamble(ActionEvent event) {
        if (monedas >= 5) {
            monedas -= 5;
            imgObjeto.setVisible(true);
            if (Math.random() < 0.05) {
                imgObjeto.setImage(imgTick);
                itemPlus.setVisible(true);
            } else {
                imgObjeto.setImage(imgX);
                itemPlus.setVisible(false);
            }
        }
    }

}

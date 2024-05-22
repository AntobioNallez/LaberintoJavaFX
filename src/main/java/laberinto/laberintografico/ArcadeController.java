/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package laberinto.laberintografico;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;

/**
 * FXML Controller class
 *
 * @author Antonio
 */
public class ArcadeController implements Initializable {

    private int monedas;

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

    private final Image imgX = new Image("/assets/imagenes/cruz.png"), imgTick = new Image("/assets/imagenes/tick.png");

    public ArcadeController() {
        String linea = App.leerFichero("info/monedas.txt");
        if (linea != null) {
            this.monedas = Integer.parseInt(linea);
        } else {
            this.monedas = 50;
        }
    }

    @FXML
    private void volverMapa() {
        App.mostrarMensajeFinal("Para salir del arcade cierra la ventana dandole click a la X de cerrar ventana", false);
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
        if (monedas >= 5 && !disfrazPlus.isVisible()) {
            monedas -= 5;
            App.escribirFichero("info/monedas.txt", String.valueOf(monedas));
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
        if (monedas >= 5 && !itemPlus.isVisible()) {
            monedas -= 5;
            App.escribirFichero("info/monedas.txt", String.valueOf(monedas));
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

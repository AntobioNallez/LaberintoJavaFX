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

/**
 * FXML Controller class
 *
 * @author Antonio
 */
public class ArcadeController implements Initializable {

    private int monedas;

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
            System.out.println("No pude leer el archivo");
            monedas = 50;
        }
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
            if (Math.random() < 0.05) {
                System.out.println("Ganaste");
            } else {
                System.out.println("Oh cielos que horror");
            }
        }
    }
    
    @FXML
    private void itemGamble(ActionEvent event) {
        
    }

}

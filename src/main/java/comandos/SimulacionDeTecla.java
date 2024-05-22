/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comandos;

import javafx.scene.Scene;
import javafx.scene.input.*;

/**
 *
 * @author Antonio
 */
public class SimulacionDeTecla {
    
    public static void simularTecla(String key, long delay, Scene scene) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                KeyEvent keyEvent = new KeyEvent(
                        KeyEvent.KEY_PRESSED, // Tipo de evento
                        key, // Carácter de la tecla
                        key, // Texto de la tecla
                        KeyCode.valueOf(key), // Código de la tecla
                        false, // No se controla el evento
                        false, // No se realiza la tecla de acceso rápido
                        false, // No se repite la tecla
                        false // No se extendió el evento
                );
                scene.getRoot().fireEvent(keyEvent);
            } catch (InterruptedException e) {}
        }).start();
    }
}

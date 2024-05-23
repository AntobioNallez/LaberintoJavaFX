package comandos;

import java.io.*;
import javafx.scene.input.*;

public class ModoAutomatico {

    private static BufferedReader bufferedReader = null;
    
    /**
     * Metodo que convierte la letra recibida a su correspondiente KeyCode
     * 
     * @param action String con la letra a convertir
     * @return KeyCode de la tecla
     */
    private static KeyCode getKeyCode(String action) {
        return switch (action) {
            case "T" -> KeyCode.T;
            case "A" -> KeyCode.A;
            case "W" -> KeyCode.W;
            case "S" -> KeyCode.S;
            case "D" -> KeyCode.D;
            case "B" -> KeyCode.B;
            case "I" -> KeyCode.I;
            case "CONTROL" -> KeyCode.CONTROL;
            default -> null;
        };
    }
    
    /**
     * Metodo que transforma la tecla que aparece en el txt a un KeyEvent de pressed Key
     * 
     * @return KeyEvent, que en este caso es el de presionar una tecla
     */
    public static KeyEvent lectura() {
        KeyEvent keyEvent = null;
        if (new File("automatico2.txt").exists()) {
            try {
                if (bufferedReader == null) {
                    bufferedReader = new BufferedReader(new FileReader("automatico2.txt"));
                }

                String teclaString = bufferedReader.readLine();
                if (teclaString != null) {
                    char inicio = teclaString.charAt(0);
                    teclaString = String.valueOf(inicio);
                    KeyCode keyCode = getKeyCode(teclaString);

                    keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, teclaString, teclaString, keyCode, false, false, false, false);
                }
            } catch (IOException ex) {}
        return keyEvent;
        }
        return null;
    }
}
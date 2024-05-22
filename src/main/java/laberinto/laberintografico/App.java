package laberinto.laberintografico;

import comandos.*;
import java.io.*;
import javafx.animation.*;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @version 0.4
 *
 * @author Antonio
 */
public class App extends Application {

    private static final ImageView imageView = new ImageView("/assets/sprites/spriteAnimacion2.png"), taunt = new ImageView("/assets/sprites/taunts.png"), flash = new ImageView("/assets/sprites/flash.png"), instruccion = new ImageView("/assets/imagenes/iluminar.png");
    //private static final ImageView[] costumes = {new ImageView("")};
    private boolean cinematica = false, specialist = false, accionesReservadas = false, oscurecido = true, keySwap = false;
    private SpriteAnimation animacionCinematica, animacionTaunt;
    private Movimiento movimiento;
    private String tecla, ultimoComando = "";
    private int fondo = 1, num, cuenta = 0;
    private Timeline cinematicaTimeline, tauntTimeline;
    private Rectangle oscuridad;
    private Juego j;
    private StackPane root;
    private Label l = new Label();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setOnCloseRequest((var event) -> {System.exit(0);});
        secondaryStage();
        j = new Juego();
        root = new StackPane();
        Scene scene = new Scene(root, 600, 400);
        movimiento = new Movimiento(imageView);
        Image img = new Image("assets/habitaciones/sala" + fondo + ".png");
        escribirFichero("info/monedas.txt", "0");
        
        instruccion.setVisible(false);
        instruccion.setOpacity(0.7);
        Background bc = new Background(new BackgroundImage(img, BackgroundRepeat.SPACE, BackgroundRepeat.SPACE, BackgroundPosition.CENTER, null));
        root.setBackground(bc);
        crearTimeline(primaryStage);

        oscuridad = new Rectangle(600, 400, Color.BLACK);
        oscuridad.setOpacity(0.9);
        oscuridad.setTranslateZ(1);
        root.getChildren().add(imageView);
        root.getChildren().add(oscuridad);
        oscuridad.setVisible(false);
        root.getChildren().add(instruccion);
        root.getChildren().add(flash);
        root.getChildren().add(taunt);
        flash.setVisible(false);
        taunt.setVisible(false);

        /**
         * Creacion de la animación
         */
        animacionCinematica = new SpriteAnimation(imageView, 10, 2, 2, 3, 38, 32, Duration.seconds(0.8));
        animacionTaunt = new SpriteAnimation(taunt, 1, 1, 0, 0, 40, 40, Duration.seconds(0.5));

        /**
         * Control de teclas presionadas y su uso
         */
        scene.setOnKeyPressed((var event) -> {
            if (!cinematica && oscurecido) {
                tecla = event.getCode().toString();
                if (keySwap) {
                    cambioTecla(tecla);
                }
                if (j.direccionValida(tecla)) {
                    switch (tecla) {
                        case "B" -> {
                            addHistorial(tecla);
                            j.buscarEnHabitacionEspecial();
                            return;
                        }
                        case "CONTROL" -> {
                            addHistorial(tecla);
                            if (!accionesReservadas) {
                                teclaEspecial();
                            } else System.out.println("Ya has activado una acción especial, está limitada a una por partida");
                            return;
                        }
                        case "T" -> {
                            addHistorial(tecla);
                            cinematica = true;
                            Random r = new Random();
                            
                            num = r.nextInt(4);
                            animacionTaunt.setOffSetY(num * 40);
                            num = r.nextInt(5);
                            animacionTaunt.setOffSetX(num * 40);
                            
                            imageView.setVisible(false);
                            taunt.setVisible(true);
                            flash.setVisible(true);
                            animacionTaunt.play();
                            tauntTimeline.play();
                            return;
                        }
                        case "W" -> {
                            movimiento.setDuration(Duration.seconds(3.8));
                            movimiento.moverArriba();
                            if (!specialist) {
                                animacionCinematica.setOffSetX(66);
                            }
                        }
                        case "A" -> {
                            movimiento.setDuration(Duration.seconds(3.8));
                            movimiento.moverIzquierda();
                            if (!specialist) {
                                animacionCinematica.setOffSetX(192);
                            }
                        }
                        case "S" -> {
                            movimiento.setDuration(Duration.seconds(3.8));
                            movimiento.moverAbajo();
                            if (!specialist) {
                                animacionCinematica.setOffSetX(2);
                            }
                        }
                        case "D" -> {
                            movimiento.setDuration(Duration.seconds(3.8));
                            movimiento.moverDerecha();
                            if (!specialist) {
                                animacionCinematica.setOffSetX(130);
                            }
                        }
                    }
                    addHistorial(tecla);
                    animacionCinematica.play();
                    cinematica = true;
                    if (cinematicaTimeline != null) {
                        cinematicaTimeline.stop();
                    }
                    cinematicaTimeline.play();
                }
            } else if (!cinematica && !oscurecido) {
                tecla = event.getCode().toString();
                if (tecla.equals("I")) {
                    addHistorial(tecla);
                    ocultarOscuridad();
                }
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void desactivarEventos(Scene scene) {
        //Lo he buscado en internet
        scene.addEventFilter(KeyEvent.KEY_PRESSED, (var event) -> {
            event.consume();
        });
    }
    
    public void addHistorial(String comando) {
        if (ultimoComando.equals(comando)) cuenta++;
        else cuenta = 1;
        
        ultimoComando = comando;
        
        if (cuenta > 1) l.setText(l.getText() + "(" + cuenta + "x)");
        else l.setText(l.getText() + "\n" + comando);
    }
    
    private void secondaryStage() {
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Historial de comandos");
        StackPane secondaryRoot = new StackPane();
        secondaryRoot.getChildren().add(l);
        Scene secondaryScene = new Scene(secondaryRoot, 300, 200);
        secondaryStage.setScene(secondaryScene);
        secondaryStage.show();
        secondaryStage.setX(100);
        secondaryStage.setY(100);
        Button exportarTxt = new Button();
        exportarTxt.setText("Exportar Historial");
        exportarTxt.setTranslateX(90);
        exportarTxt.setLayoutY(100);
        secondaryRoot.getChildren().add(exportarTxt);
        exportarTxt.setOnAction((var event) -> {
            File f = new File("historial.txt");
            try {
                try (BufferedWriter bwh = new BufferedWriter(new FileWriter(f))) {
                    bwh.write(l.getText());
                    System.out.println("Historial guardado en un txt");
                }
            } catch (IOException ex) {} 
        });
    }

    /**
     * Metodo que al ser llamado crea un mensaje de alerta marcando el fin del
     * juego para posteriormente cerrarlo
     *
     * @param mensaje Mensaje que se va a mostrar cuando salga la alerta
     * @param cerrar Boolean que determina si el juego tiene que cerrarse o no
     */
    public static void mostrarMensajeFinal(String mensaje, boolean cerrar) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText(mensaje);
            if (cerrar) {
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setTitle("Fin del Juego");
                alert.showAndWait();
                System.exit(0);
            } else {
                alert.setTitle("Aviso");
                alert.showAndWait();
            }
        });
    }
    
    /**
     * Metodo estático que lee un archivo y devuelve la linea leida
     * 
     * @param ruta Ruta del archivo
     * @return Linea leida
     */
    public static String leerFichero(String ruta) {
        String devolver = "";
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
                devolver = br.readLine();
            }
        } catch (FileNotFoundException e) {
        } catch (IOException ex) {}
        return devolver;
    }
    
    /**
     * Metodo estatico que escribe en un archivo una string
     * 
     * @param ruta Ruta del archivo
     * @param escribir String a escribir
     */
    public static void escribirFichero(String ruta, String escribir) {
        try {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
                bw.write(escribir);
            }
        } catch (IOException e) {}
    }

    /**
     * Metodo que crea todas las timeline necesarias
     *
     * @param primaryStage
     */
    private void crearTimeline(Stage primaryStage) {
        cinematicaTimeline = new Timeline(new KeyFrame(Duration.seconds(4.5), e -> {
            j.irA(tecla);
            fondo = Integer.parseInt(j.getDescripcion());
            Image newBackgroundImage = new Image("assets/habitaciones/sala" + fondo + ".png");
            Background newBackground = new Background(new BackgroundImage(newBackgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null));
            root.setBackground(newBackground);
            switch (j.getDescripcion()) {
                case "6" -> mostrarMensajeFinal("Final 1: Te quedaste atrapado en la habitación trampa.", true);
                case "8" -> {
                    oscurecido = false;
                    instruccion.setVisible(true);
                    oscuridad.setVisible(true);
                    oscuridad.setOpacity(0.9);
                }
                case "9" -> cargarFXML(primaryStage, "Combate.fxml", "Combate Final", true);
                case "10" -> cargarFXML(primaryStage, "Arcade.fxml", "Arcade", false);
            }

            movimiento.setDuration(Duration.millis(1));
            switch (tecla) {
                case "W" -> movimiento.moverAbajo();
                case "A" -> movimiento.moverDerecha();
                case "D" -> movimiento.moverIzquierda();
                case "S" -> movimiento.moverArriba();
            }
            cinematica = false;
        }));

        tauntTimeline = new Timeline(new KeyFrame(Duration.seconds(0.75), e -> {
            taunt.setVisible(false);
            flash.setVisible(false);
            imageView.setVisible(true);
            animacionTaunt.stop();
            cinematica = false;
        }));
    }

    /**
     * Metodo que cierra la escena para cargar otra
     *
     * @param primaryStage
     */
    private void cargarFXML(Stage primaryStage, String fxml, String titulo, boolean cerrar) {
        Platform.runLater(() -> {
            if (cerrar) {
                primaryStage.close();
            } else {
                primaryStage.hide();
            }
            Stage stage2;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
                AnchorPane rootAnchor = loader.load();
                Scene escena2 = new Scene(rootAnchor, 600, 400);
                stage2 = new Stage();
                stage2.setScene(escena2);
                stage2.setTitle(titulo);
                stage2.show();
            } catch (IOException e) {
                stage2 = new Stage();
                stage2.close();
            }
            stage2.setOnCloseRequest((var event) -> {
                if (!cerrar) {
                    primaryStage.show();
                    j.irA("oeste");
                    fondo = Integer.parseInt(j.getDescripcion());
                    Image newBackgroundImage = new Image("assets/habitaciones/sala" + fondo + ".png");
                    Background newBackground = new Background(new BackgroundImage(newBackgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null));
                    root.setBackground(newBackground);
                }
            });
        });
    }

    /**
     * Metodo utilizado para ocultar la oscuridad de la sala oscura
     */
    private void ocultarOscuridad() {
        oscurecido = true;
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), oscuridad);
        fadeOut.setFromValue(0.9);
        fadeOut.setToValue(0);
        fadeOut.setOnFinished(e -> oscuridad.setVisible(false));
        instruccion.setVisible(false);
        fadeOut.play();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Metodo que crea una nueva ventana la cual permite elegir una acción
     * especial
     */
    private void teclaEspecial() {
        System.out.println("Elige cuál de las mecánicas ocultas quieres activar");
        accionesReservadas = true;
        VentanaEspecial ventana = new VentanaEspecial(animacionCinematica, this);
        ventana.show();
    }

    /**
     * Setter de specialist a true
     */
    public void setSpecialist() {
        specialist = true;
    }

    /**
     * Metodo que cambia la tecla que le pases por otra diferente En este caso
     * usada para alterar los controles del personaje
     *
     * @param teclaPresionada
     */
    private void cambioTecla(String teclaPresionada) {
        Random r = new Random();
        char nuevaTecla = (char) ((teclaPresionada.charAt(0) - 'a' + r.nextInt()) % 26 + 'a');
        tecla = String.valueOf(nuevaTecla).toUpperCase();
    }

    public void cambiarTeclado() {
        keySwap = true;
    }
}

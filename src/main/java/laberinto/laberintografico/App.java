package laberinto.laberintografico;

import comandos.*;
import javafx.animation.*;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @version 0.4
 * 
 * @author Antonio
 */

public class App extends Application {

    private static final ImageView imageView = new ImageView("/assets/spriteAnimacion2.png");
    private boolean cinematica = false, specialist = false, accionesReservadas = false, oscurecido = true;
    private SpriteAnimation animacionCinematica;
    private Movimiento movimiento;
    private String tecla;
    private int fondo = 1;
    private Timeline cinematicaTimeline;
    private Rectangle oscuridad;
    private static final ImageView instruccion = new ImageView("/assets/iluminar.png");

    @Override
    public void start(Stage primaryStage) {
        Juego j = new Juego();
        StackPane root = new StackPane();
        root.getChildren().add(imageView);
        Scene scene = new Scene(root, 600, 400);
        movimiento = new Movimiento(imageView);
        Image img = new Image("assets/sala" + fondo + ".png");
        Background bc = new Background(new BackgroundImage(img, BackgroundRepeat.SPACE, BackgroundRepeat.SPACE, BackgroundPosition.CENTER, null));
        root.setBackground(bc);

        instruccion.setVisible(false);
        instruccion.setOpacity(0.7);

        oscuridad = new Rectangle(600, 400, Color.BLACK);
        oscuridad.setOpacity(0.9);
        oscuridad.setTranslateZ(1);
        root.getChildren().add(oscuridad);
        oscuridad.setVisible(false);
        root.getChildren().add(instruccion);

        /**
         * Creacion de la animación
         */
        animacionCinematica = new SpriteAnimation(imageView, 10, 2, 2, 3, 38, 32, Duration.seconds(0.8));

        /**
         * Control de teclas presionadas y su uso
         */
        scene.setOnKeyPressed((var event) -> {
            tecla = event.getCode().toString();
            if (!cinematica && oscurecido) {
                if (j.direccionValida(tecla)) {
                    switch (tecla) {
                        case "B" -> {
                            j.buscarEnHabitacionEspecial();
                            return;
                        }
                        case "CONTROL" -> {
                            if (!accionesReservadas) {
                                teclaEspecial();
                            } else {
                                System.out.println("Ya has activado una acción especial, está limitada a una por partida");
                            }
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
                    animacionCinematica.play();
                    cinematica = true;
                    if (cinematicaTimeline != null) {
                        cinematicaTimeline.stop();
                    }
                    cinematicaTimeline = new Timeline(new KeyFrame(Duration.seconds(4.5), e -> {
                        j.irA(tecla);
                        fondo = Integer.parseInt(j.getDescripcion());
                        Image newBackgroundImage = new Image("assets/sala" + fondo + ".png");
                        Background newBackground = new Background(new BackgroundImage(newBackgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null));
                        root.setBackground(newBackground);

                        if (j.getDescripcion().equals("6")) {
                            mostrarMensajeFinal("Final 1: Te quedaste atrapado en la habitación trampa.");
                            return;
                        }

                        if (j.getDescripcion().equals("8") && oscurecido) {
                            oscurecido = false;
                            instruccion.setVisible(true);
                            oscuridad.setVisible(true);
                            oscuridad.setOpacity(0.9);
                        }

                        if (j.getDescripcion().equals("9")) {
                            iniciarCombate(primaryStage);
                            return;
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
                    cinematicaTimeline.play();
                }
            } else if (!cinematica && !oscurecido && tecla.equals("I")) ocultarOscuridad();
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Metodo que al ser llamado crea un mensaje de alerta marcando el fin del
     * juego para posteriormente cerrarlo
     *
     * @param mensaje Mensaje que se va a mostrar cuando salga la alerta
     */
    public static void mostrarMensajeFinal(String mensaje) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fin del Juego");
            alert.setHeaderText(null);
            alert.setContentText(mensaje);
            alert.showAndWait();
            System.exit(0);
        });
    }

    /**
     * Metodo que cierra la escena para abrir la de combate y dar inicio a este
     *
     * @param primaryStage
     */
    private void iniciarCombate(Stage primaryStage) {
        Platform.runLater(() -> {
            primaryStage.close();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Combate.fxml"));
                AnchorPane combateRoot = loader.load();
                Scene combateScene = new Scene(combateRoot, 600, 400);
                Stage combateStage = new Stage();
                combateStage.setScene(combateScene);
                combateStage.setTitle("Combate Final");
                combateStage.show();
            } catch (IOException e) {
            }
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
}

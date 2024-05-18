package laberinto.laberintografico;

import comandos.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;

public class App extends Application {

    private static final ImageView imageView = new ImageView("/assets/spriteAnimacion2.png");
    private boolean cinematica = false, specialist = false;
    private boolean accionesReservadas = false;
    private SpriteAnimation animacionCinematica;
    private Movimiento movimiento;
    private String tecla;
    private int fondo = 1;
    private Timeline cinematicaTimeline;
    private static final ImageView bossFinal = new ImageView("assets/260px-Salamence.png");

    @Override
    public void start(Stage primaryStage) {
        Juego j = new Juego();
        StackPane root = new StackPane();
        root.getChildren().add(imageView);
        root.getChildren().add(bossFinal); // Añadimos bossFinal al root
        Scene scene = new Scene(root, 600, 400);
        movimiento = new Movimiento(imageView);
        Image img = new Image("assets/sala" + fondo + ".png");
        Background bc = new Background(new BackgroundImage(img, BackgroundRepeat.SPACE, BackgroundRepeat.SPACE, BackgroundPosition.CENTER, null));
        root.setBackground(bc);
        bossFinal.setVisible(false); // Inicialmente ocultamos bossFinal

        animacionCinematica = new SpriteAnimation(imageView, 10, 2, 2, 3, 38, 32, Duration.seconds(0.8));

        scene.setOnKeyPressed((var event) -> {
            if (!cinematica) {
                tecla = event.getCode().toString();
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

                        if (j.getDescripcion().equals("9")) {
                            iniciarCombate(primaryStage);
                            return;
                        }

                        if (j.getDescripcion().equals("6")) {
                            mostrarMensajeFinal("Final 1: Te quedaste atrapado en la habitación trampa.", primaryStage);
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
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void mostrarMensajeFinal(String mensaje, Stage primaryStage) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fin del Juego");
            alert.setHeaderText(null);
            alert.setContentText(mensaje);
            alert.showAndWait();
            primaryStage.close();
        });
    }

    private void iniciarCombate(Stage primaryStage) {
        Platform.runLater(() -> {
            primaryStage.close();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/laberintografico/Combate.fxml"));
                StackPane combateRoot = loader.load();

                Scene combateScene = new Scene(combateRoot, 800, 600);

                Stage combateStage = new Stage();
                combateStage.setScene(combateScene);
                combateStage.setTitle("Combate Final");
                combateStage.show();
            } catch (IOException e) {}
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void teclaEspecial() {
        System.out.println("Elige cuál de las mecánicas ocultas quieres activar");
        accionesReservadas = true;
        VentanaEspecial ventana = new VentanaEspecial(animacionCinematica, this);
        ventana.show();
    }

    public void setSpecialist() {
        specialist = true;
    }
}

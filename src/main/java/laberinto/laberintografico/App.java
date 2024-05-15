package laberinto.laberintografico;

import comandos.Movimiento;
import comandos.SpriteAnimation;
import comandos.VentanaEspecial;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * JavaFX App
 *
 * @version 0.0.2
 */
public class App extends Application {

    private static final ImageView imageView = new ImageView("/assets/spriteAnimacion2.png");
    private boolean cinematica = false;
    private boolean accionesReservadas = false;
    private SpriteAnimation animacionCinematica;
    private Movimiento movimiento;
    private String tecla;
    private Timeline cinematicaTimeline;

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        root.getChildren().add(imageView);
        Scene scene = new Scene(root, 600, 400);
        movimiento = new Movimiento(imageView);

        animacionCinematica = new SpriteAnimation(imageView, 10, 2, 2, 3, 38, 32, Duration.seconds(0.8));

        scene.setOnKeyPressed((var event) -> {
            if (!cinematica) {
                tecla = event.getCode().toString();
                switch (tecla) {
                    case "CONTROL" -> {
                        if (!accionesReservadas) {
                            teclaEspecial();
                        } else {
                            System.out.println("Ya has activado una acción especial esta limitada a una por partida");
                        }
                        return;
                    }
                    case "W" -> {
                        movimiento.moverArriba();
                        animacionCinematica.setOffSetX(66);
                    }
                    case "A" -> {
                        movimiento.moverIzquierda();
                        animacionCinematica.setOffSetX(192);
                    }
                    case "S" -> {
                        movimiento.moverAbajo();
                        animacionCinematica.setOffSetX(2);
                    }
                    case "D" -> {
                        movimiento.moverDerecha();
                        animacionCinematica.setOffSetX(130);
                    }
                }
                animacionCinematica.play();
                cinematica = true;
                cinematicaTimeline = new Timeline(new KeyFrame(Duration.seconds(5), e -> {
                    cinematica = false;
                }));
                cinematicaTimeline.play();
            }
        }
        );

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void teclaEspecial() {
        System.out.println("Elige cual de las mecanicas ocultas quieres activar");
        accionesReservadas = true;
        VentanaEspecial ventana = new VentanaEspecial(animacionCinematica);
        ventana.show();
    }
}

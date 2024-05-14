package laberinto.laberintografico;

import comandos.SpriteAnimation;
import javafx.animation.TranslateTransition;
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

    private ImageView imageView;
    private boolean cinematica = false;
    private boolean accionesReservadas = false;
    private SpriteAnimation animacionCinematica;
    private boolean specialist = false;
    private String tecla;

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        imageView = new ImageView("/assets/spriteAnimacion2.png");
        root.getChildren().add(imageView);
        Scene scene = new Scene(root, 600, 400);

        animacionCinematica = new SpriteAnimation(imageView, 10, 2, 2, 3, 38, 32, Duration.seconds(0.8));
        animacionCinematica.setOnFinished(event -> cinematica = false);

        scene.setOnKeyPressed((var event) -> {
            tecla = event.getCode().toString();
            if (tecla.equals("CONTROL") && !cinematica) {
                System.out.println("""
                                   Comandos especiales activados. 
                                   Para desactivar esta funcionalidad pulsa Control nuevamente.
                                   Aviso: si se utiliza uno de estos comandos, no se podrá desactivar su función
                                   hasta que se reinicie el juego. 
                                   Pulsar Control una segunda vez solo hace que, por ejemplo, si pulsas la tecla A,
                                   haga el comando de movimiento y no uno especial. Cuando se ejecute un comando
                                   especial se desactivaran los comandos especiales hasta que vuelvas a activarlos
                                   y no se podra desactivar el comando que hayas activado.""");
                accionesReservadas = true;
                return;
            }

            if (accionesReservadas) {
                switch (tecla) {
                    case "A" -> {
                        if (!specialist) {
                            animacionCinematica.setWidth(449, 23, 0, 196, 47, 56, Duration.seconds(2));
                            animacionCinematica.play();
                        } else {
                            System.out.println("Ya esta en funcionamiento");
                        }
                    }
                    default ->
                        System.out.println("Acción reservada no encontrada para esta tecla.");
                }
                accionesReservadas = false;
                return;
            }

            if (!cinematica) {
                cinematica = true;
                switch (tecla) {
                    case "S" -> {
                        moverImageViewGradualmente(0, 180);
                        animacionCinematica.setOffSetX(2);
                    }
                    case "W" -> {
                        moverImageViewGradualmente(0, -180);
                        animacionCinematica.setOffSetX(66);
                    }
                    case "D" -> {
                        moverImageViewGradualmente(280, 0);
                        animacionCinematica.setOffSetX(130);
                    }
                    case "A" -> {
                        moverImageViewGradualmente(-280, 0);
                        animacionCinematica.setOffSetX(194);
                    }
                }
            }
            animacionCinematica.play();
        }
        );

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void moverImageViewGradualmente(double moveX, double moveY) { //Se rompe si hago specialist dance aún tengo que averiguar porque
        double posXInicial = imageView.getTranslateX();
        double posYInicial = imageView.getTranslateY();

        double posXFinal = posXInicial + moveX;
        double posYFinal = posYInicial + moveY;

        TranslateTransition transition = new TranslateTransition(Duration.seconds(4), imageView);
        
        transition.setToX(posXFinal);
        transition.setToY(posYFinal);

        transition.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

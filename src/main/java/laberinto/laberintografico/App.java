package laberinto.laberintografico;

import comandos.*;
import javafx.animation.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * JavaFX App
 *
 * @version 0.0.2
 */
public class App extends Application {

    private static final ImageView imageView = new ImageView("/assets/spriteAnimacion2.png");
    private boolean cinematica, specialist = false;
    private boolean accionesReservadas = false;
    private SpriteAnimation animacionCinematica;
    private Movimiento movimiento;
    private String tecla;
    private Timeline cinematicaTimeline;
    private StackPane root;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) {
        setup();
        
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
                        if (!specialist) {
                            animacionCinematica.setOffSetX(66);
                        }
                    }
                    case "A" -> {
                        movimiento.moverIzquierda();
                        if (!specialist) {
                            animacionCinematica.setOffSetX(192);
                        }
                    }
                    case "S" -> {
                        movimiento.moverAbajo();
                        if (!specialist) {
                            animacionCinematica.setOffSetX(2);
                        }
                    }
                    case "D" -> {
                        movimiento.moverDerecha();
                        if (!specialist) {
                            animacionCinematica.setOffSetX(130);
                        }
                    }
                }
                animacionCinematica.play();
                cinematica = true;
                cinematicaTimeline = new Timeline(new KeyFrame(Duration.seconds(4.5), e -> {
                    cinematica = false;
                }));
                cinematicaTimeline.play();
            }
        }
        );

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void teclaEspecial() {
        System.out.println("Elige cual de las mecanicas ocultas quieres activar");
        accionesReservadas = true;
        VentanaEspecial ventana = new VentanaEspecial(animacionCinematica, this);
        ventana.show();
    }
    
    public void setSpecialist() {
        specialist = true;
    }
    
    private void setup() {
        root = new StackPane();
        scene = new Scene(root, 600, 400);
        movimiento = new Movimiento(imageView);
        Image imagenDeFondo = new Image("assets/0fx_hallway.png");
        ImageView imagenFondo = new ImageView("assets/0fx_hallway.png");
        imagenFondo.setFitWidth(600);
        imagenFondo.setFitHeight(400);
        BackgroundImage fondo = new BackgroundImage(imagenDeFondo, BackgroundRepeat.SPACE, BackgroundRepeat.SPACE, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background background = new Background(fondo);
        root.setBackground(background);
        root.getChildren().addAll(imagenFondo, imageView);
        animacionCinematica = new SpriteAnimation(imageView, 10, 2, 2, 3, 38, 32, Duration.seconds(0.8));
    }

    public static void main(String[] args) {
        launch(args);
    }

}

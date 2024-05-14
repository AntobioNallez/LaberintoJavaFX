package laberinto.laberintografico;

import comandos.SpriteAnimation;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * JavaFX App
 */
public class App extends Application {

    private ImageView imageView;
    private int currentImageIndex = 0;
    private Image[] images = new Image[4];
    private Timeline timeline;
    private boolean keyEnabled = true;

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        imageView = new ImageView("/assets/spriteAnimacion.png");
        root.getChildren().add(imageView);
        /**
        for (int i = 0; i < 4; i++) {
            images[i] = new Image(getClass().getResourceAsStream("/assets/oeste/andaroeste_" + i + ".png"));
        }
        imageView = new ImageView(images[currentImageIndex]);
        root.getChildren().add(imageView);
        */
        Scene scene = new Scene(root, 600, 400);
        /**
        timeline = new Timeline(new KeyFrame(Duration.millis(25), event -> {
            currentImageIndex = (currentImageIndex + 1) % 4;
            imageView.setImage(images[currentImageIndex]);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.A && keyEnabled) {
                if (timeline.getStatus() == Animation.Status.RUNNING) {
                    timeline.pause();
                } else {
                    timeline.play();
                }
                keyEnabled = false;
                resetKeyEnabledAfterDelay();
            } else if (event.getCode() == KeyCode.S) {
                if (primaryStage.getScene().equals(scene)) {
                    primaryStage.setScene(createScene2());
                } else {
                    primaryStage.setScene(scene);
                }
            }
        });
        */
        
        Duration duration = Duration.seconds(2);
        int count = 20; //Frames totales
        int columna = 4; //Columnas, va de izquierda a derecha y de arriba a abajo
        int offSetX = 2; //Distancia desde el inicio de la imagen hasta el primer pixel en el eje X
        int offSetY = 3; //Distancia desde el inicio de la imagen hasta el primer pixel en el eje Y
        int height = 38; //Altura de los frames
        int width = 32; //Anchura de los frames

        SpriteAnimation animation = new SpriteAnimation(imageView, count, columna, offSetX, offSetY, height, width, duration);
        
        scene.setOnKeyPressed(event -> {
            animation.play();
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * 
     */
     
     private void resetKeyEnabledAfterDelay() {
         Timeline resetTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
             keyEnabled = true;
            }));
            resetTimeline.play();
        }
        
        private Scene createScene2() {
        StackPane root = new StackPane();
        root.getChildren().add(imageView);
        Scene scene = new Scene(root, 400, 400);
        
        return scene;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
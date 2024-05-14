package laberinto.laberintografico;

import comandos.SpriteAnimation;
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
        Scene scene = new Scene(root, 600, 400);
        
        Duration duration = Duration.seconds(0.5);
        
        int offSetX = 2;

        SpriteAnimation animation = new SpriteAnimation(imageView, offSetX, duration);
        
        scene.setOnKeyPressed((var event) -> { //Lo de var me lo recomendo netbeans ni idea de que es pero funciona asi que
            if (null != event.getCode()) switch (event.getCode()) {
                case S -> animation.setOffSetX(2);
                case W -> animation.setOffSetX(66);
                case D -> animation.setOffSetX(130);
                case A -> animation.setOffSetX(194);
            }
            animation.play();
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
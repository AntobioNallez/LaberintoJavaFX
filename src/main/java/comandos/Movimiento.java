/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comandos;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author Antonio
 */
public class Movimiento {

    private final ImageView imageView;

    public Movimiento(ImageView imageView) {
        this.imageView = imageView;
    }
    
    /**
     * Metodo que hacer que el personaje se mueva hacia arriba
     */
    public void moverArriba() {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(3.8), imageView);
        transition.setByY(-150);
        transition.play();
    }
    
    /**
     * Metodo que hacer que el personaje se mueva hacia abajo
     */
    public void moverAbajo() {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(3.8), imageView);
        transition.setByY(150);
        transition.play();
    }
    
    /**
     * Metodo que hacer que el personaje se mueva hacia la izquierda
     */
    public void moverIzquierda() {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(3.8), imageView);
        transition.setByX(-245); // Mover hacia la izquierda
        transition.play();
    }
    
    /**
     * Metodo que hacer que el personaje se mueva hacia la derecha
     */
    public void moverDerecha() {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(3.8), imageView);
        transition.setByX(245);
        transition.play();
    }
    
}

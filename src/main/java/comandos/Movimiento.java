/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comandos;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Clase que controla el movimiento de un ImageView para moverlo en las cuatro direcciones cardinales
 *
 * @author Antonio
 */
public class Movimiento {

    private final ImageView imageView;
    private Duration duration = Duration.seconds(3.8);
    
    /**
     * Constructor de la clase movimiento
     * 
     * @param imageView Imagen a mover
     */
    public Movimiento(ImageView imageView) {
        this.imageView = imageView;
    }
    
    /**
     * Metodo para sobreescribir la duración del movimiento
     * 
     * @param duration2 Nueva duración del movimiento
     */
    public void setDuration(Duration duration2){
        this.duration = duration2;
    }
    
    /**
     * Metodo que mueve el personaje hacia arriba
     */
    public void moverArriba() {
        TranslateTransition transition = new TranslateTransition(duration, imageView);
        transition.setByY(-150);
        transition.play();
    }
    
    /**
     * Metodo que mueve el personaje hacia abajo
     */
    public void moverAbajo() {
        TranslateTransition transition = new TranslateTransition(duration, imageView);
        transition.setByY(150);
        transition.play();
    }
    
    /**
     * Metodo que mueve el personaje a la izquierda
     */
    public void moverIzquierda() {
        TranslateTransition transition = new TranslateTransition(duration, imageView);
        transition.setByX(-245);
        transition.play();
    }
    
    /**
     * Metodo que mueve el personaje a la derecha
     */
    public void moverDerecha() {
        TranslateTransition transition = new TranslateTransition(duration, imageView);
        transition.setByX(245);
        transition.play();
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comandos;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author Antonio
 */
public class SpriteAnimation extends Transition {

   private final ImageView imageView;
   private int count; //Frames totales
   private int columna; //Número de columnas a revisar
   private int offSetX; //Distancia hasta el primer pixel en el eje X
   private int offSetY; //Distancia hasta el primer pixel en el eje Y
   private int height; //Altura del frame
   private int width; //Anchura del frame
   private Duration duration; //Duración de la animación
   
   /**
    * Constructor de la clase SpriteAnimation
    * 
    * @param imageView Foto que contiene los frames de la animación
    * @param offSetX Distancia desde el inicio de la imagen hasta el primer pixel en el eje X
    * @param duration Duración de la animación
    */
    public SpriteAnimation(ImageView imageView, int count, int columna, int offSetX, int offSetY, int height, int width, Duration duration) {
        this.imageView = imageView;
        this.count = count;
        this.columna = columna;
        this.offSetX = offSetX;
        this.offSetY = offSetY;
        this.height = height;
        this.width = width;
        this.duration = duration;
        
        setCycleDuration(this.duration);
        setCycleCount(5);
        setInterpolator(Interpolator.LINEAR);
        this.imageView.setViewport(new Rectangle2D(offSetX, offSetY, this.width, this.height));
    }
    
    /**
     * Metodo que sirve para alterar todos los parametros en una animación en caso que los frames de la animación esten en la misma imagen
     * 
     * @param count Número total de frames que tiene la animación
     * @param columna Columnas que revisar de la imagen
     * @param offSetX Distancia hasta el primer pixel desde el eje X
     * @param offSetY Distancia hasta el primer pixel desde el eje Y
     * @param height Altura del frame
     * @param width Anchura del frame
     * @param duration Duración de toda la animación
     */
    public void overrideParameters(int count, int columna, int offSetX, int offSetY, int height, int width, Duration duration) {
        this.count = count;
        this.columna = columna;
        this.offSetX = offSetX;
        this.offSetY = offSetY;
        this.height = height;
        this.width = width;
        this.duration = duration;
        
        setCycleDuration(this.duration);
        setCycleCount(INDEFINITE);
        setInterpolator(Interpolator.LINEAR);
        this.imageView.setViewport(new Rectangle2D(offSetX, offSetY, this.width, this.height));
    }
    
    /**
     * Setter del parametro offSetX
     * 
     * @param offSetX 
     */
    public void setOffSetX(int offSetX) {
        this.offSetX = offSetX;
    }
    
    /**
     * Setter del parametro offSetX
     * 
     * @param offSetY 
     */
    public void setOffSetY(int offSetY) {
        this.offSetY = offSetY;
    }
    
    /**
     * Metodo que utiliza un interpolador lineal para la transición entre los frames de la animación.
     * El método interpolate() se sobrescribe para actualizar la vista del ImageView en cada iteración de la animación.
     * 
     * @param frac 
     */
   @Override
    protected void interpolate(double frac) {
        final int index = Math.min((int) Math.floor(count * frac), count - 1);
        final int x = (index % columna) * width + offSetX;
        final int y = (index / columna) * height + offSetY;
        imageView.setViewport(new Rectangle2D(x, y, width, height));
    }
    
}

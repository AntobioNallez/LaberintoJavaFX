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
    
   //Valores para ir hacia abajo (10,2,2,3,38,32), hacia arriba(10,2,66,3,38,32), hacia la derecha(10,2,130,3,38,32),hacia la izquierda(10,2,192,3,38,32);
   //Estos valores son funcionales para la foto que yo he puesto otra foto habria que ajustarlos o cambiar la foto;
   private final ImageView imageView;
   private int count; //Frames totales
   private int columna; //Columnas, va de izquierda a derecha y de arriba a abajo
   private int offSetX; 
   private int offSetY; //Distancia desde el inicio de la imagen hasta el primer pixel en el eje Y
   private int height; //Altura de los frames
   private int width; //Anchura de los frames
   private Duration duration;
   
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
     * @param count
     * @param columna
     * @param offSetX
     * @param offSetY
     * @param height
     * @param width
     * @param duration 
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

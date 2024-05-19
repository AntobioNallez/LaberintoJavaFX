/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comandos;

import javafx.animation.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author Antonio
 */
public class SpriteAnimation extends Transition {

   private final ImageView imageView;
   private int count;
   private int columna;
   private int offSetX;
   private int offSetY;
   private int altura;
   private int anchura;
   private Duration duration;
   
   /**
    * Constructor de la clase SpriteAnimation
    * 
    * @param imageView Foto que contiene los frames de la animación
    * @param count Frames totales de la animacion
    * @param columna Numero de columnas a revisar
    * @param offSetX Distancia desde el inicio de la imagen hasta el primer pixel en el eje X
    * @param offSetY Distancia desde el inicio de la imagen hasta el primer pixel en el eje Y
    * @param altura Altura del frame
    * @param anchura Anchura del frame
    * @param duration Duración total de la animación
    */
    public SpriteAnimation(ImageView imageView, int count, int columna, int offSetX, int offSetY, int altura, int anchura, Duration duration) {
        this.imageView = imageView;
        this.count = count;
        this.columna = columna;
        this.offSetX = offSetX;
        this.offSetY = offSetY;
        this.altura = altura;
        this.anchura = anchura;
        this.duration = duration;
        
        setCycleDuration(this.duration);
        setCycleCount(5);
        setInterpolator(Interpolator.LINEAR);
        this.imageView.setViewport(new Rectangle2D(offSetX, offSetY, this.anchura, this.altura));
    }
    
    /**
     * Metodo que sirve para alterar todos los parametros en una animación en caso que los frames de la animación esten en la misma imagen
     * 
     * @param count Número total de frames que tiene la animación
     * @param columna Columnas que revisar de la imagen
     * @param offSetX Distancia hasta el primer pixel desde el eje X
     * @param offSetY Distancia hasta el primer pixel desde el eje Y
     * @param altura Altura del frame
     * @param anchura Anchura del frame
     * @param duration Duración de toda la animación
     */
    public void overrideParameters(int count, int columna, int offSetX, int offSetY, int altura, int anchura, Duration duration) {
        this.count = count;
        this.columna = columna;
        this.offSetX = offSetX;
        this.offSetY = offSetY;
        this.altura = altura;
        this.anchura = anchura;
        this.duration = duration;
        
        setCycleDuration(this.duration);
        setCycleCount(INDEFINITE);
        setInterpolator(Interpolator.LINEAR);
        this.imageView.setViewport(new Rectangle2D(offSetX, offSetY, this.anchura, this.altura));
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
        final int x = (index % columna) * anchura + offSetX;
        final int y = (index / columna) * altura + offSetY;
        imageView.setViewport(new Rectangle2D(x, y, anchura, altura));
    }
    
}

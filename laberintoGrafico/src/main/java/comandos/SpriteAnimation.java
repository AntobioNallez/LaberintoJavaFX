/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comandos;

import javafx.animation.Animation;
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
   private final int count;
   private final int columna;
   private int offSetX;
   private int offSetY;
   private final int height;
   private final int width;

    public SpriteAnimation(ImageView imageView, int count, int columna, int offSetX, int offSetY, int height, int width, Duration duration) {
        this.imageView = imageView;
        this.count = count;
        this.columna = columna;
        this.offSetX = offSetX;
        this.offSetY = offSetY;
        this.height = height;
        this.width = width;
        
        setCycleDuration(duration);
        setCycleCount(Animation.INDEFINITE);
        setInterpolator(Interpolator.LINEAR);
        this.imageView.setViewport(new Rectangle2D(offSetX, offSetY, width, height));
    }

    public void setOffSetX(int offSetX) {
        this.offSetX = offSetX;
    }

    public void setOffSetY(int offSetY) {
        this.offSetY = offSetY;
    }
    
   @Override
    protected void interpolate(double frac) {
        final int index = Math.min((int) Math.floor(count * frac), count - 1);
        final int x = (index % columna) * width + offSetX;
        final int y = (index / columna) * height + offSetY;
        imageView.setViewport(new Rectangle2D(x, y, width, height));
    }
    
}

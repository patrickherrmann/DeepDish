package windowed;

import deepdish.GameObject;
import deepdish.Vector;
import deepdish.Viewport;
import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * @author Patrick Herrmann
 */
public class Circle extends GameObject {
    
    private Point2D.Double position;
    private Vector velocity;
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
        position = new Point2D.Double(0, 0);
        velocity = new Vector(0, 0);
        
    }

    @Override
    public void paint(Viewport v) {
        Point pixel = v.getPixel(position);
        v.getGraphics().setColor(Color.RED);
        int rounded = (int) radius;
        v.getGraphics().fillOval(pixel.x - rounded, pixel.y + rounded, rounded * 2, rounded * 2);
    }

    @Override
    public void update() {
        position = velocity.addTo(position);
        Random gen = new Random();
        velocity = velocity.plus(new Vector(gen.nextDouble()  - 0.5, gen.nextDouble() - 0.5).scale(0.25));
    }
    
}

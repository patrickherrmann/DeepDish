/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package deepdish;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;

/**
 *
 * @author patrick
 */
public abstract class MovableObject extends GameObject {

    // Location animation
    protected Point2D.Double location;
    protected Point2D.Double targetLocation;
    protected double speed = 1.0;
    protected boolean moving = false;
    // Dimensions animation
    protected Point2D.Double dimensions;
    protected Point2D.Double targetDimensions;
    protected double dimensionRate = 1.0;
    protected boolean scaling = false;
    // Rotation animation
    protected double rotation;
    protected double targetRotation;
    protected double rotationRate = 1.0;
    protected boolean rotating = false;

    protected MovableObject(double x, double y, double width, double height) {
        this.location = new Point2D.Double(x, y);
        this.targetLocation = new Point2D.Double(x, y);
        this.dimensions = new Point2D.Double(width, height);
        this.targetDimensions = new Point2D.Double(width, height);
        this.rotation = 0;
        this.targetRotation = 0;
    }

    @Override
    public void paint(Viewport viewport) {

        Graphics2D g = viewport.getGraphics();
        Point pixel = viewport.getPixel(location);
        Point pixelDimensions = new Point((int) dimensions.x, (int) dimensions.y);

        g.translate(pixel.x, pixel.y);
        g.rotate(-rotation);
        g.translate(-pixelDimensions.x / 2, -pixelDimensions.y / 2);

        paintObject(g, pixelDimensions);

        g.translate(pixelDimensions.x / 2, pixelDimensions.y / 2);
        g.rotate(rotation);
        g.translate(-pixel.x, -pixel.y);
    }

    public abstract void paintObject(Graphics g, Point pixelDimensions);

    @Override
    public void update() {

        updateLocation();
        updateDimensions();
        updateRotation();
    }

    protected void updateLocation() {

        Vector diff = new Vector(location, targetLocation);

        if (diff.getMagnitude() < speed) {
            location.setLocation(targetLocation);
            moving = false;
        } else {
            location.setLocation(diff.toUnitVector().scale(speed).addTo(location));
            moving = true;
        }
    }

    protected void updateDimensions() {

        Vector diff = new Vector(dimensions, targetDimensions);

        if (diff.getMagnitude() < dimensionRate) {
            dimensions.setLocation(targetDimensions);
            scaling = false;
        } else {
            dimensions.setLocation(diff.toUnitVector().scale(dimensionRate).addTo(dimensions));
            scaling = true;
        }
    }

    protected void updateRotation() {

        double diff = targetRotation - rotation;

        if (diff < rotationRate) {
            rotation = targetRotation;
            rotating = false;
        } else if (diff > 0) {
            rotation += rotationRate;
            rotating = true;
        } else {
            rotation -= rotationRate;
            rotating = true;
        }
    }

    public void setLocation(double x, double y) {
        location.setLocation(x, y);
        targetLocation.setLocation(x, y);
    }

    public void animateLocation(double x, double y) {
        targetLocation.setLocation(x, y);
    }

    public void setRotation(double angle) {
        rotation = angle;
        targetRotation = angle;
    }

    public void animateRotation(double angle) {
        targetRotation = angle;
    }

    public void setDimensions(double width, double height) {
        dimensions.setLocation(width, height);
        targetDimensions.setLocation(width, height);
    }

    public void animateDimensions(double width, double height) {
        targetDimensions.setLocation(width, height);
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setDimensionsRate(double dimensionRate) {
        this.dimensionRate = dimensionRate;
    }

    public void setRotationRate(double rotationRate) {
        this.rotationRate = rotationRate;
    }
}

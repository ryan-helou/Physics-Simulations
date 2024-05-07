package edu.vanier.template;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author salki
 */


import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.geometry.Point2D;

/**
 * Represents a particle object.
 * 
 * @author salki
 */
public class Mover { 
    private Point2D location;
    private Point2D velocity;
    private Point2D acceleration;
    private double topspeed;
    private double mass = 1;
    private double forceMagnitude = 0.2; // Adjust this factor to control the force magnitude
    private double size;
    private double scaleFactor = 1;
    private Circle circle;
    private List<Point2D> trail;
    private int trailLength = 30;
    private boolean trailStatus = true;
    
    public Mover(double x, double y, double size) {
        location = new Point2D(x, y);
        velocity = new Point2D(0, 0); //made it start in the center
        topspeed = 5;   //Might implement later if I have time
        this.mass = mass;
        this.size = size;
        circle = new Circle(location.getX(), location.getY(), size);
        circle.setFill(Color.rgb(127, 127, 127));
        circle.setStroke(Color.WHITE);
        circle.setStrokeWidth(2);
        circle.setRadius(size);
        trail = new ArrayList<>();
}
    
    /**
     * Updates mover location based on mouse position
     * @param mouse 
     */
    public void update(Point2D mouse) {
        acceleration = mouse.subtract(location);
        acceleration = acceleration.normalize().multiply(0.2);

        double force = forceMagnitude/10; //*100
        double accelerationMagnitude = (force / mass); 
        /*
        double accelerationFactor = 1; // Adjust this factor to increase acceleration
        acceleration = acceleration.multiply(accelerationFactor);
        */
        
        velocity = velocity.add(acceleration.multiply(accelerationMagnitude));
        double speed = velocity.magnitude();
        if (speed > topspeed) {
            velocity = velocity.normalize().multiply(topspeed);
        }
        
        /*
        // Increase velocity directly
        double velocityFactor = 1; // Adjust this factor to change velocity increase
        velocity = velocity.multiply(velocityFactor);
    
        double speed = velocity.magnitude();
        if (speed > topspeed) { //top speed limits the speed (import from my old code)
            velocity = velocity.normalize().multiply(topspeed);
        }
*/
        location = location.add(velocity);
        
        trail.add(new Point2D(location.getX(), location.getY()));
        // removes oldest point
        if (trail.size() > trailLength) {
            trail.remove(0);
        }
        
        circle.setCenterX(location.getX());
        circle.setCenterY(location.getY());
    }

    public double getSize() {
        return size;
    }

    public double getForceMagnitude() {
        return forceMagnitude;
    }

    public void setForceMagnitude(double forceMagnitude) {
        this.forceMagnitude = forceMagnitude;
    }

    
    //To fix: make it so that the smaller trails align with the main particles
    
    
    /*
    Displays the trail and the main particle
    */
    public void display(GraphicsContext gc) {
        
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);
        gc.setFill(Color.rgb(127, 127, 127));
        gc.fillOval(location.getX() - 24, location.getY() - 24, 48*scaleFactor, 48*scaleFactor);
        gc.strokeOval(location.getX() - 24, location.getY() - 24, 48*scaleFactor, 48*scaleFactor);
        
        for (int i = 0; i < trail.size(); i++) {
            Point2D point = trail.get(i);
            double opacity = 0.1 + (0.9 * ((double)i / trail.size()));  
        if(trailStatus == true){
            opacity = 0.1 + (0.9 * ((double)i / trail.size()));  
        } else{
            opacity = 0;
        }
        //double opacity = 0;
        gc.setFill(Color.rgb(127, 127, 127, opacity)); //opacity
        double trailCircleSize = 16 * scaleFactor; //trail size
        gc.fillOval(point.getX() - (trailCircleSize / 2), point.getY() - (trailCircleSize / 2), (trailCircleSize*(scaleFactor)), (trailCircleSize*(scaleFactor)));
    }
    }

    public Shape getShape() {
        return circle;
    }
    
    public void numberOfParticles(){ 
    }
    
    public void changeMass(){    
    }
    
    public void changeAcceleration(){ 
    }
    
    public void changePlanet(){  
    }
    
    public void triggerTrail(){
    }

    public void setTrailStatus(boolean trailStatus) {
        this.trailStatus = trailStatus;
    }

    public double getMass() {
        return mass;
    }
    
    public void setMass(double mass) {
        this.mass = mass;
    }
    
    public void setSize(double newSize) {
        size = newSize;
        circle.setRadius(size);
    }

    public void setScaleFactor(double scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    public Point2D getLocation() {
        return location;
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }

    public Point2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    public Point2D getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Point2D acceleration) {
        this.acceleration = acceleration;
    }

    public double getTopspeed() {
        return topspeed;
    }

    public void setTopspeed(double topspeed) {
        this.topspeed = topspeed;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public List<Point2D> getTrail() {
        return trail;
    }

    public void setTrail(List<Point2D> trail) {
        this.trail = trail;
    }

    public int getTrailLength() {
        return trailLength;
    }

    public void setTrailLength(int trailLength) {
        this.trailLength = trailLength;
    }
    
    
    
}
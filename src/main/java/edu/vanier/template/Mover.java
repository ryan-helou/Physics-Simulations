package edu.vanier.template;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author salki
 */


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.geometry.Point2D;

public class Mover { 
    private Point2D location;
    private Point2D velocity;
    private Point2D acceleration;
    private double topspeed;
    private Circle circle;

    public Mover(double x, double y) {
        location = new Point2D(x, y);
        velocity = new Point2D(0, 0); //made it start in the center
        topspeed = 5;
        circle = new Circle(location.getX(), location.getY(), 24);
        circle.setFill(Color.rgb(127, 127, 127));
        circle.setStroke(Color.WHITE);
        circle.setStrokeWidth(2);
    }

    public void update(Point2D mouse) {
        acceleration = mouse.subtract(location);
        acceleration = acceleration.normalize().multiply(0.4);
        
        velocity = velocity.add(acceleration);
        double speed = velocity.magnitude();
        if (speed > topspeed) { //top speed limits the speed (import from my old code)
            velocity = velocity.normalize().multiply(topspeed);
        }
        location = location.add(velocity);
        circle.setCenterX(location.getX());
        circle.setCenterY(location.getY());
    }

    public void display(GraphicsContext gc) {
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);
        gc.setFill(Color.rgb(127, 127, 127));
        gc.fillOval(location.getX() - 24, location.getY() - 24, 48, 48);
        gc.strokeOval(location.getX() - 24, location.getY() - 24, 48, 48);
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
    
}
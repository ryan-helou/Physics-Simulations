/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.template.pendulum;

/**
 *
 * @author salki
 */
public class Vector2D {
    private final double x;
    private final double y;

    /**
     * 
     * @param x
     * @param y 
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
   public Vector2D copy() {
    return new Vector2D(this.x, this.y);
    }

    public Vector2D add(Vector2D other) {
        return new Vector2D(x + other.x, y + other.y);
    }

    public Vector2D subtract(Vector2D other) {
        return new Vector2D(x - other.x, y - other.y);
    }
    
    /**
     * 
     * @param other
     * @return 
     */
    public double distance(Vector2D other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    /**
     * 
     * @param x1
     * @param y1
     * @param r1
     * @param x2
     * @param y2
     * @param r2
     * @return 
     */
    public boolean checkCollision(double x1, double y1, double r1, double x2, double y2, double r2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance <= r1 + r2;
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    
    
}

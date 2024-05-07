/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.template.pendulum;

/**
 * Class representing a vector on a 2-dimensional
 * plane. Used for reference in the pendulum class
 * and Newton's cradle to help assist with dragging
 * movement.
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
    
    /**
     * 
     * @return 
     */
    public Vector2D copy() {
    return new Vector2D(this.x, this.y);
    }

    /**
     * 
     * @param other
     * @return 
     */
    public Vector2D add(Vector2D other) {
        return new Vector2D(x + other.x, y + other.y);
    }

    /**
     * 
     * @param other
     * @return 
     */
    public Vector2D subtract(Vector2D other) {
        return new Vector2D(x - other.x, y - other.y);
    }
    
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    
    
}

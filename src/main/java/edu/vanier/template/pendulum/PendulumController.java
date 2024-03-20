/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.template.pendulum;

/**
 *
 * @author ryanhelou
 */
public class PendulumController {
    private double length1;
    private double length2;
    private double mass1;
    private double mass2;
    private double angle1;
    private double angle2;
    private double velocity1;
    private double velocity2;

    public void draw(){
        
    }
    
    public double getLength1() {
        return length1;
    }

    public void setLength1(double length1) {
        this.length1 = length1;
    }

    public double getLength2() {
        return length2;
    }

    public void setLength2(double length2) {
        this.length2 = length2;
    }

    public double getMass1() {
        return mass1;
    }

    public void setMass1(double mass1) {
        this.mass1 = mass1;
    }

    public double getMass2() {
        return mass2;
    }

    public void setMass2(double mass2) {
        this.mass2 = mass2;
    }

    public double getAngle1() {
        return angle1;
    }

    public void setAngle1(double angle1) {
        this.angle1 = angle1;
    }

    public double getAngle2() {
        return angle2;
    }

    public void setAngle2(double angle2) {
        this.angle2 = angle2;
    }

    public double getVelocity1() {
        return velocity1;
    }

    public void setVelocity1(double velocity1) {
        this.velocity1 = velocity1;
    }

    public double getVelocity2() {
        return velocity2;
    }

    public void setVelocity2(double velocity2) {
        this.velocity2 = velocity2;
    }
    
    
    public void startAnimation() {

    }

    public void stopAnimation() {
      
    }

    public double calculateVerticalComponent() {
        return 0.0;

    }
    
    public double calculateHorizontalComponent() {
        return 0.0;
    }
    
    
    public PendulumController(){
        
    }

}

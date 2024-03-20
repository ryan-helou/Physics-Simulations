package edu.vanier.template.controllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author anishmehra
 */

public class CradleFormulas {
    public Double time ;
    public Double length;
    public Double gravity;
    public Double initialSpeed;
    public Double finalSpeed;
    public Double initialHeight;
    public Double finalHeight;
    public void time(){
        time = (2*Math.PI)*Math.sqrt(length/gravity);
    }
    public void speed(){
        initialSpeed = finalSpeed;
    }
    public void height(){
        initialHeight = finalHeight;
    }
}

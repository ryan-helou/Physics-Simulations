/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.template.pendulum;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 *
 * @author ryanhelou
 */
public class DoublePendulumController implements Initializable{
    
    private double length1;
    private double length2;
    private double mass1;
    private double mass2;
    private double angle1;
    private double angle2;
    private double velocity1;
    private double velocity2;
    private  final double G = 9.81;
    private double theta1; // Initial angle of the first pendulum
    private double theta2; // Initial angle of the second pendulum
    
    @FXML
    private Slider sliderAngle1;
    
    @FXML
    private Slider sliderAngle2;
    
    @FXML
    private Slider sliderLength1;
    
    @FXML
    private Slider sliderLength2;
    
    @FXML
    private Slider sliderMass1;
    
    @FXML
    private Slider sliderMass2;
    
    @FXML
    private Text textAngle1;
    
    @FXML
    private Text textAngle2;
    
    @FXML
    private Text textLength1;
    
    @FXML
    private Text textLength2;
    
    @FXML
    private Text textMass1;
    
    @FXML
    private Text textMass2;
    
    @FXML 
    private Pane mainPane;

    public void draw() {

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

    public DoublePendulumController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
 

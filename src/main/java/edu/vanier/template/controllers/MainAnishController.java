/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.vanier.template.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author 2165566
 */
public class MainAnishController implements Initializable {

    @FXML
    private ImageView spacebackground;
    @FXML
    private ImageView doublependulum;
    @FXML
    private ImageView newtonscradle;
    @FXML
    private ImageView particleattraction;
    @FXML
    private ImageView settings;

    private double SCALING_FACTOR = 1.09;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeImages();
    }    

    private void initializeImages(){
        spacebackground.setImage(new Image(getClass().getResourceAsStream("/images/spacemainmenu.gif")));
        settings.setImage(new Image(getClass().getResourceAsStream("/images/settings.png")));
        doublependulum.setImage(new Image(getClass().getResourceAsStream("/images/doublependulum.png")));
        particleattraction.setImage(new Image(getClass().getResourceAsStream("/images/particleattraction.jpg")));
        newtonscradle.setImage(new Image(getClass().getResourceAsStream("/images/newtonscradle.jpg")));
        //spacebackground.setImage();
    }
            
    private void setImage(ImageView image, double opacity, double scaling){
        image.setOpacity(opacity);
        image.setScaleX(scaling);
        image.setScaleY(scaling);
    }
    
    @FXML
    private void dpOnMouseExited(MouseEvent event) {
        setImage(doublependulum, 0.31, 1);
    }

    @FXML
    private void dpOnMouseEntered(MouseEvent event) {
        setImage(doublependulum, 0.85, SCALING_FACTOR);
    }

    @FXML
    private void dpOnMouseClicked(MouseEvent event) {
    }

    @FXML
    private void cradleOnMouseExited(MouseEvent event) {
        setImage(newtonscradle, 0.31, 1);
    }

    @FXML
    private void cradleOnMouseEntered(MouseEvent event) {
        setImage(newtonscradle, 0.85, SCALING_FACTOR);
    }

    @FXML
    private void cradleOnMouseClicked(MouseEvent event) {
    }

    @FXML
    private void paOnMouseExited(MouseEvent event) {
        setImage(particleattraction, 0.31, 1);
    }

    @FXML
    private void paOnMouseEntered(MouseEvent event) {
        setImage(particleattraction, 0.85, SCALING_FACTOR);
    }

    @FXML
    private void paOnMouseClicked(MouseEvent event) {
    }

    @FXML
    private void setttingsOnMouseExited(MouseEvent event) {
        setImage(settings, 0.31, 1);
    }

    @FXML
    private void setttingsOnMouseEntered(MouseEvent event) {
        setImage(settings, 0.85, SCALING_FACTOR-0.05);
    }

    @FXML
    private void setttingsOnMouseClicked(MouseEvent event) {
    }
    
}

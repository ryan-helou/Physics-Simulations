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
    
    private final double SCALING_FACTOR = 1.09;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadImages();
    }    

    private void loadImages(){
        spacebackground.setImage(new Image(getClass().getResourceAsStream("/images/spacemainmenu.gif")));
        doublependulum.setImage(new Image(getClass().getResourceAsStream("/images/doublependulum.png")));
        particleattraction.setImage(new Image(getClass().getResourceAsStream("/images/particleattraction.PNG")));
        newtonscradle.setImage(new Image(getClass().getResourceAsStream("/images/newtonscradle.jpg")));
        settings.setImage(new Image(getClass().getResourceAsStream("/images/settings.png")));
    }

    private void imageSelection(ImageView image, double opacity, double scale){
        image.setOpacity(opacity);
        image.setScaleX(scale);
        image.setScaleY(scale);
    }
    
    @FXML
    private void dpOnMouseEntered(MouseEvent event) {
        imageSelection(doublependulum, 0.85, SCALING_FACTOR);
    }

    @FXML
    private void dpOnMouseExited(MouseEvent event) {
        imageSelection(doublependulum, 0.31, 1);
    }
    
    @FXML
    private void dpOnMouseClicked(MouseEvent event) {
    }

    @FXML
    private void cradleOnMouseEntered(MouseEvent event) {
        imageSelection(newtonscradle, 0.85, SCALING_FACTOR);
    }

    @FXML
    private void cradleOnMouseExited(MouseEvent event) {
        imageSelection(newtonscradle, 0.31, 1);
    }
    
    @FXML
    private void cradleOnMouseClicked(MouseEvent event) {
    }

    @FXML
    private void paOnMouseEntered(MouseEvent event) {
        imageSelection(particleattraction, 0.85, SCALING_FACTOR);
    }
    
    @FXML
    private void paOnMouseExited(MouseEvent event) {
        imageSelection(particleattraction, 0.31, 1);
    }

    @FXML
    private void paOnMouseClicked(MouseEvent event) {
    }

    @FXML
    private void setttingsOnMouseEntered(MouseEvent event) {
        imageSelection(settings, 0.85, SCALING_FACTOR);
    }
    
    @FXML
    private void setttingsOnMouseExited(MouseEvent event) {
        imageSelection(settings, 0.31, 1);
    }

    @FXML
    private void setttingsOnMouseClicked(MouseEvent event) {
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.vanier.template.controllers;

import edu.vanier.template.DoublePendulumMain;
import edu.vanier.template.pendulum.CradleMain;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class. Controller responsible for
 * the main menu viewed upon startup.
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
        initializeImages();
    }    


    /**
     * Sets the images from the images package to their designated
     * ImageView in the main menu upon startup (five in total).
     */
    private void initializeImages(){
        spacebackground.setImage(new Image(getClass().getResourceAsStream("/images/spacemainmenu.gif")));
        settings.setImage(new Image(getClass().getResourceAsStream("/images/settings.png")));
        doublependulum.setImage(new Image(getClass().getResourceAsStream("/images/doublependulum.png")));
        particleattraction.setImage(new Image(getClass().getResourceAsStream("/images/particleattraction.jpg")));
        newtonscradle.setImage(new Image(getClass().getResourceAsStream("/images/newtonscradle.jpg")));
        //spacebackground.setImage();
    }
            
    /**
     * Sets an image to a selected ImageView.
     * 
     * @param image location of the desired image (image folder).
     * @param opacity image opacity (0 to 1)
     * @param scaling image scaling, how big the image is relative to initial size.
     */
    private void setImage(ImageView image, double opacity, double scaling){
        image.setOpacity(opacity);
        image.setScaleX(scaling);
        image.setScaleY(scaling);
    }
    
    
    /**
     * Collection of methods responsible for the image scaling
     * and opacity effect upon hovering over them. 
     * @param event 
     */
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
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    DoublePendulumMain doublePendulumMain = new DoublePendulumMain();
    doublePendulumMain.start(stage);
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
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    CradleMain cradleMain = new CradleMain();
    cradleMain.start(stage);  
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
    private void paOnMouseClicked(MouseEvent event) throws IOException {
        Parent particle = FXMLLoader.load(getClass().getResource("/fxml/PAFXML.fxml"));
        Scene scene = new Scene(particle);
        
        //Stage stage = new Stage();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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

    
    /**
     * Getters and setters.
     * 
     * @return 
     */
    public ImageView getSpacebackground() {
        return spacebackground;
    }

    public void setSpacebackground(ImageView spacebackground) {
        this.spacebackground = spacebackground;
    }

    public ImageView getDoublependulum() {
        return doublependulum;
    }

    public void setDoublependulum(ImageView doublependulum) {
        this.doublependulum = doublependulum;
    }

    public ImageView getNewtonscradle() {
        return newtonscradle;
    }

    public void setNewtonscradle(ImageView newtonscradle) {
        this.newtonscradle = newtonscradle;
    }

    public ImageView getParticleattraction() {
        return particleattraction;
    }

    public void setParticleattraction(ImageView particleattraction) {
        this.particleattraction = particleattraction;
    }

    public ImageView getSettings() {
        return settings;
    }

    public void setSettings(ImageView settings) {
        this.settings = settings;
    }

    public double getSCALING_FACTOR() {
        return SCALING_FACTOR;
    }

    
    
    
}

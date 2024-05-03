/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.vanier.template.controllers;

import edu.vanier.template.DoublePendulumMain;
import edu.vanier.template.NewFXMain;
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

    /*
    Sets and Images to the existing ImageView upon startup
    */
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
    
}

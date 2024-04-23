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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        spacebackground.setImage(new Image(getClass().getResourceAsStream("/images/spacemainmenu.gif")));
        doublependulum.setImage(new Image(getClass().getResourceAsStream("/images/doublependulum.png")));
    }    

    @FXML
    private void dpOnMouseEntered(MouseEvent event) {
    }

    @FXML
    private void dpOnMouseClicked(MouseEvent event) {
    }
    
}

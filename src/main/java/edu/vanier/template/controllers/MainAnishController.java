/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.vanier.template.controllers;

import edu.vanier.template.DoublePendulumMain;
import edu.vanier.template.Settings;
import edu.vanier.template.pendulum.CradleMain;
import java.io.File;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * FXML Controller class. Controller responsible for the main menu viewed upon
 * startup.
 *
 * @author 2165566
 */
public class MainAnishController implements Initializable {
    
    //images
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

    public MediaPlayer player;
    private final double SCALING_FACTOR = 1.09; //scaling factor for image resizing

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //initializeMusic();
        initializeImages();
    }

    /**
     * Sets the images from the images package to their designated ImageView in
     * the main menu upon startup (five in total).
     */
    private void initializeImages() {
        spacebackground.setImage(new Image(getClass().getResourceAsStream("/images/spacemainmenu.gif")));
        settings.setImage(new Image(getClass().getResourceAsStream("/images/settings.png")));
        doublependulum.setImage(new Image(getClass().getResourceAsStream("/images/doublependulum.png")));
        particleattraction.setImage(new Image(getClass().getResourceAsStream("/images/particleattraction.jpg")));
        newtonscradle.setImage(new Image(getClass().getResourceAsStream("/images/newtonscradle.jpg")));
        //spacebackground.setImage();
    }

    /**
     * Activates the music upon startup of the main menu.
     */
    private void initializeMusic() {
        File soundFile = new File("C:\\Users\\salki\\OneDrive\\Documents\\GitHub\\physics-simulations\\src\\main\\resources\\Media\\bgmusic.mp3");
        Media media = new Media(soundFile.toURI().toString());
        player = new MediaPlayer(media);
        player.setVolume(0.1);          //volume
        player.setCycleCount(-1);
        player.stop();
        player.play();
        player.setOnEndOfMedia(()
                -> {
            player.play();
        });
    }

    /**
     * Assigns an image to a selected ImageView.
     *
     * @param image location of the desired image (image folder).
     * @param opacity image opacity (0 to 1)
     * @param scaling image scaling, how big the image is relative to initial
     * size.
     */
    public void setImage(ImageView image, double opacity, double scaling) {
        image.setOpacity(opacity);
        image.setScaleX(scaling);
        image.setScaleY(scaling);
    }

    
    //Double pendulum
    /**
     * Resizes the image on mouse entry.
     *
     * @param event
     */
    @FXML
    private void dpOnMouseEntered(MouseEvent event) {
        setImage(doublependulum, 0.85, SCALING_FACTOR);
    }

    /**
     * Sends the user to the designated 
     * simulation.
     *
     * @param event
     */
    @FXML
    private void dpOnMouseClicked(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        DoublePendulumMain doublePendulumMain = new DoublePendulumMain();
        doublePendulumMain.start(stage);
    }
    
    /**
     * Resizes and brightens the image 
     * back to its initial state
     *
     * @param event
     */
    @FXML
    private void dpOnMouseExited(MouseEvent event) {
        setImage(doublependulum, 0.31, 1);
    }

    //Newton's Cradle
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
    private void cradleOnMouseExited(MouseEvent event) {
        setImage(newtonscradle, 0.31, 1);
    }

    //Particle Attraction
    @FXML
    private void paOnMouseEntered(MouseEvent event) {
        setImage(particleattraction, 0.85, SCALING_FACTOR);
    }

    /**
     * Sends the user to the particle attraction upon clicking. This code
     * differs from the others since the particle attraction simulation is the
     * only one that utilizes an actual FXML file created in SceneBuilder.
     * Simply creates a new scene and stage, then loads the PAFXML file by
     * getting its location in the "/fxml/" package.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void paOnMouseClicked(MouseEvent event) throws IOException {
        Parent particle = FXMLLoader.load(getClass().getResource("/fxml/PAFXML.fxml"));
        Scene scene = new Scene(particle);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void paOnMouseExited(MouseEvent event) {
        setImage(particleattraction, 0.31, 1);
    }

    //Settings
    @FXML
    private void setttingsOnMouseEntered(MouseEvent event) {
        setImage(settings, 0.85, SCALING_FACTOR - 0.05);
    }

    @FXML
    private void setttingsOnMouseClicked(MouseEvent event) {
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Settings settings = new Settings();
        settings.start(stage);
        
         
    }

    @FXML
    private void setttingsOnMouseExited(MouseEvent event) {
        setImage(settings, 0.31, 1);
    }

    
    //Getters and setters
    public void setVolume(double volume) {
        if (player != null) {
            player.setVolume(volume);
        }
    }

    public double getVolume() {
        if (player != null) {
            return player.getVolume();
        }
        return 0.0;
    }
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

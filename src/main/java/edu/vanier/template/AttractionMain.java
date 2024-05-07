package edu.vanier.template;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class responsible for enabling the FXML file for particle attraction.
 * It is a Main class.
 * @author salki
 */
public class AttractionMain extends Application {
    
    /**
     * 
     * @param primaryStage
     * @throws IOException 
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/PAFXML.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Particle Attraction");
        primaryStage.setScene(scene);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();
        primaryStage.setAlwaysOnTop(false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

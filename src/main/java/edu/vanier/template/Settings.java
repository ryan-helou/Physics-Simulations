/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.template;

import edu.vanier.template.controllers.MainAnishController;
import edu.vanier.template.pendulum.CradleMain;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author anishmehra
 */
public class Settings extends Application {

     private MainAnishController controller;
    @Override
   

    public void start(Stage stage) {

        stage.setTitle("Settings :)");

        Group root = new Group();

        ImageView img = new ImageView();
        img.setImage(new Image(getClass().getResourceAsStream("/images/spacemainmenu.gif")));
        img.setFitWidth(800);
        img.setFitHeight(450);

        Font customFont2 = Font.loadFont(getClass().getResourceAsStream("/ChewyBubble.otf"), 15);
        Font customFont3 = Font.loadFont(getClass().getResourceAsStream("/ChewyBubble.otf"), 30);

        Label title = new Label("        Settings");
        labelInitialize(title, customFont3, 270, 10, Color.WHITE);

        Button backButton = new Button("Back");
        backButton.setFont(customFont2);
        backButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        backButton.setLayoutX(10);
        backButton.setLayoutY(10);

        backButton.setOnAction(event -> {
            try {
                new NewFXMain().start(stage);
            } catch (IOException ex) {
                Logger.getLogger(CradleMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Button exitButton = new Button("EXIT");
        exitButton.setFont(customFont2);
        exitButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        exitButton.setLayoutX(10);
        exitButton.setLayoutY(400);

        exitButton.setOnAction(e -> {
            stage.close();
        });

        TextArea about = new TextArea("Physics Simulators by:\n"
                + "Anish Mehra\n"
                + "Marco Salkica\n"
                + "Ryan Helou");
        about.setEditable(false);
        about.setFont(customFont2);
        about.setStyle("-fx-control-inner-background: black; -fx-text-fill: white;");
        about.setPrefWidth(200);
        about.setPrefHeight(150);
        about.setLayoutX(100);
        about.setLayoutY(115);
        
        
         TextArea about2 = new TextArea("Simulations made:\n"
                + "Newton's Cradle\n"
                + "Particle Attraction\n"
                + "Double Pendulum");
        about2.setEditable(false);
        about2.setFont(customFont2);
        about2.setStyle("-fx-control-inner-background: black; -fx-text-fill: white;");
        about2.setPrefWidth(200);
        about2.setPrefHeight(150);
        about2.setLayoutX(515);
        about2.setLayoutY(115);
        
        root.getChildren().addAll(img, title, backButton, exitButton, about,about2);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void labelInitialize(Label label, Font customFont, int layoutX, int layoutY,
            Color color) {
        label.setFont(customFont);
        label.setLayoutX(layoutX);
        label.setLayoutY(layoutY);
        label.setTextFill(color);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

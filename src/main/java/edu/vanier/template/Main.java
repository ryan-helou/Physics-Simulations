package edu.vanier.template;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author salki
 */

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    private Mover mover;
    private double mouseX;
    private double mouseY;
    
    //Delete later
    //just a demo build for testing functions etc
    @Override
    public void start(Stage primaryStage) {
        mover = new Mover(640, 360, 24); 
        
        Canvas canvas = new Canvas(1280, 720);
        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Mover Example");
        // We just need to bring the main window to front.
        primaryStage.setAlwaysOnTop(true);            
        primaryStage.show();
        primaryStage.setAlwaysOnTop(false);
        primaryStage.show();
        
        //setAlwaysOnTop is used because the java project will run behind the window if it is maximized

        scene.setOnMouseMoved(e -> {
            mouseX = e.getX();
            mouseY = e.getY();
            redraw(canvas);
        });

       AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                mover.update(new javafx.geometry.Point2D(mouseX, mouseY));
                redraw(canvas);
                
            }
            
            
        };
        animationTimer.start();
    }

    private void redraw(Canvas canvas) {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        mover.display(canvas.getGraphicsContext2D());
    }

    public static void main(String[] args) {
        launch(args);
    }
}


//so for class name change the "" in main and the class name ofc
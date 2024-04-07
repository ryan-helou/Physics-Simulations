/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.template;

/**
 *
 * @author ryanhelou
 */
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DoublePendulumMain extends Application {

    private final double length1 = 125; 
    private final double length2 = 125;
    private final double mass1 = 10;
    private final double mass2 = 10;
    private double angle1 = Math.PI / 2;
    private double angle2 = Math.PI / 2;
    private double angle1_v = 0;
    private double angle2_v = 0;
    private final double gravity = 1;

    private double px2 = -1;
    private double py2 = -1;
    private double cx, cy;

    private GraphicsContext gc;
    private Canvas bufferCanvas;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Double Pendulum Simulation");

        Group root = new Group();
        Canvas canvas = new Canvas(600, 350);
        bufferCanvas = new Canvas(600, 350); // Initialize bufferCanvas
        GraphicsContext buffer = bufferCanvas.getGraphicsContext2D();
        buffer.setFill(Color.WHITE);
        buffer.fillRect(0, 0, bufferCanvas.getWidth(), bufferCanvas.getHeight());

        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();

        cx = canvas.getWidth() / 2;
        cy = 50;
        buffer.translate(cx, cy);
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                draw();
            }
        }.start();

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void draw() {
        SnapshotParameters sp = new SnapshotParameters();
        sp.setFill(Color.TRANSPARENT);
        Image bufferImage = bufferCanvas.snapshot(sp, null);

        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 600, 350);
        gc.clearRect(0, 0, 600, 350);  // Clear the main canvas
        gc.drawImage(bufferImage, 0, 0);

        double a1_a = (-gravity * (2 * mass1 + mass2) * Math.sin(angle1) - mass2 * gravity 
                    * Math.sin(angle1 - 2 * angle2) - 2 * Math.sin(angle1 - angle2) * mass2 
                    * (angle2_v * angle2_v * length2 + angle1_v * angle1_v * length1 * Math.cos(angle1 - angle2)))
                    / (length1 * (2 * mass1 + mass2 - mass2 * Math.cos(2 * angle1 - 2 * angle2)));


        double a2_a = (2 * Math.sin(angle1 - angle2) * (angle1_v * angle1_v * length1 * (mass1 + mass2) + gravity 
                    * (mass1 + mass2) * Math.cos(angle1) + angle2_v * angle2_v * length2 * mass2 
                    * Math.cos(angle1 - angle2))) / (length2 * (2 * mass1 + mass2 - mass2 
                    * Math.cos(2 * angle1 - 2 * angle2)));


        gc.translate(cx, cy);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);

        double x1 = length1 * Math.sin(angle1);
        double y1 = length1 * Math.cos(angle1);

        double x2 = x1 + length2 * Math.sin(angle2);
        double y2 = y1 + length2 * Math.cos(angle2);

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);

        gc.strokeLine(0, 0, x1, y1);
        gc.strokeLine(x1, y1, x2, y2);

        gc.setFill(Color.BLACK);
        gc.fillOval(x1 - mass1, y1 - mass1, mass1 * 2, mass1 * 2);
        gc.fillOval(x2 - mass2, y2 - mass2, mass2 * 2, mass2 * 2);
        
        angle1_v += a1_a;
        angle2_v += a2_a;
        angle1 += angle1_v;
        angle2 += angle2_v;

        GraphicsContext bufferGc = bufferCanvas.getGraphicsContext2D();
        bufferGc.setStroke(Color.BLACK);
        if (px2 != -1 && py2 != -1) {
            bufferGc.setLineWidth(0.5);
            bufferGc.strokeLine(px2, py2, x2, y2);
        }

        px2 = x2;
        py2 = y2;

        gc.translate(-cx, -cy);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

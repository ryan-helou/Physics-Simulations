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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DoublePendulumMain extends Application {

    private double length1 = 125;
    private double length2 = 125;
    private double mass1 = 10;
    private double mass2 = 10;
    private double angle1 = Math.PI / 2;
    private double angle2 = Math.PI / 2;
    private double angle1_v = 0;
    private double angle2_v = 0;
    private double gravity = 1;
    private boolean showPath = true;

    private double previous_x2 = -1;
    private double previous_y2 = -1;
    private double center_x, center_y;

    private GraphicsContext gc;
    private Canvas bufferCanvas;
    private AnimationTimer animationTimer;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Double Pendulum Simulation");

        BorderPane root = new BorderPane();
        VBox controlPanel = new VBox(10);
        controlPanel.setPrefWidth(200);

        Canvas canvas = new Canvas(800, 600);
        bufferCanvas = new Canvas(800, 600);
        GraphicsContext buffer = bufferCanvas.getGraphicsContext2D();
        buffer.setFill(Color.WHITE);
        buffer.fillRect(0, 0, bufferCanvas.getWidth(), bufferCanvas.getHeight());

        Label length1Label = new Label("Length 1");
        Slider length1Slider = new Slider(50, 300, length1);
        Label length2Label = new Label("Length 2");
        Slider length2Slider = new Slider(50, 300, length2);
        Label mass1Label = new Label("Mass 1");
        Slider mass1Slider = new Slider(1, 20, mass1);
        Label mass2Label = new Label("Mass 2");
        Slider mass2Slider = new Slider(1, 20, mass2);
        Label gravityLabel = new Label("Gravity");
        Slider gravitySlider = new Slider(0.1, 10, gravity);
        CheckBox pathCheckbox = new CheckBox("Show Path");
        pathCheckbox.setSelected(showPath);

        Button startButton = new Button("Start");
        Button resetButton = new Button("Reset");

        controlPanel.getChildren().addAll(
                length1Label, length1Slider,
                length2Label, length2Slider,
                mass1Label, mass1Slider,
                mass2Label, mass2Slider,
                gravityLabel, gravitySlider,
                pathCheckbox,
                startButton, resetButton
        );
        root.setRight(controlPanel);
        root.setCenter(canvas);
        gc = canvas.getGraphicsContext2D();

        center_x = canvas.getWidth() / 2;
        center_y = canvas.getHeight() / 4;
        buffer.translate(center_x, center_y);

        length1Slider.valueProperty().addListener((obs, oldVal, newVal) -> length1 = newVal.doubleValue());
        length2Slider.valueProperty().addListener((obs, oldVal, newVal) -> length2 = newVal.doubleValue());
        mass1Slider.valueProperty().addListener((obs, oldVal, newVal) -> mass1 = newVal.doubleValue());
        mass2Slider.valueProperty().addListener((obs, oldVal, newVal) -> mass2 = newVal.doubleValue());
        gravitySlider.valueProperty().addListener((obs, oldVal, newVal) -> gravity = newVal.doubleValue());

        startButton.setOnAction(e -> {
            if (animationTimer != null) {
                animationTimer.stop();
            }
            animationTimer = new AnimationTimer() {
                public void handle(long currentNanoTime) {
                    draw();
                }
            };
            animationTimer.start();
        });

        resetButton.setOnAction(e -> {
            resetAnimation();
        });
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void draw() {
        SnapshotParameters sp = new SnapshotParameters();
        sp.setFill(Color.TRANSPARENT);
        Image bufferImage = bufferCanvas.snapshot(sp, null);

        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 600, 350);
        gc.clearRect(0, 0, 600, 350);
        gc.drawImage(bufferImage, 0, 0);

        double a1_a = (-gravity * (2 * mass1 + mass2) * Math.sin(angle1) - mass2 * gravity
                * Math.sin(angle1 - 2 * angle2) - 2 * Math.sin(angle1 - angle2) * mass2
                * (angle2_v * angle2_v * length2 + angle1_v * angle1_v * length1 * Math.cos(angle1 - angle2)))
                / (length1 * (2 * mass1 + mass2 - mass2 * Math.cos(2 * angle1 - 2 * angle2)));

        double a2_a = (2 * Math.sin(angle1 - angle2) * (angle1_v * angle1_v * length1 * (mass1 + mass2) + gravity
                * (mass1 + mass2) * Math.cos(angle1) + angle2_v * angle2_v * length2 * mass2
                * Math.cos(angle1 - angle2))) / (length2 * (2 * mass1 + mass2 - mass2
                * Math.cos(2 * angle1 - 2 * angle2)));

        gc.translate(center_x, center_y);
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
        if (previous_x2 != -1 && previous_y2 != -1) {
            bufferGc.setLineWidth(0.5);
            bufferGc.strokeLine(previous_x2, previous_y2, x2, y2);
        }

        previous_x2 = x2;
        previous_y2 = y2;

        gc.translate(-center_x, -center_y);
    }

    public void resetAnimation() {
        angle1 = Math.PI / 2;
        angle2 = Math.PI / 2;
        angle1_v = 0;
        angle2_v = 0;
        previous_x2 = -1;
        previous_y2 = -1;
        if (animationTimer != null) {
            animationTimer.stop();
        }

        GraphicsContext bufferGc = bufferCanvas.getGraphicsContext2D();
        bufferGc.setTransform(1, 0, 0, 1, 0, 0);
        bufferGc.setFill(Color.WHITE);
        bufferGc.fillRect(0, 0, bufferCanvas.getWidth(), bufferCanvas.getHeight());
        bufferGc.translate(center_x, center_y);

        draw();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

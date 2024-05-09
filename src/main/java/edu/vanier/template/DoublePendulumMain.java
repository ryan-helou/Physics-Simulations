/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.template;

/**
 *
 * @author ryanhelou
 */
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DoublePendulumMain extends Application {

    private double length1 = 150;
    private double length2 = 150;
    private double mass1 = 10;
    private double mass2 = 10;
    private double angle1 = Math.PI / 2;
    private double angle2 = Math.PI / 2;
    private double angle1_v = 0;
    private double angle2_v = 0;
    private double gravity = 3;
    private boolean showPath = true;

    private double previous_x2 = -1;
    private double previous_y2 = -1;
    private double center_x, center_y;

    private GraphicsContext gc;
    private Canvas bufferCanvas;
    private AnimationTimer animationTimer;
    private SnapshotParameters snapshotParameters;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Double Pendulum Simulation :)");

        snapshotParameters = new SnapshotParameters();
        snapshotParameters.setFill(Color.TRANSPARENT);

        BorderPane root = new BorderPane();
        VBox controlPanel = new VBox(10);
        controlPanel.setPrefWidth(215);

        Canvas canvas = new Canvas(1000, 1000);
        bufferCanvas = new Canvas(1000, 1000);
        GraphicsContext buffer = bufferCanvas.getGraphicsContext2D();
        buffer.setFill(Color.WHITE);
        buffer.fillRect(0, 0, bufferCanvas.getWidth(), bufferCanvas.getHeight());
        
         Image image = new Image(getClass().getResourceAsStream("/images/spacemainmenu.gif"));
        BackgroundImage backgroundImage = new BackgroundImage(image,
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));

        
        controlPanel.setBackground(new Background(backgroundImage));
        

        Font customFont = Font.loadFont(getClass().getResourceAsStream("/ChewyBubble.otf"), 22);
        Label length1Label = new Label("Length 1");
        length1Label.setTextFill(Color.WHITE);
        length1Label.setFont(customFont);
        Slider length1Slider = new Slider(50, 300, length1);
        length1Slider.setShowTickLabels(true);
        length1Slider.setShowTickMarks(true);
        length1Slider.setMajorTickUnit(50);
        length1Slider.setBlockIncrement(10);
        length1Slider.setStyle("-fx-font-size: 15px; -fx-font-family: '" + customFont.getFamily() + "'; -fx-font-weight: bold;");

        Label length2Label = new Label("Length 2");
        length2Label.setFont(customFont);
        length2Label.setTextFill(Color.WHITE);
        Slider length2Slider = new Slider(50, 300, length2);
        length2Slider.setShowTickLabels(true);
        length2Slider.setShowTickMarks(true);
        length2Slider.setMajorTickUnit(50);
        length2Slider.setBlockIncrement(10);
        length2Slider.setStyle("-fx-font-size: 15px; -fx-font-family: '" + customFont.getFamily() + "'; -fx-font-weight: bold;");

        Label mass1Label = new Label("Mass 1");
        mass1Label.setTextFill(Color.WHITE);
        mass1Label.setFont(customFont);

        Slider mass1Slider = new Slider(1, 25, mass1);
        mass1Slider.setShowTickLabels(true);
        mass1Slider.setShowTickMarks(true);
        mass1Slider.setMajorTickUnit(3);
        mass1Slider.setBlockIncrement(1);
        mass1Slider.setStyle("-fx-font-size: 15px; -fx-font-family: '" + customFont.getFamily() + "'; -fx-font-weight: bold;");

        Label mass2Label = new Label("Mass 2");
        mass2Label.setTextFill(Color.WHITE);
        mass2Label.setFont(customFont);

        Slider mass2Slider = new Slider(1, 25, mass2);
        mass2Slider.setShowTickLabels(true);
        mass2Slider.setShowTickMarks(true);
        mass2Slider.setMajorTickUnit(3);
        mass2Slider.setBlockIncrement(1);
        mass2Slider.setStyle("-fx-font-size: 15px; -fx-font-family: '" + customFont.getFamily() + "'; -fx-font-weight: bold;");

        Label gravityLabel = new Label("Gravity");
        gravityLabel.setTextFill(Color.WHITE);
        gravityLabel.setFont(customFont);

        Slider gravitySlider = new Slider(1, 15, gravity);
        gravitySlider.setShowTickLabels(true);
        gravitySlider.setShowTickMarks(true);
        gravitySlider.setMajorTickUnit(2);
        gravitySlider.setBlockIncrement(1);
        gravitySlider.setStyle("-fx-font-size: 15px; -fx-font-family: '" + customFont.getFamily() + "'; -fx-font-weight: bold;");

        CheckBox pathCheckbox = new CheckBox("Show Path");
        pathCheckbox.setTextFill(Color.WHITE);
        pathCheckbox.setFont(customFont);
        pathCheckbox.setSelected(showPath);

        Button startButton = new Button("Start");
        startButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        startButton.setFont(customFont);

        Button resetButton = new Button("Reset");
        resetButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        resetButton.setFont(customFont);

        Button backButton = new Button("Back  ");
        backButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        backButton.setFont(customFont);

        controlPanel.getChildren().addAll(
                
                length1Label, length1Slider,
                length2Label, length2Slider,
                mass1Label, mass1Slider,
                mass2Label, mass2Slider,
                gravityLabel, gravitySlider,
                pathCheckbox,
                startButton, resetButton, backButton
        );
        root.setRight(controlPanel);
        root.setCenter(canvas);
        gc = canvas.getGraphicsContext2D();

        center_x = canvas.getWidth() / 2;
        center_y = canvas.getHeight() / 4 + 150;
        buffer.translate(center_x, center_y);

        length1Slider.valueProperty().addListener((obs, oldVal, newVal) -> length1 = newVal.doubleValue());
        length2Slider.valueProperty().addListener((obs, oldVal, newVal) -> length2 = newVal.doubleValue());
        mass1Slider.valueProperty().addListener((obs, oldVal, newVal) -> mass1 = newVal.doubleValue());
        mass2Slider.valueProperty().addListener((obs, oldVal, newVal) -> mass2 = newVal.doubleValue());
        gravitySlider.valueProperty().addListener((obs, oldVal, newVal) -> gravity = newVal.doubleValue());

        startButton.setOnAction(e -> {
            startButton.setDisable(true);
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
            startButton.setDisable(false);
            resetAnimation();
            clearPath();
        });

        backButton.setOnAction(e -> {
            try {
                new NewFXMain().start(primaryStage);
            } catch (IOException ex) {
                Logger.getLogger(DoublePendulumMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        pathCheckbox.setOnAction(e -> {
            showPath = pathCheckbox.isSelected();
            if (!showPath) {
                clearPath();
            }
        });
    }

    private void draw() {
        SnapshotParameters sp = new SnapshotParameters();

        sp.setFill(Color.TRANSPARENT);
        Image bufferImage = bufferCanvas.snapshot(snapshotParameters, null);
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 1000, 350);
        gc.clearRect(0, 0, 1000, 350);
        gc.drawImage(bufferImage, 0, 0);

        double a1_a = ((-gravity / 4) * (2 * mass1 + mass2) * Math.sin(angle1) - mass2 * (gravity / 4)
                * Math.sin(angle1 - 2 * angle2) - 2 * Math.sin(angle1 - angle2) * mass2
                * (angle2_v * angle2_v * length2 + angle1_v * angle1_v * length1 * Math.cos(angle1 - angle2)))
                / (length1 * (2 * mass1 + mass2 - mass2 * Math.cos(2 * angle1 - 2 * angle2)));

        double a2_a = (2 * Math.sin(angle1 - angle2) * (angle1_v * angle1_v * length1 * (mass1 + mass2) + (gravity / 4)
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
        if (showPath) {
            bufferGc.setStroke(Color.BLACK);
            bufferGc.setLineWidth(0.5);
            if (previous_x2 != -1 && previous_y2 != -1) {
                bufferGc.strokeLine(previous_x2, previous_y2, x2, y2);
            }
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

    private void clearPath() {
        GraphicsContext bufferGc = bufferCanvas.getGraphicsContext2D();
        bufferGc.setFill(Color.WHITE);
        bufferGc.fillRect(-400, -200, bufferCanvas.getWidth(), bufferCanvas.getHeight());
    }

    public static void main(String[] args) {
        launch(args);
    }

    public double getLength1() {
        return length1;
    }

    public void setLength1(double length1) {
        this.length1 = length1;
    }

    public double getLength2() {
        return length2;
    }

    public void setLength2(double length2) {
        this.length2 = length2;
    }

    public double getMass1() {
        return mass1;
    }

    public void setMass1(double mass1) {
        this.mass1 = mass1;
    }

    public double getMass2() {
        return mass2;
    }

    public void setMass2(double mass2) {
        this.mass2 = mass2;
    }

    public double getAngle1() {
        return angle1;
    }

    public void setAngle1(double angle1) {
        this.angle1 = angle1;
    }

    public double getAngle2() {
        return angle2;
    }

    public void setAngle2(double angle2) {
        this.angle2 = angle2;
    }

    public double getAngle1_v() {
        return angle1_v;
    }

    public void setAngle1_v(double angle1_v) {
        this.angle1_v = angle1_v;
    }

    public double getAngle2_v() {
        return angle2_v;
    }

    public void setAngle2_v(double angle2_v) {
        this.angle2_v = angle2_v;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public boolean isShowPath() {
        return showPath;
    }

    public void setShowPath(boolean showPath) {
        this.showPath = showPath;
    }

    public double getPrevious_x2() {
        return previous_x2;
    }

    public void setPrevious_x2(double previous_x2) {
        this.previous_x2 = previous_x2;
    }

    public double getPrevious_y2() {
        return previous_y2;
    }

    public void setPrevious_y2(double previous_y2) {
        this.previous_y2 = previous_y2;
    }

    public double getCenter_x() {
        return center_x;
    }

    public void setCenter_x(double center_x) {
        this.center_x = center_x;
    }

    public double getCenter_y() {
        return center_y;
    }

    public void setCenter_y(double center_y) {
        this.center_y = center_y;
    }

}

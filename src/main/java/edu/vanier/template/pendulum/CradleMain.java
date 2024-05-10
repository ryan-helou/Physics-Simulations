/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package edu.vanier.template.pendulum;

import edu.vanier.template.NewFXMain;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.animation.Transition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Main class responsible for the cradle animation
 *
 * @author salki
 */
public class CradleMain extends Application {

    private Canvas canvas;
    private boolean initialStart = false;
    private boolean centerPendulum;
    private boolean showTrail = true;
    private double armLength = 100.0;
    private double centerY;
    private final double PENDULUM_SPACING = -69;
    private int direction = 0;
    private AnimationTimer animationTimer;

    /**
     * status: 0 = outermost bobs 1 = innermost bobs 2 = center bob
     */
    /**
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        //setTestValue(0);
        Label lengthLabel = new Label("Length:");
        lengthLabel.setLayoutX(10);
        lengthLabel.setLayoutY(330);

        primaryStage.setTitle("Newton's Cradle Simulation");
        canvas = new Canvas(800, 450);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        StackPane root = new StackPane();

        Transition transition;
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        ImageView img = new ImageView();
        img.setImage(new Image(getClass().getResourceAsStream("/images/spacemainmenu.gif")));
        img.setFitWidth(800);
        img.setFitHeight(450);

        Group group = new Group();
        group.getChildren().addAll(img, canvas);

        Pendulum[] pendulums = new Pendulum[5];
        centerY = (canvas.getHeight() / 2) - 69.420;

        Circle[] circles = new Circle[5];
        for (int i = 0; i < circles.length; i++) {
            circles[i] = new Circle(15, Color.RED);
            group.getChildren().add(circles[i]);
        }
        circles[1].setCenterX(2);
        circles[1].setCenterY(2);
        circles[2].setCenterX(3);
        circles[2].setCenterX(3);

        //Distance between circles = 31
        // Current pendulum in the center
        pendulums[0] = new Pendulum(gc, new Vector2D(canvas.getWidth() / 2, centerY), (float) armLength, showTrail, 0);
        // Additional pendulums on the left side
        for (int i = 1; i <= 2; i++) {
            double x = canvas.getWidth() / 2 - (armLength + PENDULUM_SPACING) * i;
            pendulums[i] = new Pendulum(gc, new Vector2D(x, centerY), (float) armLength, showTrail, i);

        }

        // Additional pendulums on the right side
        for (int i = 3; i <= 4; i++) {
            double x = canvas.getWidth() / 2 + (armLength + PENDULUM_SPACING) * (i - 2);
            pendulums[i] = new Pendulum(gc, new Vector2D(x, centerY), (float) armLength, showTrail, i);
        }

        group.setOnMousePressed(e -> {
            if (circles[0].isPressed()) {
                centerPendulum = true;
            } else {
                centerPendulum = false;
            }
            resetAllPendulums(pendulums); // Reset all pendulums when one is clicked, originally at the end

            for (Pendulum p : pendulums) {
                p.clicked((int) e.getX(), (int) e.getY());
                if (p.equals(pendulums[1]) && circles[0].isPressed()) {   //nice
                    p.clicked((int) ((e.getX()) - 31), (int) e.getY());
                    pendulums[2].clicked((int) ((e.getX() - 62)), (int) e.getY());
                }

                if (p.equals(pendulums[3]) && circles[0].isPressed()) {   //nice
                    p.clicked((int) ((e.getX()) + 31), (int) e.getY());          //problem is here
                    pendulums[4].clicked((int) ((e.getX() + 62)), (int) e.getY());
                }

                if (p.equals(pendulums[2])) {
                    p.clicked((int) ((e.getX()) - 31), (int) e.getY());
                }

                if (p.equals(pendulums[4])) {
                    p.clicked((int) ((e.getX()) + 31), (int) e.getY());
                }

                if (p.equals(pendulums[2]) && circles[0].isPressed() && e.getX() < 399) {
                    p.clicked((int) ((e.getX()) - 31), (int) e.getY());
                }

                if (p.equals(pendulums[4]) && circles[0].isPressed() && e.getX() > 401) {
                    p.clicked((int) ((e.getX()) + 31), (int) e.getY());
                }
            }
        });

        group.setOnMouseDragged(e -> {
            for (Pendulum p : pendulums) {
                p.dragged((int) e.getX(), (int) e.getY());

                if (circles[0].isPressed()) {
                    pendulums[0].dragged((int) e.getX(), (int) e.getY());
                    pendulums[1].dragged((int) e.getX() - 31, (int) e.getY());
                    pendulums[2].dragged((int) e.getX() - 62, (int) e.getY());
                    pendulums[3].dragged((int) e.getX() + 31, (int) e.getY());
                    pendulums[4].dragged((int) e.getX() + 62, (int) e.getY());
                }
                if (p.equals(pendulums[1]) && circles[0].isPressed() && e.getX() < 399) {   //nice
                    pendulums[1].clicked((int) ((e.getX() - 31)), (int) e.getY());
                    pendulums[2].clicked((int) ((e.getX() - 62)), (int) e.getY());
                    pendulums[3].clicked((int) ((e.getX())), (int) e.getY());
                    pendulums[3].clicked((int) ((e.getX())), (int) e.getY());
                    pendulums[3].stopDragging();
                    pendulums[4].stopDragging();
                    pendulums[3].resetAll();
                    pendulums[4].resetAll();

                    p.dragged((int) ((e.getX() - 31)), (int) e.getY());
                    pendulums[2].dragged((int) ((e.getX() - 62)), (int) e.getY());
                }

                if (p.equals(pendulums[3]) && circles[0].isPressed() && (e.getX() > 401)) {   //nice
                    pendulums[1].clicked((int) ((e.getX() - 31)), (int) e.getY());
                    pendulums[2].clicked((int) ((e.getX() - 62)), (int) e.getY());
                    pendulums[3].clicked((int) ((e.getX() + 31)), (int) e.getY());
                    pendulums[4].clicked((int) ((e.getX() + 62)), (int) e.getY());

                    p.dragged((int) ((e.getX() + 31)), (int) e.getY());
                    pendulums[4].dragged((int) ((e.getX() + 62)), (int) e.getY());
                    pendulums[1].stopDragging();
                    pendulums[2].stopDragging();

                }

                if (p.equals(pendulums[2]) && !circles[0].isPressed()) {
                    p.dragged((int) ((e.getX()) - 31), (int) e.getY());
                }

                if (p.equals(pendulums[2]) && circles[0].isPressed() && circles[0].getCenterX() < 400) {
                    p.dragged((int) ((e.getX()) - 62), (int) e.getY());
                }

                if (p.equals(pendulums[4]) && !circles[0].isPressed()) {
                    p.dragged((int) ((e.getX()) + 31), (int) e.getY());
                }

                if (p.equals(pendulums[4]) && circles[0].isPressed() && circles[0].getCenterX() > 400) {
                    p.dragged((int) ((e.getX()) + 62), (int) e.getY());
                }
            }
        });

        group.setOnMouseReleased(e -> {
            for (Pendulum p : pendulums) {
                p.stopDragging();
            }
        });

        /**
         * Dummy transition used as a delay for the collision timer to avoid it
         * activating during the first millisecond, where all the circles are
         * initially placed together.
         *
         */
        transition = new Transition() {
            {
                setCycleDuration(Duration.millis(5)); //duration set to 10 ms
            }

            @Override
            protected void interpolate(double d) {
                if (d == 1.0) { // 1.0 means end of transition, LEAVE IT AS ONE @RYAN
                    initialStart = true;
                }
            }
        };
        transition.setCycleCount(1);
        transition.play();

        /**
         * AnimationTimer used to detect collision and update the canvas
         */
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {      //real pendulum order: 21034
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                for (int i = 0; i < pendulums.length; i++) {
                    if (i == 0) {
                        pendulums[0].go();
                        circles[0].setCenterX(pendulums[0].loc.getX());
                        circles[0].setCenterY(pendulums[0].loc.getY());
                    } else {
                        pendulums[i].go();
                        circles[i].setCenterX(pendulums[i].loc.getX());
                        circles[i].setCenterY(pendulums[i].loc.getY());
                    }
                }

                circles[0].setCenterX(pendulums[0].loc.getX());
                circles[0].setCenterY(pendulums[0].loc.getY());

                /**
                 * Outermost bobs 2 1 3 4
                 */
                if (!centerPendulum) {
                    if (circles[1].getBoundsInParent().intersects(circles[0].getBoundsInParent()) && initialStart) {
                        // Reverse velocities of adjacent bobs (1 and 2)
                        double temp = pendulums[1].theta_vel;
                        pendulums[1].theta_vel = pendulums[3].theta_vel;
                        pendulums[2].theta_vel = pendulums[3].theta_vel;
                        pendulums[3].theta_vel = (float) temp;
                        pendulums[4].theta_vel = (float) temp;
                        pendulums[1].resetAll();
                        pendulums[2].resetAll();

                    }

                    if (circles[3].getBoundsInParent().intersects(circles[0].getBoundsInParent()) && initialStart) {
                        // Reverse velocities of adjacent bobs (2 and 0)
                        double temp = pendulums[3].theta_vel;
                        pendulums[3].theta_vel = pendulums[0].theta_vel;
                        pendulums[4].theta_vel = pendulums[0].theta_vel;
                        pendulums[1].theta_vel = (float) temp;
                        pendulums[2].theta_vel = (float) temp;

                        pendulums[3].resetAll();
                        pendulums[4].resetAll();
                    }

                    if (circles[2].getBoundsInParent().intersects(circles[1].getBoundsInParent()) && initialStart) {
                        // Reverse velocities of adjacent bobs (1 and 2)
                        double temp = pendulums[2].theta_vel;
                        pendulums[2].theta_vel = pendulums[4].theta_vel;
                        pendulums[4].theta_vel = (float) temp;
                        pendulums[2].resetAll();
                    }

                    if (circles[4].getBoundsInParent().intersects(circles[3].getBoundsInParent()) && initialStart) {
                        // Reverse velocities of adjacent bobs (2 and 0)
                        double temp = pendulums[4].theta_vel;
                        pendulums[4].theta_vel = pendulums[3].theta_vel;
                        pendulums[2].theta_vel = (float) temp;
                        pendulums[4].resetAll();
                    }
                }

                //=======================================
                /**
                 * Central Bob 0
                 */
                if (centerPendulum) {

                    if (circles[1].getBoundsInParent().intersects(circles[0].getBoundsInParent()) && initialStart) {
                        // Reverse velocities of adjacent bobs (2 and 0)
                        double temp = pendulums[0].theta_vel;
                        pendulums[3].theta_vel = pendulums[1].theta_vel;
                        pendulums[4].theta_vel = pendulums[1].theta_vel;
                        pendulums[0].theta_vel = pendulums[1].theta_vel;
                        pendulums[1].theta_vel = (float) temp;
                        pendulums[2].theta_vel = (float) temp;
                        pendulums[2].theta_vel = (float) temp;
                        pendulums[0].theta_vel = (float) temp;
                        pendulums[3].resetAll();
                        pendulums[4].resetAll();
                    }

                    if (circles[3].getBoundsInParent().intersects(circles[0].getBoundsInParent()) && initialStart) {

                        // Reverse velocities of adjacent bobs (1 and 2)
                        double temp = pendulums[0].theta_vel;
                        pendulums[1].theta_vel = pendulums[3].theta_vel;
                        pendulums[2].theta_vel = pendulums[3].theta_vel;
                        pendulums[0].theta_vel = pendulums[3].theta_vel;
                        pendulums[0].theta_vel = (float) temp;
                        pendulums[3].theta_vel = (float) temp;
                        pendulums[4].theta_vel = (float) temp;

                        pendulums[1].resetAll();
                        pendulums[2].resetAll();

                    }
                }

            }
        };
        animationTimer.start();

        Font customFont = Font.loadFont(getClass().getResourceAsStream("/ChewyBubble.otf"), 22);
        Font customFont2 = Font.loadFont(getClass().getResourceAsStream("/ChewyBubble.otf"), 15);
        Font customFont3 = Font.loadFont(getClass().getResourceAsStream("/ChewyBubble.otf"), 30);

        CheckBox showTrailCheckbox = new CheckBox("Show Trail");
        checkboxInitialize(showTrailCheckbox, customFont2, 25, 365, true, Color.WHITE);

        // event listener to toggle showing/hiding the trail
        showTrailCheckbox.setOnAction(event -> {
            boolean showTrail = showTrailCheckbox.isSelected();
            for (Pendulum pendulum : pendulums) {
                pendulum.setShowTrail(showTrail);
            }
        });

        CheckBox dampingCheckbox = new CheckBox("Damping");
        checkboxInitialize(dampingCheckbox, customFont2, 25, 320, false, Color.WHITE);

        dampingCheckbox.setOnAction(event -> {
            boolean isDampingEnabled = dampingCheckbox.isSelected();
            float newDamping;
            if (isDampingEnabled) {
                newDamping = 0.998f; // some damping
            } else {
                newDamping = 1.0f;   // No damping
            }
            for (Pendulum pendulum : pendulums) {
                pendulum.setDamping(newDamping);
            }
        });

        CheckBox pauseCheckbox = new CheckBox("Pause");
        checkboxInitialize(pauseCheckbox, customFont2, 25, 280, false, Color.WHITE);

        pauseCheckbox.setOnAction(event -> {
            if (pauseCheckbox.isSelected()) {
                animationTimer.stop();
            } else {
                animationTimer.start();
            }
        });

        Label title = new Label("Newton's Cradle");
        labelInitialize(title, customFont3, 270, 10, Color.WHITE);

        Label length = new Label("Length");
        labelInitialize(length, customFont, 670, 25, Color.WHITE);

        Label gravityLabel = new Label("Gravity");
        labelInitialize(gravityLabel, customFont, 670, 125, Color.WHITE);

        Label massLabel = new Label("  Mass");
        labelInitialize(massLabel, customFont, 670, 225, Color.WHITE);

        Slider lengthSlider = new Slider(75, 150, 100); // Minimum, Maximum, Default Value
        sliderInitialize(lengthSlider, true, true, 25, 5, true, 150, 645, 65);
        lengthSlider.setBlockIncrement(25);

        // event listener to update pendulum lengths when the slider value changes
        lengthSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double newLength = newValue.doubleValue();
            updatePendulumLengths(pendulums, newLength);
            resetAllPendulums(pendulums);
        });

        Slider gravitySlider = new Slider(1, 20, 10);
        sliderInitialize(gravitySlider, true, true, 5, 4, true, 150, 645, 165);

        gravitySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            for (Pendulum pendulum : pendulums) {
                pendulum.setGravity((float) (newValue.doubleValue() * 0.1));
            }
            resetAllPendulums(pendulums);
        });

        Slider massSlider = new Slider(1, 10, 10);
        sliderInitialize(massSlider, true, true, 1, 0, true, 150, 645, 265);

        massSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int massIndex = newValue.intValue();
            Color color = getColorBasedOnMass(massIndex);

            for (int i = 0; i < pendulums.length; i++) {
                pendulums[i].setMass(massIndex);
                circles[i].setFill(color);
            }
        });

        Button backButton = new Button("Back");
        backButton.setFont(customFont2);
        backButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        backButton.setLayoutX(10);
        backButton.setLayoutY(10);

        backButton.setOnAction(event -> {
            resetAllPendulums(pendulums);
            try {
                new NewFXMain().start(primaryStage);
            } catch (IOException ex) {
                Logger.getLogger(CradleMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        // Add the Slider to the root layout
        group.getChildren().addAll(lengthSlider, length, showTrailCheckbox,
                dampingCheckbox, gravityLabel, gravitySlider, massSlider,
                massLabel, backButton, title, pauseCheckbox);
        primaryStage.setScene(new Scene(group, 800, 400)); // Set the scene with the group
        primaryStage.show();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void checkboxInitialize(CheckBox checkbox, Font customFont, int layoutX, int layoutY,
            boolean selected, Color color) {
        checkbox.setFont(customFont);
        checkbox.setLayoutX(layoutX);
        checkbox.setLayoutY(layoutY);
        checkbox.setSelected(selected);
        checkbox.setTextFill(color);
    }

    /**
     * Initializes a label. Created to reduce repeated code.
     *
     * @param label indicates a chosen label.
     * @param customFont indicates a chosen font.
     * @param layoutX indicates label x position.
     * @param layoutY indicates label y position.
     */
    public void labelInitialize(Label label, Font customFont, int layoutX, int layoutY,
            Color color) {
        label.setFont(customFont);
        label.setLayoutX(layoutX);
        label.setLayoutY(layoutY);
        label.setTextFill(color);
    }

    /**
     * Initializes a slider. Created to reduce repeated code.
     *
     * @param slider indicates a chosen slider.
     * @param tickStatus Enables/Disables slider tick labels.
     * @param tickMarks Enables/disables slider tick marks.
     * @param tickUnit indicates slider tick units
     * @param tickCount indicates slider tick count
     * @param snapTicks Enables/disables slider snap ticks
     * @param prefWidth pref height for the slider
     * @param layoutX slider x position
     * @param layoutY slider y position
     */
    public void sliderInitialize(Slider slider, boolean tickStatus, boolean tickMarks,
            int tickUnit, int tickCount, boolean snapTicks,
            int prefWidth, int layoutX, int layoutY) {

        slider.setShowTickLabels(tickStatus);
        slider.setShowTickMarks(tickMarks);
        slider.setMajorTickUnit(tickUnit);
        slider.setMinorTickCount(tickCount);
        slider.setSnapToTicks(snapTicks);
        slider.setPrefWidth(prefWidth);
        slider.setLayoutX(layoutX);
        slider.setLayoutY(layoutY);
    }

    /**
     *
     * @param pendulums array containing the pendulums used during the
     * simulation.
     * @param newLength
     */
    private void updatePendulumLengths(Pendulum[] pendulums, double newLength) {
        // Update the lengths of all pendulums
        for (Pendulum pendulum : pendulums) {
            pendulum.r = (float) newLength;
        }
    }

    /**
     *
     * @param pendulums
     */
    public void resetAllPendulums(Pendulum[] pendulums) {
        for (Pendulum p : pendulums) {
            p.resetAll();
        }
    }

    /**
     *
     * @param showTrail Boolean indicates whether the trail is active or not
     */
    public void setShowTrail(boolean showTrail) {
        this.showTrail = showTrail;
    }

    /**
     *
     * @param mass mass of the particle taken from slider.
     * @return
     */
    private Color getColorBasedOnMass(int mass) {
        float hue = 120 - (mass - 1) * 12;
        return Color.hsb(hue, 1.0, 1.0);
    }

    public boolean isInitialStart() {
        return initialStart;
    }

    public void setInitialStart(boolean initialStart) {
        this.initialStart = initialStart;
    }

    public boolean isCenterPendulum() {
        return centerPendulum;
    }

    public void setCenterPendulum(boolean centerPendulum) {
        this.centerPendulum = centerPendulum;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

}

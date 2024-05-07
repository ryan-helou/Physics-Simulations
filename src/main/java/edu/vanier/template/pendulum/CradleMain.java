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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    private int testValue = 0;
    private int direction = 0;
    private AnimationTimer animationTimer;
    private CheckBox pauseCheckbox;

    /**
     * status: 0 = outermost bobs 1 = innermost bobs 2 = center bob
     */
    /**
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        setTestValue(0);
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
        double centerY = (canvas.getHeight() / 2) - 69.420;
        double armLength = 100.0;
        double spacing = -69;

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
            double x = canvas.getWidth() / 2 - (armLength + spacing) * i;
            pendulums[i] = new Pendulum(gc, new Vector2D(x, centerY), (float) armLength, showTrail, i);

        }

        // Additional pendulums on the right side
        for (int i = 3; i <= 4; i++) {
            double x = canvas.getWidth() / 2 + (armLength + spacing) * (i - 2);
            pendulums[i] = new Pendulum(gc, new Vector2D(x, centerY), (float) armLength, showTrail, i);
        }

        group.setOnMousePressed(e -> {
            setTestValue(1);
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

                //potential fix for initial center bug: make it so that it resets completely @ center
                if (circles[0].getCenterX() <= 401 && circles[0].getCenterX() >= 399 && circles[0].isPressed() && testValue == 1) {
                    setTestValue(0);
                    resetAllPendulums(pendulums);
                    pendulums[0].clicked((int) e.getX(), (int) e.getY());
                    pendulums[1].clicked((int) e.getX() - 31, (int) e.getY());
                    pendulums[2].clicked((int) e.getX() - 62, (int) e.getY());
                    pendulums[3].clicked((int) e.getX() + 31, (int) e.getY());
                    pendulums[4].clicked((int) e.getX() + 62, (int) e.getY());

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
         * AnimationTimer used to detect collision and update 
         * the canvas
         */
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {                              //real pendulum order: 21034
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
                 * Innermost bobs
                 */
                if (!centerPendulum) {
                    if (circles[1].getBoundsInParent().intersects(circles[0].getBoundsInParent()) && initialStart) {
                        // Reverse velocities of adjacent bobs (1 and 2)
                        double temp = pendulums[1].theta_vel;
                        pendulums[1].theta_vel = pendulums[3].theta_vel;
                        pendulums[2].theta_vel = pendulums[3].theta_vel;    /////////////
                        pendulums[3].theta_vel = (float) temp;
                        pendulums[4].theta_vel = (float) temp;              //||||||
                        pendulums[1].resetAll();
                        pendulums[2].resetAll();                            ///////                    
                    }

                    if (circles[3].getBoundsInParent().intersects(circles[0].getBoundsInParent()) && initialStart) {
                        testValue = 0;
                        // Reverse velocities of adjacent bobs (2 and 0)
                        double temp = pendulums[3].theta_vel;
                        pendulums[3].theta_vel = pendulums[0].theta_vel;
                        pendulums[4].theta_vel = pendulums[0].theta_vel;            //|||||
                        pendulums[1].theta_vel = (float) temp;
                        pendulums[2].theta_vel = (float) temp;               //////////////////////////////////////////

                        pendulums[3].resetAll();
                        pendulums[4].resetAll();            //|||||||||||||||||||
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
                 * Innermost bobs
                 */
                if (centerPendulum) {

                    if (circles[1].getBoundsInParent().intersects(circles[0].getBoundsInParent()) && initialStart) {
                        testValue = 0;
                        // Reverse velocities of adjacent bobs (2 and 0)
                        double temp = pendulums[0].theta_vel;
                        pendulums[3].theta_vel = pendulums[1].theta_vel;
                        pendulums[4].theta_vel = pendulums[1].theta_vel;            //|||||
                        pendulums[0].theta_vel = pendulums[1].theta_vel;
                        pendulums[1].theta_vel = (float) temp;
                        pendulums[2].theta_vel = (float) temp;                              //////////////////////////////////////////
                        pendulums[0].theta_vel = (float) temp;                                        
                        pendulums[3].resetAll();
                        pendulums[4].resetAll();            //|||||||||||||||||||
                    }

                    if (circles[3].getBoundsInParent().intersects(circles[0].getBoundsInParent()) && initialStart) {

                        // Reverse velocities of adjacent bobs (1 and 2)
                        double temp = pendulums[0].theta_vel;
                        pendulums[1].theta_vel = pendulums[3].theta_vel;
                        pendulums[2].theta_vel = pendulums[3].theta_vel;    /////////////
                        pendulums[0].theta_vel = pendulums[3].theta_vel;
                        pendulums[0].theta_vel = (float) temp;
                        pendulums[3].theta_vel = (float) temp;
                        pendulums[4].theta_vel = (float) temp;              //||||||

                        pendulums[1].resetAll();
                        pendulums[2].resetAll();                            ///////

                    }
                }

            }
            // }
        };
        animationTimer.start();

        Font customFont = Font.loadFont(getClass().getResourceAsStream("/ChewyBubble.otf"), 22);
        Font customFont2 = Font.loadFont(getClass().getResourceAsStream("/ChewyBubble.otf"), 15);
        Font customFont3 = Font.loadFont(getClass().getResourceAsStream("/ChewyBubble.otf"), 30);

        Label length = new Label("Length");
        length.setTextFill(Color.WHITE);

        length.setFont(customFont);
        length.setLayoutX(670);
        length.setLayoutY(25);

        // Create a Slider component for adjusting pendulum length
        Slider lengthSlider = new Slider(75, 150, 100); // Minimum, Maximum, Default Value
        lengthSlider.setShowTickLabels(true);
        lengthSlider.setShowTickMarks(true);
        lengthSlider.setPrefWidth(150);
        lengthSlider.setMajorTickUnit(25);
        lengthSlider.setMinorTickCount(5);
        lengthSlider.setBlockIncrement(25);
        lengthSlider.setLayoutX(645);
        lengthSlider.setLayoutY(65);

        // Add an event listener to update pendulum lengths when the slider value changes
        lengthSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double newLength = newValue.doubleValue();
            updatePendulumLengths(pendulums, newLength);
            resetAllPendulums(pendulums);
        });
        CheckBox showTrailCheckbox = new CheckBox("Show Trail");
        showTrailCheckbox.setSelected(true); // Default to showing trail
        showTrailCheckbox.setFont(customFont2);
        showTrailCheckbox.setLayoutX(25);
        showTrailCheckbox.setLayoutY(365);
        showTrailCheckbox.setTextFill(Color.WHITE);

        // Add an event listener to toggle showing/hiding the trail
        showTrailCheckbox.setOnAction(event -> {
            boolean showTrail = showTrailCheckbox.isSelected();
            for (Pendulum pendulum : pendulums) {
                pendulum.setShowTrail(showTrail);
            }
        });

        CheckBox dampingCheckbox = new CheckBox("Damping");
        dampingCheckbox.setFont(customFont2);
        dampingCheckbox.setLayoutX(25);
        dampingCheckbox.setLayoutY(320);
        dampingCheckbox.setSelected(false);
        dampingCheckbox.setTextFill(Color.WHITE);

        Label title = new Label("Newton's Cradle");
        title.setLayoutX(270);
        title.setLayoutY(10);
        title.setFont(customFont3);
        title.setTextFill(Color.WHITE);

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
        Slider gravitySlider = new Slider(1, 20, 10);

        gravitySlider.setShowTickLabels(true);
        gravitySlider.setShowTickMarks(true);
        gravitySlider.setMajorTickUnit(5);
        gravitySlider.setMinorTickCount(4);
        gravitySlider.setSnapToTicks(true);
        gravitySlider.setLayoutX(645);
        gravitySlider.setLayoutY(165);
        gravitySlider.setPrefWidth(150);

        Label gravityLabel = new Label("Gravity");
        gravityLabel.setFont(customFont);
        gravityLabel.setLayoutX(670);
        gravityLabel.setLayoutY(125);
        gravityLabel.setTextFill(Color.WHITE);

        gravitySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            for (Pendulum pendulum : pendulums) {
                pendulum.setGravity((float) (newValue.doubleValue() * 0.1));
            }
            resetAllPendulums(pendulums);
        });

        Slider massSlider = new Slider(1, 10, 10);
        massSlider.setShowTickLabels(true);
        massSlider.setShowTickMarks(true);
        massSlider.setMajorTickUnit(1);
        massSlider.setMinorTickCount(0);
        massSlider.setSnapToTicks(true);
        massSlider.setPrefWidth(150);
        massSlider.setLayoutX(645);
        massSlider.setLayoutY(265);

        Label massLabel = new Label("  Mass");
        massLabel.setFont(customFont);
        massLabel.setLayoutX(670);
        massLabel.setLayoutY(225);
        massLabel.setTextFill(Color.WHITE);

        massSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int massIndex = newValue.intValue();
            Color color = getColorBasedOnMass(massIndex);

            for (int i = 0; i < pendulums.length; i++) {
                pendulums[i].setMass(massIndex);
                circles[i].setFill(color);
            }
        });

        pauseCheckbox = new CheckBox("Pause");
        pauseCheckbox.setFont(customFont2);
        pauseCheckbox.setLayoutX(25);
        pauseCheckbox.setLayoutY(280);
        pauseCheckbox.setTextFill(Color.WHITE);

        pauseCheckbox.setOnAction(event -> {
            if (pauseCheckbox.isSelected()) {
                animationTimer.stop();
            } else {
                animationTimer.start();
            }
        });

        Button backButton = new Button("Back");
        backButton.setFont(customFont2);
        backButton.setLayoutX(10);
        backButton.setLayoutY(10);

        backButton.setOnAction(event -> {
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

    /**
     *
     * @param showTrail Boolean indicates whether the trail is active or not
     */
    public void setShowTrail(boolean showTrail) {
        this.showTrail = showTrail;
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
     * @param pendulums
     * @param newLength
     */
    private void updatePendulumLengths(Pendulum[] pendulums, double newLength) {
        // Update the lengths of all pendulums
        for (Pendulum pendulum : pendulums) {
            pendulum.r = (float) newLength;
        }
    }

    public int getTestValue() {
        return testValue;
    }

    public void setTestValue(int testValue) {
        this.testValue = testValue;
    }

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


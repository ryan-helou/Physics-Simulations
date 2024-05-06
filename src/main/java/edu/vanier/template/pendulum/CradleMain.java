/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package edu.vanier.template.pendulum;

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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 
 * @author salki
 */
public class CradleMain extends Application {

    private boolean initialStart = false;
    Canvas canvas;
    private boolean showTrail = true;
    private int status = 0; 
    private int testValue = 0;
    private int direction = 0;
    private boolean centerPendulum;
    /**
     * status:
     * 0 = outermost bobs
     * 1 = innermost bobs
     * 2 = center bob
     */

    /**
     * 
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage) {
        Label lengthLabel = new Label("Length:");
        lengthLabel.setLayoutX(10);
        lengthLabel.setLayoutY(330);

        primaryStage.setTitle("Newton's Cradle Simulation");
        canvas = new Canvas(800, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        StackPane root = new StackPane();

        Transition transition;
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        Group group = new Group();
        group.getChildren().addAll(canvas);
        

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
        
        //Distance between circles = 31 <===================
        
        
        // Current pendulum in the center
        pendulums[0] = new Pendulum(gc, new Vector2D(canvas.getWidth() / 2, centerY), (float) armLength, showTrail);
        // Additional pendulums on the left side
        for (int i = 1; i <= 2; i++) {
            double x = canvas.getWidth() / 2 - (armLength + spacing) * i;
            pendulums[i] = new Pendulum(gc, new Vector2D(x, centerY), (float) armLength, showTrail);
            
        }

        // Additional pendulums on the right side
        for (int i = 3; i <= 4; i++) {
            double x = canvas.getWidth() / 2 + (armLength + spacing) * (i - 2);
            pendulums[i] = new Pendulum(gc, new Vector2D(x, centerY), (float) armLength, showTrail);
        }


        group.setOnMousePressed(e -> {
            if(circles[0].isPressed()){
                centerPendulum = true;
            } else{
                centerPendulum = false;
            }
            resetAllPendulums(pendulums); // Reset all pendulums when one is clicked, originally at the end
            for (Pendulum p : pendulums) {
               
                p.clicked((int) e.getX(), (int) e.getY());
                if(p.equals(pendulums[1]) && circles[0].isPressed()){   //nice
                     p.clicked((int) ((e.getX())-31), (int) e.getY()); 
                     pendulums[2].clicked((int) ((e.getX()-62)), (int) e.getY()); 
                }
                
                if(p.equals(pendulums[3]) && circles[0].isPressed()){   //nice
                     p.clicked((int) ((e.getX())+31), (int) e.getY());          //problem is here
                     pendulums[4].clicked((int) ((e.getX()+62)), (int) e.getY());
                     System.out.println("edu.vanier.template.pendulum.CradleMain.start()");
                }
                
                if(p.equals(pendulums[2])){
                     p.clicked((int) ((e.getX())-31), (int) e.getY());  
                } 
                
                if(p.equals(pendulums[4])){
                    p.clicked((int) ((e.getX())+31), (int) e.getY());
                }
                
                if(p.equals(pendulums[2]) && circles[0].isPressed() && circles[0].getCenterX() < 400){
                     p.clicked((int) ((e.getX())-31), (int) e.getY());  
                } 
                
                if(p.equals(pendulums[4]) && circles[0].isPressed() && circles[0].getCenterX() > 400){
                    p.clicked((int) ((e.getX())+31), (int) e.getY());
                }
                
            System.out.println(circles[0].getCenterX() + "lol");
            }
        });

        group.setOnMouseDragged(e -> {
            for (Pendulum p : pendulums) {
                p.dragged((int) e.getX(), (int) e.getY());
                if(p.equals(pendulums[1]) && circles[0].isPressed() && circles[0].getCenterX() < 400){   //nice
                    pendulums[3].stopDragging();
                    pendulums[4].stopDragging();
                    //pendulums[3].dragged((int) 0, (int) 0);
                    /*
                    if(testValue == 0){
                        p.clicked((int) ((e.getX())-31), (int) e.getY()); 
                     pendulums[2].clicked((int) ((e.getX()-62)), (int) e.getY());
                     //pendulums[4].clicked((int) ((e.getX())), (int) e.getY());
                     //pendulums[3].clicked((int) ((e.getX())), (int) e.getY());
                     System.out.println("edu.vanier.template.pendulum.CradleMain.start()");
                        setTestValue(1);
                    }*/
                     p.dragged((int) ((e.getX()-31)), (int) e.getY());  
                     pendulums[2].dragged((int) ((e.getX()-62)), (int) e.getY());
                     System.out.println(circles[0].getCenterX());
                } 
                
                if(p.equals(pendulums[3]) && circles[0].isPressed() && circles[0].getCenterX() > 400){   //nice
                    pendulums[1].stopDragging();
                    pendulums[2].stopDragging();
                    /*
                    if(testValue == 0){
                        p.clicked((int) ((e.getX())+31), (int) e.getY()); 
                     pendulums[4].clicked((int) ((e.getX()+62)), (int) e.getY());
                     //pendulums[2].clicked((int) ((e.getX())), (int) e.getY());
                     //pendulums[1].clicked((int) ((e.getX())), (int) e.getY());
                     System.out.println("edu.vanier.template.pendulum.CradleMain.start()");
                        setTestValue(1);
                    }*/
                    
                     p.dragged((int) ((e.getX()+31)), (int) e.getY());  
                     pendulums[4].dragged((int) ((e.getX()+62)), (int) e.getY());
                     
                     
                }
                
                
                if(circles[0].getCenterX() == 400 && circles[0].isPressed() && testValue == 1){
                    resetAllPendulums(pendulums);
                    setTestValue(0);
                    p.clicked((int) e.getX(), (int) e.getY());
                    p.dragged((int) e.getX(), (int) e.getY());
                    //System.out.println("lo9l");
                    //pendulums[1].resetAll();
                    //pendulums[2].resetAll();

                }
                
                if(p.equals(pendulums[2]) && !circles[0].isPressed()){
                     p.dragged((int) ((e.getX())-31), (int) e.getY());  
                } 
                
                if(p.equals(pendulums[2]) && circles[0].isPressed() && circles[0].getCenterX() < 400){
                     p.dragged((int) ((e.getX())-62), (int) e.getY());  
                     //pendulums[3].resetAll();
                } 
                
                if(p.equals(pendulums[4]) && !circles[0].isPressed()){
                    p.dragged((int) ((e.getX())+31), (int) e.getY());
                }
                
                if(p.equals(pendulums[4]) && circles[0].isPressed() && circles[0].getCenterX() > 400){
                    p.dragged((int) ((e.getX())+62), (int) e.getY());
                }

                     
                     //System.out.println(circles[3].getCenterY());
            }
        });


        group.setOnMouseReleased(e -> {
            for (Pendulum p : pendulums) {
                p.stopDragging();
            }
        });

        /*
        Dummy transition used as a delay for the collision timer
        to avoid it activating during the first millisecond, where
        all the circles are initially placed together.
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

        /*
                AnimationTimer used to detect collision and update 
                the canvas
         */
        double mass1 = 1;
        double mass2 = 1;
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                // Update pendulum positions and circles
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
                
                // Check for collisions between adjacent bobs
                /*
        if (circles[2].getBoundsInParent().intersects(circles[1].getBoundsInParent()) && initialStart) {
            // Reverse velocities of adjacent bobs (1 and 2)
            double temp = pendulums[2].theta_vel;
            pendulums[2].theta_vel = pendulums[1].theta_vel;
            pendulums[1].theta_vel = (float) temp;
            //System.out.println("test");
        }
                 */
                
                /**
                 * Innermost bobs
                 */
                /*
                if (circles[1].getBoundsInParent().intersects(circles[0].getBoundsInParent()) && initialStart) {
                    // Reverse velocities of adjacent bobs (1 and 2)
                    double temp = pendulums[1].theta_vel;
                    pendulums[1].theta_vel = pendulums[3].theta_vel;
                    pendulums[2].theta_vel = pendulums[3].theta_vel;    /////////////
                    pendulums[3].theta_vel = (float) temp;
                    pendulums[4].theta_vel = (float) temp;              //||||||
                    pendulums[1].resetAll();
                    pendulums[2].resetAll();                            ///////
                    //System.out.println("test");
                }
                */
                //=======================================
                /**
                 * Innermost bobs
                 */
                
                if (circles[1].getBoundsInParent().intersects(circles[0].getBoundsInParent()) && initialStart) {
                    testValue = 1;
                    // Reverse velocities of adjacent bobs (1 and 2)
                    double temp = pendulums[1].theta_vel;
                    pendulums[1].theta_vel = pendulums[3].theta_vel;
                    pendulums[2].theta_vel = pendulums[3].theta_vel;    /////////////
                    pendulums[3].theta_vel = (float) temp;
                    pendulums[4].theta_vel = (float) temp;              //||||||
                    //pendulums[0].theta_vel = (float) temp;
                    pendulums[0].resetAll();
                    pendulums[1].resetAll();
                    pendulums[2].resetAll();                            ///////
                    //System.out.println("test");
                    
                    pendulums[0].theta_vel = pendulums[3].theta_vel;
                    
                }
                
                //=========================================
                /**
                 * outermost
                 */
                if (circles[2].getBoundsInParent().intersects(circles[1].getBoundsInParent()) && initialStart) {
                    // Reverse velocities of adjacent bobs (1 and 2)
                    double temp = pendulums[2].theta_vel;
                    pendulums[2].theta_vel = pendulums[4].theta_vel;
                    pendulums[4].theta_vel = (float) temp;
                    pendulums[0].theta_vel = (float) temp;
                    pendulums[2].resetAll();
                    //System.out.println("test");
                }
                
                //=====================================================
               
//        if (circles[1].getBoundsInParent().intersects(circles[0].getBoundsInParent()) && initialStart) {
//            // Reverse velocities of adjacent bobs (1 and 0)
//            double temp = pendulums[1].theta_vel;
//            pendulums[1].theta_vel = pendulums[0].theta_vel;
//            pendulums[0].theta_vel = (float) temp;
//            System.out.println("test");
//            
//        }
//        
//        
//        if (circles[0].getBoundsInParent().intersects(circles[3].getBoundsInParent()) && initialStart) {
//            // Reverse velocities of adjacent bobs (1 and 2)
//            double temp = pendulums[0].theta_vel;
//            pendulums[0].theta_vel = pendulums[3].theta_vel;
//            pendulums[3].theta_vel = (float) temp;
//            //System.out.println("test");
//        }
//
//       
                //==========================================
                //if(centerPendulum == true){
                    
                
                if (circles[3].getBoundsInParent().intersects(circles[0].getBoundsInParent()) && initialStart) {
                    testValue = 0;
                    // Reverse velocities of adjacent bobs (2 and 0)
                    double temp = pendulums[3].theta_vel;
                    pendulums[3].theta_vel = pendulums[0].theta_vel;
                    pendulums[4].theta_vel = pendulums[0].theta_vel;            //|||||
                    pendulums[1].theta_vel = (float) temp;
                    pendulums[2].theta_vel = (float) temp;                              //////////////////////////////////////////
                    
                    
                    //pendulums[0].theta_vel = (float) temp;
                    
                    System.out.println("x= " + circles[1].getCenterX() + " y= " + circles[3].getCenterX());
                    pendulums[0].resetAll();
                    pendulums[3].resetAll();
                    pendulums[4].resetAll();            //|||||||||||||||||||
                    
                    pendulums[0].theta_vel = pendulums[1].theta_vel;
                }
//}
                //System.out.println(circles[1].getCenterX());
                /*
                if (circles[3].getBoundsInParent().intersects(circles[0].getBoundsInParent()) && initialStart) {
                    testValue = 0;
                    // Reverse velocities of adjacent bobs (2 and 0)
                    double temp = pendulums[3].theta_vel;
                    pendulums[3].theta_vel = pendulums[0].theta_vel;
                    pendulums[4].theta_vel = pendulums[0].theta_vel;            //|||||
                    pendulums[1].theta_vel = (float) temp;
                    pendulums[2].theta_vel = (float) temp;                              //////////////////////////////////////////
                    pendulums[0].theta_vel = (float) temp;
                    System.out.println("x= " + circles[1].getCenterX() + " y= " + circles[3].getCenterX());
                    //pendulums[0].resetAll();
                    pendulums[3].resetAll();
                    pendulums[4].resetAll();            //|||||||||||||||||||
                }
                */
                //=========================================================
                
                if (circles[4].getBoundsInParent().intersects(circles[3].getBoundsInParent()) && initialStart) {
                    // Reverse velocities of adjacent bobs (2 and 0)
                    double temp = pendulums[4].theta_vel;
                    pendulums[4].theta_vel = pendulums[3].theta_vel;
                    pendulums[2].theta_vel = (float) temp;
                    System.out.println("x= " + circles[2].getCenterX() + " y= " + circles[4].getCenterX());
                    pendulums[4].resetAll();
                }
                
                //real order: 21034
                //===========================================================
                /*
        if (circles[0].getBoundsInParent().intersects(circles[2].getBoundsInParent()) && initialStart) {
            // Reverse velocities of adjacent bobs (2 and 0)
            double temp = pendulums[2].theta_vel;
            pendulums[0].theta_vel = pendulums[2].theta_vel;
            pendulums[2].theta_vel = (float) temp;
        }
        
        if (circles[0].getBoundsInParent().intersects(circles[3].getBoundsInParent()) && initialStart) {
            // Reverse velocities of adjacent bobs (1 and 0)
            double temp = pendulums[0].theta_vel;
            pendulums[0].theta_vel = pendulums[3].theta_vel;
            pendulums[3].theta_vel = (float) temp;
            
        }
        
        if (circles[3].getBoundsInParent().intersects(circles[4].getBoundsInParent()) && initialStart) {
            // Reverse velocities of adjacent bobs (3 and 4)
            double temp = pendulums[3].theta_vel;
            pendulums[3].theta_vel = pendulums[4].theta_vel;
            pendulums[4].theta_vel = (float) temp;
        }
                 */
 /*
        System.out.println("circles(0)" + "x= " + circles[0].getCenterX() + " y= " + circles[0].getCenterY());
        System.out.println("circles{1}" + "x= " + circles[1].getCenterX() + " y= " + circles[1].getCenterY());
        System.out.println("circles{2}" + "x= " + circles[2].getCenterX() + " y= " + circles[2].getCenterY());
        System.out.println("circles{3}" + "x= " + circles[3].getCenterX() + " y= " + circles[3].getCenterY());
        System.out.println("circles{4}" + "x= " + circles[4].getCenterX() + " y= " + circles[4].getCenterY());

        
        System.out.println("pendulums(0)" + "PENDx= " + circles[0].getCenterX() + " y= " + circles[0].getCenterY());
        System.out.println("pendulums{1}" + "PENDx= " + circles[1].getCenterX() + " y= " + circles[1].getCenterY());
        System.out.println("pendulums{2}" + "PENDx= " + circles[2].getCenterX() + " y= " + circles[2].getCenterY());
        System.out.println("pendulums{3}" + "PENDx= " + circles[3].getCenterX() + " y= " + circles[3].getCenterY());
        System.out.println("pendulums{4}" + "PENDx= " + circles[4].getCenterX() + " y= " + circles[4].getCenterY());
                 */
                //real order: 21034
            }
        }.start();

        // Create a Slider component for adjusting pendulum length
        Slider lengthSlider = new Slider(75, 150, 100); // Minimum, Maximum, Default Value
        lengthSlider.setShowTickLabels(true);
        lengthSlider.setShowTickMarks(true);
        lengthSlider.setMajorTickUnit(25);
        lengthSlider.setMinorTickCount(5);
        lengthSlider.setBlockIncrement(25);
        lengthSlider.setLayoutX(50);
        lengthSlider.setLayoutY(350);

        // Add an event listener to update pendulum lengths when the slider value changes
        lengthSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double newLength = newValue.doubleValue();
            updatePendulumLengths(pendulums, newLength);
            resetAllPendulums(pendulums);
        });
        CheckBox showTrailCheckbox = new CheckBox("Show Trail");
        showTrailCheckbox.setSelected(true); // Default to showing trail
        showTrailCheckbox.setLayoutX(700);
        showTrailCheckbox.setLayoutY(350);

        // Add an event listener to toggle showing/hiding the trail
        showTrailCheckbox.setOnAction(event -> {
            boolean showTrail = showTrailCheckbox.isSelected();
            for (Pendulum pendulum : pendulums) {
                pendulum.setShowTrail(showTrail);
            }
        });

        // Add the Slider to the root layout
        group.getChildren().addAll(lengthSlider, lengthLabel, showTrailCheckbox);
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
     * @param showTrail Boolean indicated whether the trail is active or not
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

    
}

//new AnimationTimer() {
//    @Override
//    public void handle(long now) {
//        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//
//        // Update pendulum positions and circles
//        for (int i = 0; i < pendulums.length; i++) {
//            if (i == 0) {
//                pendulums[0].go();
//                circles[0].setCenterX(pendulums[0].loc.getX());
//                circles[0].setCenterY(pendulums[0].loc.getY());
//            } else {
//                pendulums[i].go();
//                circles[i].setCenterX(pendulums[i].loc.getX());
//                circles[i].setCenterY(pendulums[i].loc.getY());
//            }
//        }
//
//        // Check for collisions between adjacent bobs
//        for (int i = 0; i < pendulums.length - 1; i++) {
//            if (circles[i].getBoundsInParent().intersects(circles[i + 1].getBoundsInParent()) && initialStart) {
//                double x1 = circles[i].getCenterX();
//                double y1 = circles[i].getCenterY();
//                double r1 = circles[i].getRadius();
//                double x2 = circles[i + 1].getCenterX();
//                double y2 = circles[i + 1].getCenterY();
//                double r2 = circles[i + 1].getRadius();
//
//                // Check for collision using Vector2D method
//                if (new Vector2D(x1, y1).checkCollision(x1, y1, r1, x2, y2, r2)) {
//                    // Reverse velocities of adjacent bobs
//                    double temp = pendulums[i + 1].theta_vel;
//                    pendulums[i + 1].theta_vel = pendulums[i].theta_vel;
//                    pendulums[i].theta_vel = (float) temp;
//                }
//            }
//        }
//    }
//}.start();

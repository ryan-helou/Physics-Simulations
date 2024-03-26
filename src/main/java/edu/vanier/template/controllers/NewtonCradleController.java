/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author anishmehra
 */
public class NewtonCradleController implements Initializable {

    @FXML
    private Pane root;
    @FXML
    private Pane cradlePane;
    @FXML
    private Slider numOfPendulums;
    @FXML
    private Slider numOfSwings;
    @FXML
    private Label mass;
    @FXML
    private Slider massSlider;
    @FXML
    private Label length;
    @FXML
    private Slider lengthSlider;
    @FXML
    private Label gravity;
    @FXML
    private Slider gravitySlider;
    @FXML
    private CheckBox pause;
    @FXML
    private Button back;
    @FXML
    private Button playButton;
    private Arc arc1, arc2, arc3, arc4, arc5;
    private Pendulum pendulum1, pendulum2, pendulum3, pendulum4, pendulum5;
    private Circle holder1,holder2,holder3,holder4,holder5; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        double w = 491;
        double h = 374;
        //double holderY = (h*0.8 - (h/2)) ;
        
         arc1 = new Arc(w / 2 - 100, h * 0.8, w * 0.15, w * 0.15, 90, 90);
        Circle bob1 = new Circle(arc1.getCenterX() - arc1.getRadiusX(), arc1.getCenterY(), 26);
         holder1 = new Circle(arc1.getCenterX(),arc1.getCenterY() - h / 2 , 5); 
        arc1 = new Arc(holder1.getCenterX(), holder1.getCenterY()-50  , (w / 2)-50, (h / 2), 240, 60);
        Line line1 = new Line(holder1.getCenterX(), holder1.getCenterY(), bob1.getCenterX(), bob1.getCenterX()); 
         pendulum1 = new Pendulum(holder1, bob1, line1, arc1);  
        // System.out.println(arc1.getCenterY());
         
         
         arc2 = new Arc(w / 2 - 50, h * 0.8, w * 0.15, w * 0.15, 90, 90);
        Circle bob2 = new Circle(arc2.getCenterX() - arc2.getRadiusX(), arc2.getCenterY(), 26);
         holder2 = new Circle(arc2.getCenterX(), arc2.getCenterY() - h / 2, 5);
        arc2 = new Arc(holder2.getCenterX(), holder2.getCenterY()-50, (w / 2)-50, (h / 2), 240, 60);
        Line line2 = new Line(holder2.getCenterX(), holder2.getCenterY(), bob2.getCenterX(), bob2.getCenterY());
         pendulum2 = new Pendulum(holder2, bob2, line2, arc2);

         arc3 = new Arc(w / 2, h * 0.8, w * 0.15, w * 0.15, 90, 90);
        Circle bob3 = new Circle(arc3.getCenterX() - arc3.getRadiusX(), arc3.getCenterY(), 26);
         holder3 = new Circle(arc3.getCenterX(), arc3.getCenterY() - h / 2,5);
        arc3 = new Arc(holder3.getCenterX(), holder3.getCenterY()-50, (w / 2)-50, (h / 2), 240, 60);
        Line line3 = new Line(holder3.getCenterX(), holder3.getCenterY(), bob3.getCenterX(), bob3.getCenterY());
         pendulum3 = new Pendulum(holder3, bob3, line3, arc3);

         arc4 = new Arc(w / 2 + 50, h * 0.8, w * 0.15, w * 0.15, 90, 90);
        Circle bob4 = new Circle(arc4.getCenterX() - arc4.getRadiusX(), arc4.getCenterY(), 26);
         holder4 = new Circle(arc4.getCenterX(), arc4.getCenterY() - h / 2, 5);
        arc4 = new Arc(holder4.getCenterX(), holder4.getCenterY()-50, (w / 2)-50, (h / 2), 240, 60);
        Line line4 = new Line(holder4.getCenterX(), holder4.getCenterY(), bob4.getCenterX(), bob4.getCenterY());
         pendulum4 = new Pendulum(holder4, bob4, line4, arc4);

         arc5 = new Arc(w / 2 + 100, h * 0.8, w * 0.15, w * 0.15, 90, 90);
        Circle bob5 = new Circle(arc5.getCenterX() - arc5.getRadiusX(), arc5.getCenterY(), 26);
         holder5 = new Circle(arc5.getCenterX(), arc5.getCenterY() - h / 2, 5);
        arc5 = new Arc(holder5.getCenterX(), holder5.getCenterY()-50, (w / 2)-50, (h / 2), 240, 60); 
        Line line5 = new Line(holder5.getCenterX(), holder5.getCenterY(), bob5.getCenterX(), bob5.getCenterY()-300);
         pendulum5 = new Pendulum(holder5, bob5, line5, arc5);
        cradlePane.getChildren().addAll(holder1,holder2,holder3,holder4,holder5, line1,line2,line3,line4,line5, bob1,bob2,bob3,bob4,bob5);
        
        back.setOnAction(e -> { 
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
        });
        playButton.setOnAction(e -> {
         pendulum1.getPath().play();
        });
       lengthSlider.valueProperty().addListener(new ChangeListener<Number>() {
   
           @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        
        
        pendulum1.getPath().stop();
        double newArcY1 = (holder1.getCenterY()-50) + newValue.doubleValue();
        double newArcWidth1 = (w / 2) + newValue.doubleValue();
        arc1.setCenterY(newArcY1);
        arc1.setRadiusX(newArcWidth1);
        pendulum1.getPath().play();
        
        pendulum2.getPath().stop();
        double newArcY2 = (holder2.getCenterY()-50) + newValue.doubleValue();
        double newArcWidth2 = (w / 2) + newValue.doubleValue();
        arc2.setCenterY(newArcY2);
        arc2.setRadiusX(newArcWidth2);
        pendulum2.getPath().play();
        
        pendulum3.getPath().stop();
        double newArcY3 = (holder3.getCenterY()-50) + newValue.doubleValue();
        double newArcWidth3 = (w / 2) + newValue.doubleValue();
        arc3.setCenterY(newArcY3);
        arc3.setRadiusX(newArcWidth3);
        pendulum3.getPath().play();
        
        pendulum4.getPath().stop();
        double newArcY4 = (holder4.getCenterY()-50) + newValue.doubleValue();
        double newArcWidth4 = (w / 2) + newValue.doubleValue();
        arc4.setCenterY(newArcY4);
        arc4.setRadiusX(newArcWidth4);
        pendulum4.getPath().play();
        
        pendulum5.getPath().stop();
        double newArcY5 = (holder5.getCenterY()-50) + newValue.doubleValue();
        double newArcWidth5 = (w / 2) + newValue.doubleValue();
        arc5.setCenterY(newArcY5);
        arc5.setRadiusX(newArcWidth5);
        pendulum5.getPath().play();

        
    }
});
        numOfPendulums.valueProperty().addListener(new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
           
        }
    });
        numOfSwings.valueProperty().addListener(new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
           
        }
    });
    massSlider.valueProperty().addListener(new ChangeListener<Number>() {
    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        double brightness = newValue.doubleValue() / massSlider.getMax(); 
        Color color = Color.hsb(40 + (60 * brightness), 0.5 + (0.5 * brightness), 0.9 + (0.1 * brightness)); 
        bob1.setFill(color);
        bob2.setFill(color);
        bob3.setFill(color);
        bob4.setFill(color);
        bob5.setFill(color);
    }
});
        gravitySlider.valueProperty().addListener(new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
           
        }
    });
//         pause.selectedProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue) {
//                if (pendulum1 != null) {
//                    pendulum1.getPath().pause();
//                }
//            } else {
//                if (pendulum1 != null) {
//                    pendulum1.getPath().play();
//                }
//            }
//        });
    }
    
        private void numberOfPendulums(){
            
        }
        private void numberOfSwings(){
            
        }
         private void massChange(){
            
        }
        private void updateStringLength() {
            
        }
        private void gravityChange(){
            
        }
        private void detectCollision() {
            
        }
      
}

package edu.vanier.template.controllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import edu.vanier.template.Mover;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import java.util.Random;
/**
 * FXML Controller class
 *
 * @author salki
 */
public class PAFXMLController implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private Text mainTitle;
    @FXML
    private Pane areaPane;
    @FXML
    private ImageView mute;
    @FXML
    private ComboBox<?> modeBox;
    @FXML
    private ImageView animatedGif;
    @FXML
    private Text gravityText;
    @FXML
    private TextField gravityValue;
    @FXML
    private Text sizeText;
    @FXML
    private Slider sizeValue;
    @FXML
    private HBox massOnAction;
    @FXML
    private TextField massValue;
    @FXML
    private Text amountText;
    @FXML
    private TextField amountValue;
    @FXML
    private CheckBox enableThemes;
    @FXML
    private CheckBox showTrail;
    @FXML
    private Canvas canvas;
    
    private Mover mover;
    private double mouseX;
    private double mouseY;
    List<Mover> movers = new ArrayList();
    Random random = new Random();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         this.mover = new Mover(random.nextDouble(240), random.nextDouble(240));

        canvas.setOnMouseMoved(e -> {
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

    @FXML
    private void backOnAction(ActionEvent event) {
    }

    @FXML
    private void modeBoxOnAction(ActionEvent event) {
    }

    @FXML
    private void gravityOnAction(ActionEvent event) {
    }

    @FXML
    private void sizeSliderValue(MouseEvent event) {
    }

    @FXML
    private void amountOnAction(ActionEvent event) {
    movers.clear();
    int particleAmount = Integer.parseInt(amountValue.getText());
    
    for (int i = 0; i < particleAmount; i++) {
        this.mover = new Mover(random.nextDouble(600), random.nextDouble(300));
        movers.add(mover);
    }
    
    /*
        for (Mover mover1 : movers) {
            System.out.println("11");
        }
*/
    }

    @FXML
    private void themeValue(MouseEvent event) {
    }

    @FXML
    private void themeOnAction(ActionEvent event) {
    }

    @FXML
    private void trailOnAction(ActionEvent event) {
    }
    
}

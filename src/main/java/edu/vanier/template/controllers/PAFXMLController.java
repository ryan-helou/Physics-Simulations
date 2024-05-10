package edu.vanier.template.controllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
import edu.vanier.template.Mover;
import java.io.IOException;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class for particle attraction.
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
    private Text massText;
    @FXML
    private TextField massValue;
    @FXML
    private Text amountText;
    @FXML
    private TextField amountValue;

    @FXML
    private CheckBox showTrail;
    @FXML
    private Canvas canvas;

    private int particleAmount = 1;
    private double gravityNumber = 1;
    private double massNumber = 1;
    private double newSize = 1;
    private double mouseX;
    private double mouseY;
    private Mover mover;
    private List<Mover> movers = new ArrayList();
    private Random random = new Random();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/ChewyBubble.otf"), 27);
        Font customFont2 = Font.loadFont(getClass().getResourceAsStream("/ChewyBubble.otf"), 15);

        backButton.setFont(customFont2);
        backButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");

        mainTitle.setFont(customFont);
        mainTitle.setFill(Color.WHITE);

        gravityText.setFont(customFont2);
        gravityText.setFill(Color.WHITE);

        sizeText.setFont(customFont2);
        sizeText.setFill(Color.WHITE);

        amountText.setFont(customFont2);
        amountText.setFill(Color.WHITE);

        showTrail.setFont(customFont2);
        showTrail.setTextFill(Color.WHITE);

        massText.setFont(customFont2);
        massText.setFill(Color.WHITE);

        gravityValue.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        massValue.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        amountValue.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        sizeValue.setStyle(
                "-fx-control-inner-background: black; "
                + "-fx-color: black; "
                + "-fx-thumb-color: black; "
                + "-fx-track-color: black;"
        );

        ImageView backgroundImg = new ImageView(new Image(getClass().getResourceAsStream("/images/spacemainmenu.gif")));
        backgroundImg.setFitWidth(755); // Set this to match the width of your root AnchorPane
        backgroundImg.setFitHeight(400);

        // Retrieve the root AnchorPane from one of the existing components
        AnchorPane rootPane = (AnchorPane) backButton.getParent().getParent();
        rootPane.getChildren().add(0, backgroundImg);
        movers.clear();
        //Create the particles based on the amount inserted
        for (int i = 0; i < particleAmount; i++) {
            this.mover = new Mover(random.nextDouble(240), random.nextDouble(240), 11);
            movers.add(mover);
        }

        canvas.setOnMouseMoved(e -> {
            mouseX = e.getX();
            mouseY = e.getY();
            redraw(canvas);
        });

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (Mover mover : movers) {
                    mover.update(new javafx.geometry.Point2D(mouseX, mouseY));
                    redraw(canvas);
                }
                //redraw(canvas);
            }
        };
        animationTimer.start();
    }

    /**
     * Responsible for updating the
     *
     * @param canvas
     */
    private void redraw(Canvas canvas) {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Mover mover : movers) {
            mover.display(canvas.getGraphicsContext2D());
        }
    }

    /**
     * Error dialog. Occurs upon invalid entry of a gravity value.
     *
     * @param message
     */
    private void showErrorDialog(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    @FXML
    private void backOnAction(ActionEvent event) {
        movers.clear();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainAnish1.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(PAFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gravityOnAction(ActionEvent event) {
        setAllGravity();
    }

    @FXML
    private void sizeSliderValue(MouseEvent event) {
        newSize = sizeValue.getValue();
        for (Mover mover : movers) {
            mover.setScaleFactor(newSize);
        }
    }

    /**
     *
     * @param event
     */
    @FXML
    private void amountOnAction(ActionEvent event) {
        movers.clear();

        try {
            particleAmount = Integer.parseInt(amountValue.getText());
        } catch (NumberFormatException e) {
            showErrorDialog("Please enter a valid integer.");
        }

        if (particleAmount > 100 || particleAmount <= 0) {
            showErrorDialog("Please enter a valid number (between 1 and 100)");
        } else {

            for (int i = 0; i < particleAmount; i++) {
                mover = new Mover(random.nextDouble(600), random.nextDouble(300), 11);
                movers.add(mover);
                mover.setScaleFactor(newSize);
            }

        }

        for (Mover mover : movers) {
            setAllGravity();
            setAllMass();
        }
    }

    @FXML
    private void trailOnAction(ActionEvent event) {
        if (!showTrail.isSelected()) {
            for (Mover mover : movers) {
                mover.setTrailStatus(false);
            }
        } else {
            for (Mover mover : movers) {
                mover.setTrailStatus(true);
            }
        }
    }

    /**
     * Method that changes the gravity value for each particle present within
     * the simulation based on the value set by the user through the gravity
     * slider.
     */
    private void setAllGravity() {
        try {
            gravityNumber = Double.parseDouble(gravityValue.getText());
        } catch (NumberFormatException e) {
            showErrorDialog("Please enter a valid gravity number.");
        }

        if (gravityNumber > 1000 || gravityNumber <= 0) {
            showErrorDialog("Please enter a valid number (between 1 and 1000)");
        } else {
            for (Mover mover : movers) {
                mover.setForceMagnitude(gravityNumber);
            }
        }
    }
    
    @FXML
    private void massOnAction(ActionEvent event) {
        setAllMass();
    }
    
    /**
     * Sets the mass of every particle present within the particle simulation.
     * Also contains an error dialog in the case that the mass inputted is not
     * valid.
     */
    private void setAllMass() {
        try {
            massNumber = Double.parseDouble(massValue.getText());
        } catch (NumberFormatException e) {
            showErrorDialog("Please enter a valid mass number.");
        }

        if (massNumber > 1000 || massNumber <= 0) {
            showErrorDialog("Please enter a valid number (between 1 and 1000)");
        } else {
            for (Mover mover : movers) {
                mover.setMass(massNumber);
            }
        }
    }

    public int getParticleAmount() {
        return particleAmount;
    }

    public void setParticleAmount(int particleAmount) {
        this.particleAmount = particleAmount;
    }

    public double getNewSize() {
        return newSize;
    }

    public void setNewSize(double newSize) {
        this.newSize = newSize;
    }

    public double getMouseX() {
        return mouseX;
    }

    public void setMouseX(double mouseX) {
        this.mouseX = mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }

    public void setMouseY(double mouseY) {
        this.mouseY = mouseY;
    }

    public Mover getMover() {
        return mover;
    }

    public void setMover(Mover mover) {
        this.mover = mover;
    }

}

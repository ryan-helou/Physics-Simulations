/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

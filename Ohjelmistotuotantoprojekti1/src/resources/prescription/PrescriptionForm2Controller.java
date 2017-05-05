/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.prescription;

import gui.Controller_IF;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author joosiika
 */
public class PrescriptionForm2Controller extends Tab implements Initializable {

    @FXML
    private GridPane gridPane;
    @FXML
    private Label patientLabel;
    @FXML
    private Label diagnoseLabel;
    @FXML
    private Label drugLabel;
    @FXML
    private Label doseLabel;
    @FXML
    private Label timesADayLabel;
    @FXML
    private Label startDateLabel;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private Label endDateLabel;
    @FXML
    private TextField patientField;
    @FXML
    private TextField drugField;
    @FXML
    private Text drugDoseField;
    @FXML
    private TextField doseField;
    @FXML
    private TextField timesADayField;
    @FXML
    private ChoiceBox<?> diagnoseSelector;
    @FXML
    private Label infoLabel;
    @FXML
    private TextArea infoField;
    @FXML
    private ButtonBar buttonBar;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;
    @FXML
    private Label creationDateLabel;
    @FXML
    private Label mainTitle;
    @FXML
    private Label doctorNameLabel;
    @FXML
    private Label prescriptionIdLabel;
    
    private final Controller_IF controller;
    private final Prescription prescription;

    public PrescriptionForm2Controller(Controller_IF controller, Prescription prescription) {
        this.controller = controller;
        this.prescription = prescription;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void setStartDate(ActionEvent event) {
        
    }

    @FXML
    private void setEndDate(ActionEvent event) {
    }

    @FXML
    private void setDose(KeyEvent event) {
    }

    @FXML
    private void setTimesADay(KeyEvent event) {
    }

    @FXML
    private void cancel(ActionEvent event) {
    }

    @FXML
    private void save(ActionEvent event) {
    }
    
}

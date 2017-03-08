package gui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * FXML Controller class
 *
 * @author joosiika
 */
public class PrescriptionFormGUI extends Tab {

    @FXML
    private GridPane gridPane;
    @FXML
    private Label patientLabel;
    @FXML
    private TextField patientField;
    @FXML
    private Label diagnoseLabel;
    @FXML
    private ChoiceBox diagnoseSelector;
    @FXML
    private Label drugLabel;
    @FXML
    private TextField drugField;
    @FXML
    private Label doseLabel;
    @FXML
    private TextField doseField;
    @FXML
    private Label timesADayLabel;
    @FXML
    private TextField timesADayField;
    @FXML
    private Label infoLabel;
    @FXML
    private TextArea infoField;
    @FXML
    private Label startDateLabel;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private Label endDateLabel;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private ButtonBar buttonBar;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;
    @FXML
    private Label mainTitle;
    @FXML
    private Label prescriptionIdLabel;
    @FXML
    private Label doctorNameLabel;
    @FXML
    private Label creationDateLabel;
    
    private final String title;
    private FXMLLoader loader;
    
    public PrescriptionFormGUI(String title) {
        this.title = title;
        try {
            loader = new FXMLLoader(getClass().getResource("PrescriptionForm.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
            this.setText(title);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public Label getPatientLabel() {
        return patientLabel;
    }

    public TextField getPatientField() {
        return patientField;
    }

    public Label getDiagnoseLabel() {
        return diagnoseLabel;
    }

    public ChoiceBox getDiagnoseSelector() {
        return diagnoseSelector;
    }

    public Label getDrugLabel() {
        return drugLabel;
    }

    public TextField getDrugField() {
        return drugField;
    }

    public Label getDoseLabel() {
        return doseLabel;
    }

    public TextField getDoseField() {
        return doseField;
    }

    public Label getTimesADayLabel() {
        return timesADayLabel;
    }

    public TextField getTimesADayField() {
        return timesADayField;
    }

    public Label getInfoLabel() {
        return infoLabel;
    }

    public TextArea getInfoField() {
        return infoField;
    }

    public Label getStartDateLabel() {
        return startDateLabel;
    }

    public DatePicker getStartDatePicker() {
        return startDatePicker;
    }

    public Label getEndDateLabel() {
        return endDateLabel;
    }

    public DatePicker getEndDatePicker() {
        return endDatePicker;
    }

    public ButtonBar getButtonBar() {
        return buttonBar;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Label getMainTitle() {
        return mainTitle;
    }

    public Label getPrescriptionIdLabel() {
        return prescriptionIdLabel;
    }

    public Label getDoctorNameLabel() {
        return doctorNameLabel;
    }

    public Label getCreationDateLabel() {
        return creationDateLabel;
    }
}

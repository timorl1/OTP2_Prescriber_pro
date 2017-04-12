package resources.prescription;

import gui.Localisation;
import static gui.Localisation.getInstance;
import prescriptionMaker.PrescriptionMakerController;
import prescriptionMaker.PrescriptionMakerController_IF;
import resources.SideBarListView_IF;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import resources.diagnose.Diagnose;
import resources.drug.Drug;
import resources.patient.Patient;
import resources.prescription.Prescription;
import resources.user.User_IF;

/**
 * FXML Controller class
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class PrescriptionFormGUI extends Tab implements PrescriptionFormGUI_IF {

    ResourceBundle text;
    Localisation local = getInstance();
    
    @FXML
    private GridPane gridPane;
    @FXML
    private Label patientLabel;
    @FXML
    private Text patientField;
    @FXML
    private Label diagnoseLabel;
    @FXML
    private ChoiceBox<Diagnose> diagnoseSelector;
    @FXML
    private Label drugLabel;
    @FXML
    private Text drugField;
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
    
    private PrescriptionMakerController_IF controller;
    private ObservableList<Diagnose> diagnoses;
    private final SideBarListView_IF<Patient> patientSelector;
    private final SideBarListView_IF<Drug> drugSelector;
    private Prescription prescription;
    private final String title;
    private FXMLLoader loader;
    
    private int id;
    private Patient patient;
    private User_IF doctor;
    private Drug drug;
    private Diagnose diagnose;
    private double dose;
    private int timesADay;
    private Date startDate;
    private Date endDate;
    private Date creationDate;
    private String info;
    
    public PrescriptionFormGUI(SideBarListView_IF<Patient> patientSelector, SideBarListView_IF<Drug> drugSelector, String title, Prescription prescription) {
        text = local.language();
        this.patientSelector = patientSelector;
        this.drugSelector = drugSelector;
        this.title = title;
        this.prescription = prescription;
        this.controller = new PrescriptionMakerController(this);
        this.id = this.prescription.getId();
        this.patient = this.prescription.getPatient();
        this.doctor = this.prescription.getDoctor();
        this.drug = this.prescription.getDrug();
        this.dose = this.prescription.getDose();
        this.timesADay = this.prescription.getTimesADay();
        this.startDate = this.prescription.getStartDate();
        this.endDate = this.prescription.getEndDate();
        this.creationDate = this.prescription.getCreationDate();
        this.info = this.prescription.getInfo();
        this.diagnoses = FXCollections.observableArrayList();
        this.diagnose = this.prescription.getDiagnose();
        this.diagnoses.add(this.diagnose);
        try {
            loader = new FXMLLoader(getClass().getResource("PrescriptionForm.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
            patientLabel.setText(text.getString("patient")+":");
            diagnoseLabel.setText(text.getString("diagnose")+":");
            drugLabel.setText(text.getString("drug")+":");
            doseLabel.setText(text.getString("singleDose")+":");
            timesADayLabel.setText(text.getString("timesADay")+":");
            startDateLabel.setText(text.getString("startDate")+":");
            endDateLabel.setText(text.getString("endtDate")+":");
            infoLabel.setText(text.getString("info")+":");
            cancelButton.setText(text.getString("cancel"));
            saveButton.setText(text.getString("save"));
            mainTitle.setText(text.getString("prescription"));
            doctorNameLabel.setText(text.getString("doctor"));
            prescriptionIdLabel.setText(text.getString("ID"));
            this.initializeFields();
            this.initializeBasicListeners();
            if (this.patient == null) {
                this.initializeNewPrescriptionListeners();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void initializeFields() {
        this.setText(title);
        this.creationDateLabel.setText(this.creationDate.toString());
        this.doctorNameLabel.setText(this.doctor.getFirstName() + " " + this.doctor.getLastName());
        if (this.id != 0) {
            this.prescriptionIdLabel.setText("#" + Integer.toString(this.id));
        }
        if (this.patient != null) {
            this.patientField.setText(this.patient.toString());
        }
        if (this.patient == null && this.patientSelector.getSelection() != null) {
            this.prescription.setPatient(this.patientSelector.getSelection());
            this.patientField.setText(this.patientSelector.getSelection().toString());
        }
        if (this.drug != null) {
            this.drugField.setText(this.drug.toString());
        }
        if (this.drug == null && this.drugSelector.getSelection() != null) {
            this.prescription.setDrug(this.drugSelector.getSelection());
            this.drugField.setText(this.drugSelector.getSelection().toString());
        }
        this.diagnoseSelector.setItems(diagnoses);
        this.diagnoseSelector.getSelectionModel().clearAndSelect(0);
        this.doseField.setText(Double.toString(this.dose));
        this.timesADayField.setText(Integer.toString(this.timesADay));
        this.infoField.setText(this.info);
        if (this.startDate != null) {
            this.startDatePicker.setValue(this.startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        if (this.endDate != null) {
            this.endDatePicker.setValue(this.endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
    }
    
    private void initializeBasicListeners() {
        this.drugSelector.getListView().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Drug>() {
            @Override
            public void changed(ObservableValue<? extends Drug> ov, Drug oldValue, Drug newValue) {
                if (newValue != null && !newValue.equals(oldValue)) {
                    drug = newValue;
                    prescription.setDrug(drug);
                    drugField.setText(drug.toString());
                    drugField.setFill(Color.BLACK);
                    if (prescription.getPatient() != null) {
                        dose = controller.getOptimalDose();
                        prescription.setDose(dose);
                        doseField.setText(Double.toString(dose));
                        drugField.setFill(Color.BLACK);
                        controller.checkDose();
                        controller.checkAllergens();
                    }
                }
            }
        });
        this.doseField.setOnKeyReleased(e -> {
            try {
                this.dose = Double.parseDouble(this.doseField.getText().replace(',', '.'));
            } catch (NumberFormatException ex) {
            }
            this.prescription.setDose(this.dose);
            this.controller.checkDose();
        });
        this.timesADayField.setOnKeyReleased(e -> {
            try {
                this.timesADay = Integer.parseInt(this.timesADayField.getText());
            } catch (NumberFormatException ex) {
            }
            this.prescription.setTimesADay(this.timesADay);
            this.controller.checkDose();
        });
        this.infoField.setOnKeyReleased(e -> {
            this.info = this.infoField.getText();
            this.prescription.setInfo(this.info);
        });
    }
    
    private void initializeNewPrescriptionListeners() {
        this.patientSelector.getListView().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Patient>() {
            @Override
            public void changed(ObservableValue<? extends Patient> ov, Patient oldValue, Patient newValue) {
                if (newValue != null && !newValue.equals(oldValue)) {
                    patient = newValue;
                    prescription.setPatient(patient);
                    patientField.setText(patient.toString());
                    drugField.setFill(Color.BLACK);
                    if (prescription.getDrug() != null) {
                        dose = controller.getOptimalDose();
                        doseField.setText(Double.toString(dose));
                        drugField.setFill(Color.BLACK);
                        prescription.setDose(dose);
                        controller.checkDose();
                        controller.checkAllergens();
                    }
                }
            }
        });
        this.diagnoseSelector.setOnAction(e -> {
            if (!this.diagnoses.isEmpty()) {
                this.diagnose = this.diagnoseSelector.getSelectionModel().getSelectedItem();
                this.prescription.setDiagnose(this.diagnose);
            }
        });
        this.startDatePicker.setOnAction(e -> {
            this.startDate = Date.from(this.startDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            this.prescription.setStartDate(this.startDate);
            this.controller.checkDose();
        });
        this.endDatePicker.setOnAction(e -> {
            this.endDate = Date.from(this.endDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            this.prescription.setEndDate(this.endDate);
            this.controller.checkDose();
        });
    }
    
    @Override
    public void setDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
        this.prescription.setDiagnose(this.diagnose);
    }
    
    @Override
    public Text getPatientField() {
        return this.patientField;
    }

    @Override
    public ChoiceBox<Diagnose> getDiagnoseSelector() {
        return diagnoseSelector;
    }

    @Override
    public Button getCancelButton() {
        return cancelButton;
    }

    @Override
    public Button getSaveButton() {
        return saveButton;
    }
    
    @Override
    public Prescription getPrescription() {
        return this.prescription;
    }
    
    @Override
    public void setNullDoseMessage() {
        this.doseField.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7);");
    }
    
    @Override
    public void setInsuffucientDoseMessage() {
        this.doseField.setStyle("-fx-background-color: rgba(0, 0, 255, 0.5);");
    }

    @Override
    public void setOptimalDoseMessage() {
        this.doseField.setStyle("-fx-background-color: rgba(0, 255, 0, 0.5);");
    }

    @Override
    public void setOverOptimalDoseMessage() {
        this.doseField.setStyle("-fx-background-color: rgba(255, 255, 0, 0.5);");
    }

    @Override
    public void setRiskLimitDoseMessage() {
        this.doseField.setStyle("-fx-background-color: rgba(255, 0, 0, 0.5);");
    }

    @Override
    public void setOverdoseMessage() {
        this.doseField.setStyle("-fx-background-color: rgba(255, 0, 0, 0.5);");
        Alert alert = new Alert(Alert.AlertType.WARNING, "Olet määräämässä vaarallista annostusta!\nKerta-annos on vaarallisen suuri.\nPienennä annostusta.");
        alert.setTitle("Lääkelaskuri");
        alert.setHeaderText("Varoitus:");
        alert.initStyle(StageStyle.UNDECORATED);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("prescriptionform.css").toExternalForm());
        alert.showAndWait();
    }
    
    @Override
    public void setCumulativeOverdoseMessage() {
        this.doseField.setStyle("-fx-background-color: rgba(255, 0, 0, 0.5);");
        Alert alert = new Alert(Alert.AlertType.WARNING, "Olet määräämässä vaarallista annostusta!\nKumulatiivinen vaikutus nostaa lääkeaineen pitoisuuden liian korkeaksi.\nPienennä annostusta tai lyhennä kuurin kestoa.");
        alert.setTitle("Lääkelaskuri");
        alert.setHeaderText("Varoitus:");
        alert.initStyle(StageStyle.UNDECORATED);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("prescriptionform.css").toExternalForm());
        alert.showAndWait();
    }
    
    @Override
    public void setIsAllergicMessage(List<String> allergens) {
        this.drugField.setFill(Color.RED);
        String s = "";
        for (String a : allergens) {
            s += a + "\n";
        }
        Alert alert = new Alert(Alert.AlertType.WARNING, "Allergia varoitus!\nValitsemasi lääke sisältää ainesosia, joille potilas on allerginen:\n\n" + s);
        alert.setTitle("Allergeenitarkistus");
        alert.setHeaderText("Varoitus:");
        alert.initStyle(StageStyle.UNDECORATED);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("prescriptionform.css").toExternalForm());
        alert.showAndWait();
    }

    @Override
    public void markUpdate() {
        this.infoField.appendText("\nMuokattu: " + LocalDate.now(ZoneId.systemDefault()).toString());
        this.info = this.infoField.getText();
        this.prescription.setInfo(this.info);
    }
}

package resources.prescription;

import gui.AlertMessage;
import gui.Localisation;
import static gui.Localisation.getInstance;
import resources.SideBarListView_IF;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import resources.diagnose.Diagnose;
import resources.drug.Drug;
import resources.patient.Patient;
import resources.user.User_IF;

/**
 * FXML Controller class
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class PrescriptionFormGUI extends Tab implements PrescriptionFormGUI_IF {
    ResourceBundle text;
    Localisation local = getInstance();
    AlertMessage alertMessage = AlertMessage.getINSTANCE();
    
    @FXML
    private GridPane gridPane;
    @FXML
    private Label patientLabel;
    @FXML
    private TextField patientField;
    @FXML
    private Label diagnoseLabel;
    @FXML
    private ChoiceBox<Diagnose> diagnoseSelector;
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
    @FXML
    private Text drugDoseField;
    @FXML
    private RadioButton rb1;
    @FXML
    private RadioButton rb2;
    @FXML
    private RadioButton rb3;
    @FXML
    private ToggleGroup strategy;
    
    private PrescriptionMakerController_IF controller;
    private ObservableList<Diagnose> diagnoses;
    private final SideBarListView_IF<Patient> patientSelector;
    private final SideBarListView_IF<Drug> drugSelector;
    private Prescription prescription;
    private FXMLLoader loader;
    private PrescriptionValidator validator = PrescriptionValidator.getInstance();
    
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
    
    public PrescriptionFormGUI(ResourceBundle rb, SideBarListView_IF<Patient> patientSelector, SideBarListView_IF<Drug> drugSelector, Prescription prescription) {
        this.text = rb;
        this.patientSelector = patientSelector;
        this.drugSelector = drugSelector;
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
            loader.setResources(rb);
            loader.load();
            infoField.setWrapText(true);
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
        if (this.dose != 0) {
            this.doseField.setText(Double.toString(this.dose));
        }
        if (this.timesADay != 0) {
            this.timesADayField.setText(Integer.toString(this.timesADay));
        }
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
                    if (prescription.getPatient() != null) {
                        dose = controller.getOptimalDose();
                        prescription.setDose(dose);
                        doseField.setText(Double.toString(dose));
                        controller.checkDose();
                        controller.checkAllergens();
                        controller.checkCrossReactions();
                    }
                }
            }
        });
        this.doseField.setOnKeyReleased(e -> {
            try {
                this.dose = Double.parseDouble(this.doseField.getText().replace(',', '.'));
                this.prescription.setDose(this.dose);
                this.controller.checkDose();
                if (this.doseField.getTooltip().isActivated()) {
                    this.doseField.getTooltip().hide();
                    this.doseField.getTooltip().setText(text.getString("doseHint"));
                }
            } catch (NumberFormatException ex) {
                if (!this.doseField.getText().isEmpty()){
                    this.doseField.getTooltip().setText(text.getString("falseEntry"));
                    this.doseField.getTooltip().centerOnScreen();
                    this.doseField.getTooltip().show(this.doseField.getScene().getWindow());
                }
            }
        });
        this.timesADayField.setOnKeyReleased(e -> {
            try {
                this.timesADay = Integer.parseInt(this.timesADayField.getText());
                this.prescription.setTimesADay(this.timesADay);
                this.controller.checkDose();
            } catch (NumberFormatException ex) {
            }
        });
        this.infoField.setOnKeyReleased(e -> {
            this.info = this.infoField.getText();
            this.prescription.setInfo(this.info);
        });
        this.strategy.selectedToggleProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue != null && !newValue.equals(oldValue)) {
                    controller.setCalculatorStrategy(strategy.getToggles().indexOf(newValue));
                    if (doseField.getText().isEmpty() && validator.isCalculable(prescription)) {
                        dose = controller.getOptimalDose();
                        doseField.setText(Double.toString(dose));
                        prescription.setDose(dose);
                    }
                    if (validator.isEvaluable(prescription)) {
                        controller.checkDose();
                    }
                }
            }
        
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
                    if (prescription.getDrug() != null) {
                        dose = controller.getOptimalDose();
                        doseField.setText(Double.toString(dose));
                        prescription.setDose(dose);
                        controller.checkDose();
                        controller.checkAllergens();
                        controller.checkCrossReactions();
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
    public TextField getPatientField() {
        return this.patientField;
    }

    @Override
    public TextField getDrugField() {
        return drugField;
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
        this.doseField.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7);"); //white
        this.drugDoseField.setText(text.getString("nullDose"));
    }
    
    @Override
    public void setInsuffucientDoseMessage() {
        this.doseField.setStyle("-fx-background-color: rgba(0, 0, 255, 0.5);");
        this.drugDoseField.setText(text.getString("insuffucientDose"));
    }

    @Override
    public void setOptimalDoseMessage() {
        this.doseField.setStyle("-fx-background-color: rgba(0, 255, 0, 0.5);");
        this.drugDoseField.setText(text.getString("optimalDose"));
    }

    @Override
    public void setOverOptimalDoseMessage() {
        this.doseField.setStyle("-fx-background-color: rgba(255, 255, 0, 0.5);"); 
        this.drugDoseField.setText(text.getString("overOptimalDose"));
    }

    @Override
    public void setRiskLimitDoseMessage() {
        this.doseField.setStyle("-fx-background-color: rgba(255, 0, 0, 0.5);");
        this.drugDoseField.setText(text.getString("riskLimitDose"));
    }

    @Override
    public void setOverdoseMessage() {
        this.doseField.setStyle("-fx-background-color: rgba(255, 0, 0, 0.5);");
        alertMessage.showWarningAlert(text.getString("titleDrugCalculator"),
                text.getString("warning"), text.getString("alertOverdose"));
    }

    @Override
    public void setCumulativeOverdoseMessage() {
        this.doseField.setStyle("-fx-background-color: rgba(255, 0, 0, 0.5);");
        alertMessage.showWarningAlert(text.getString("titleDrugCalculator"),
                text.getString("warning"), text.getString("alertCumulativeOverdose"));
    }
    
    @Override
    public void setIsAllergicMessage(List<String> allergens) {
                if (!this.timesADayField.getText().isEmpty()) {
                    this.timesADayField.getTooltip().setText(text.getString("falseEntry"));
                }
        String s = "";
        for (String a : allergens) {
            s += a + "\n";
        }
        alertMessage.showWarningAlert(text.getString("titleAllergen"),
                text.getString("warning"),text.getString("alertAllergin")+s);
    }

    @Override
    public void markUpdate() {
        this.infoField.appendText(text.getString("updated") + LocalDate.now(ZoneId.systemDefault()).toString());
        this.info = this.infoField.getText();
        this.prescription.setInfo(this.info);
    }

    @Override
    public void setCrossReactionMessage(HashMap crossReactions) {
        text = local.language();
        String prescribedDrug = " ";
        String crossReactionDrug = " ";
        Set set = crossReactions.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry)iterator.next();
            prescribedDrug += mentry.getKey()+"\n";
            crossReactionDrug += mentry.getValue() +"\n";
        }    
        alertMessage.showWarningAlert(text.getString("titleCrossReaction"),text.getString("warning")
                , text.getString("alertCrossReaction")+text.getString("prescribedDrug")
                +prescribedDrug + text.getString("crossReactionDrug")+crossReactionDrug);
    } 
}

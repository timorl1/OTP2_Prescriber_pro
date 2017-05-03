package resources.prescription;

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
import javafx.scene.paint.Color;
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
            patientField.setEditable(false);
            patientField.setPromptText(text.getString("choosePatient"));
            drugField.setEditable(false);
            drugField.setPromptText(text.getString("chooseDrug"));
            patientLabel.setText(text.getString("patient")+":");
            diagnoseLabel.setText(text.getString("diagnose")+":");
            drugLabel.setText(text.getString("drug")+":");
            doseLabel.setText(text.getString("singleDose")+":");
            timesADayLabel.setText(text.getString("timesADay")+":");
            startDateLabel.setText(text.getString("startDate")+":");
            endDateLabel.setText(text.getString("endDate")+":");
            infoLabel.setText(text.getString("info")+":");
            cancelButton.setText(text.getString("cancel"));
            saveButton.setText(text.getString("save"));
            mainTitle.setText(text.getString("prescription"));
            doctorNameLabel.setText(text.getString("doctor"));
            prescriptionIdLabel.setText(text.getString("ID"));
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
        text = local.language();
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
                    //drugField.setFill(Color.BLACK);
                    if (prescription.getPatient() != null) {
                        dose = controller.getOptimalDose();
                        prescription.setDose(dose);
                        doseField.setText(Double.toString(dose));
                        //drugField.setFill(Color.BLACK);
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
            } catch (NumberFormatException ex) {
                if (!this.doseField.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.WARNING, text.getString("falseEntry"));
                    alert.initStyle(StageStyle.UNDECORATED);
                    alert.getDialogPane().getStylesheets().add(getClass().getResource("prescriptionform.css").toExternalForm());
                    alert.showAndWait();
                }
            }
            this.prescription.setDose(this.dose);
            this.controller.checkDose();
        });
        this.timesADayField.setOnKeyReleased(e -> {
            try {
                this.timesADay = Integer.parseInt(this.timesADayField.getText());
            } catch (NumberFormatException ex) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, text.getString("falseEntry"));
                    alert.initStyle(StageStyle.UNDECORATED);
                    alert.getDialogPane().getStylesheets().add(getClass().getResource("prescriptionform.css").toExternalForm());
                    alert.showAndWait();
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
                   // drugField.setFill(Color.BLACK);
                    if (prescription.getDrug() != null) {
                        dose = controller.getOptimalDose();
                        doseField.setText(Double.toString(dose));
                       // drugField.setFill(Color.BLACK);
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
        text = local.language();
        this.doseField.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7);"); //white
        this.drugDoseField.setText(text.getString("nullDose"));
        //this.drugDoseField.setFill(Color.rgb(255, 255, 255, 0.7));
    }
    
    @Override
    public void setInsuffucientDoseMessage() {
        text = local.language();
        this.doseField.setStyle("-fx-background-color: rgba(0, 0, 255, 0.5);");
        this.drugDoseField.setText(text.getString("insuffucientDose"));
       // this.drugDoseField.setFill(Color.rgb(0, 0, 255, 0.5));
    }

    @Override
    public void setOptimalDoseMessage() {
        text = local.language();
        this.doseField.setStyle("-fx-background-color: rgba(0, 255, 0, 0.5);");
        this.drugDoseField.setText(text.getString("optimalDose"));
        //this.drugDoseField.setFill(Color.rgb(0, 255, 0, 0.5));
    }

    @Override
    public void setOverOptimalDoseMessage() {
        text = local.language();
        this.doseField.setStyle("-fx-background-color: rgba(255, 255, 0, 0.5);"); 
        this.drugDoseField.setText(text.getString("overOptimalDose"));
        //this.drugDoseField.setFill(Color.rgb(255, 255, 0, 0.5));
    }

    @Override
    public void setRiskLimitDoseMessage() {
        text = local.language();
        this.doseField.setStyle("-fx-background-color: rgba(255, 0, 0, 0.5);");
        this.drugDoseField.setText(text.getString("riskLimitDose"));
        //this.drugDoseField.setFill(Color.rgb(255, 0, 0, 0.5));
    }

    @Override
    public void setOverdoseMessage() {
        text = local.language();
        this.doseField.setStyle("-fx-background-color: rgba(255, 0, 0, 0.5);");
        Alert alert = new Alert(Alert.AlertType.WARNING, text.getString("alertOverdose"));
        alert.setTitle(text.getString("titleDrugCalculator"));
        alert.setHeaderText(text.getString("warning"));
        alert.initStyle(StageStyle.UNDECORATED);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("prescriptionform.css").toExternalForm());
        alert.showAndWait();
    }

    @Override
    public void setCumulativeOverdoseMessage() {
        text = local.language();
        this.doseField.setStyle("-fx-background-color: rgba(255, 0, 0, 0.5);");
        Alert alert = new Alert(Alert.AlertType.WARNING, text.getString("alertCumulativeOverdose"));
        alert.setTitle(text.getString("titleDrugCalculator"));
        alert.setHeaderText(text.getString("warning"));
        alert.initStyle(StageStyle.UNDECORATED);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("prescriptionform.css").toExternalForm());
        alert.showAndWait();
    }
    
    @Override
    public void setIsAllergicMessage(List<String> allergens) {
        text = local.language();
        //this.drugField.setFill(Color.RED);
        String s = "";
        for (String a : allergens) {
            s += a + "\n";
        }
        Alert alert = new Alert(Alert.AlertType.WARNING,text.getString("alertAllergin")+s);
        alert.setTitle(text.getString("titleAllergen"));
        alert.setHeaderText(text.getString("warning"));
        alert.initStyle(StageStyle.UNDECORATED);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("prescriptionform.css").toExternalForm());
        alert.showAndWait();
    }

    @Override
    public void markUpdate() {
        text = local.language();
        this.infoField.appendText(text.getString("updated") + LocalDate.now(ZoneId.systemDefault()).toString());
        this.info = this.infoField.getText();
        this.prescription.setInfo(this.info);
    }

    @Override
    public void setCrossReactionMessage(HashMap crossReactions) {
        text = local.language();
        this.drugField.setFill(Color.RED);
        String prescribedDrug = " ";
        String crossReactionDrug = " ";
        Set set = crossReactions.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry)iterator.next();
            prescribedDrug += mentry.getKey()+"\n";
            crossReactionDrug += mentry.getValue() +"\n";
        }    
 
        Alert alert = new Alert(Alert.AlertType.WARNING, text.getString("alertCrossReaction")+text.getString("prescribedDrug")
                +prescribedDrug + text.getString("crossReactionDrug")+crossReactionDrug);
        alert.setTitle(text.getString("titleCrossReaction"));
        alert.setHeaderText(text.getString("warning"));
        alert.initStyle(StageStyle.UNDECORATED);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("prescriptionform.css").toExternalForm());
        alert.showAndWait(); 
    } 
}

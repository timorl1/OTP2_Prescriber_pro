package resources.prescription;

import calculator.DoseStatus;
import gui.AlertMessage;
import gui.MainGUI_IF;
import resources.SideBarListView_IF;
import java.io.IOException;
import java.text.DecimalFormat;
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
import resources.diagnose.Diagnose;
import resources.drug.Drug;
import resources.patient.Patient;
import gui.Mediator_IF;
import javafx.event.Event;

/**
 * FXML Controller class
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class PrescriptionFormGUI extends Tab implements PrescriptionFormGUI_IF {
    
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
    
    private final Mediator_IF mediator;
    private final PrescriptionValidator validator = PrescriptionValidator.getInstance();
    private final AlertMessage alertMessage = AlertMessage.getINSTANCE();
    private final DecimalFormat formatter = new DecimalFormat("#0.000");
    private final ResourceBundle text;
    private final SideBarListView_IF<Patient> patientSelector;
    private final SideBarListView_IF<Drug> drugSelector;
    private final Prescription prescription;
    private FXMLLoader loader;
    
    private ChangeListener patientSelectorListener;
    private ChangeListener drugSelectionListener;
    
    public PrescriptionFormGUI(Mediator_IF mediator, ResourceBundle rb, SideBarListView_IF<Patient> patientSelector, SideBarListView_IF<Drug> drugSelector, Prescription prescription) {
        this.mediator = mediator;
        this.text = rb;
        this.patientSelector = patientSelector;
        this.drugSelector = drugSelector;
        this.prescription = prescription;
        try {
            this.loader = new FXMLLoader(getClass().getResource("PrescriptionForm.fxml"));
            this.loader.setController(this);
            this.loader.setRoot(this);
            this.loader.setResources(rb);
            this.loader.load();
            this.initializeFields();
            this.initializeBasicListeners();
            if (this.prescription.getPatient() == null) {
                this.initializeNewPrescriptionListeners();
            }
            this.performChecks();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void initializeFields() {
        this.creationDateLabel.setText(this.prescription.getCreationDate().toString());
        this.doctorNameLabel.setText(this.prescription.getDoctor().getFirstName() + " " + this.prescription.getDoctor().getLastName());
        if (this.prescription.getId() != 0) {
            this.prescriptionIdLabel.setText("#" + Integer.toString(this.prescription.getId()));
        }
        if (this.prescription.getPatient() != null) {
            this.patientField.setText(this.prescription.getPatient().toString());
            ObservableList<Diagnose> list = FXCollections.observableArrayList();
            list.add(this.prescription.getDiagnose());
            this.diagnoseSelector.setItems(list);
            this.diagnoseSelector.getSelectionModel().clearAndSelect(0);
        }
        else if (this.patientSelector.getSelection() != null) {
            this.prescription.setPatient(this.patientSelector.getSelection());
            this.patientField.setText(this.patientSelector.getSelection().toString());
            ObservableList<Diagnose> list = FXCollections.observableArrayList();
            list.add(this.prescription.getDiagnose());
            this.diagnoseSelector.setItems(list);
            this.diagnoseSelector.getSelectionModel().clearAndSelect(0);
        }
        if (this.prescription.getDrug() != null) {
            this.drugField.setText(this.prescription.getDrug().toString());
        }
        else if (this.drugSelector.getSelection() != null) {
            this.prescription.setDrug(this.drugSelector.getSelection());
            this.drugField.setText(this.drugSelector.getSelection().toString());
        }
        if (this.prescription.getDose() != 0) {
            this.doseField.setText(Double.toString(this.prescription.getDose()));
        }
        if (this.prescription.getTimesADay() != 0) {
            this.timesADayField.setText(Integer.toString(this.prescription.getTimesADay()));
        }
        this.infoField.setText(this.prescription.getInfo());
        if (this.prescription.getStartDate() != null) {
            this.startDatePicker.setValue(this.prescription.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        if (this.prescription.getEndDate() != null) {
            this.endDatePicker.setValue(this.prescription.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
    }
    
    private void initializeBasicListeners() {
        this.drugSelectionListener = new ChangeListener<Drug>() {
            @Override
            public void changed(ObservableValue<? extends Drug> ov, Drug oldValue, Drug newValue) {
                if (newValue != null && !newValue.equals(oldValue)) {
                    prescription.setDrug(newValue);
                    drugField.setText(newValue.toString());
                    performChecks();
                }
            }
        };
        this.drugSelector.getListView().getSelectionModel().selectedItemProperty().addListener(this.drugSelectionListener);
    }
    
    private void initializeNewPrescriptionListeners() {
        this.patientSelectorListener = new ChangeListener<Patient>() {
            @Override
            public void changed(ObservableValue<? extends Patient> ov, Patient oldValue, Patient newValue) {
                if (newValue != null && !newValue.equals(oldValue)) {
                    prescription.setPatient(newValue);
                    patientField.setText(prescription.getPatient().toString());
                    ObservableList<Diagnose> list = FXCollections.observableList(prescription.getPatient().getDiagnoses());
                    diagnoseSelector.setItems(list);
                    diagnoseSelector.getSelectionModel().clearAndSelect(0);
                    prescription.setDiagnose(diagnoseSelector.getSelectionModel().getSelectedItem());
                    performChecks();
                }
            }
        };
        this.patientSelector.getListView().getSelectionModel().selectedItemProperty().addListener(this.patientSelectorListener);
    }
    
    @Override
    @FXML
    public void save() {
        if (this.validator.validate(prescription)) {
            this.mediator.savePrescription();
            try {
                this.patientSelector.getListView().getSelectionModel().selectedItemProperty().removeListener(this.patientSelectorListener);
            } catch (NullPointerException e) {
            }
            try {
                this.drugSelector.getListView().getSelectionModel().selectedItemProperty().removeListener(this.drugSelectionListener);
            } catch (NullPointerException e) {
            }
            this.getTabPane().getTabs().remove(this);
        }
        else {
            this.alertMessage.showWarningAlert(text.getString("message"),
                    text.getString("alertTextWarning"),
                    text.getString("alertTitlePrescriptionNotSent"));
        }
    }

    @Override
    @FXML
    public void cancel() {
        this.mediator.revertPrescription();
        try {
            this.patientSelector.getListView().getSelectionModel().selectedItemProperty().removeListener(this.patientSelectorListener);
        } catch (NullPointerException e) {
        }
        try {
            this.drugSelector.getListView().getSelectionModel().selectedItemProperty().removeListener(this.drugSelectionListener);
        } catch (NullPointerException e) {
        }
        this.getTabPane().getTabs().remove(this);
    }

    @Override
    @FXML
    public void changeStrategy() {
        int i = this.strategy.getToggles().indexOf(this.strategy.getSelectedToggle());
        this.mediator.changeCalculationMethod(i);
        if (this.validator.isCalculable(this.prescription)) {
            this.getOptimalDose();
        }
        if (this.validator.isEvaluable(this.prescription)) {
            this.checkDose();
        }
    }
    
    @Override
    @FXML
    public void setPatient() {
        if (this.patientField.isFocused()) {
            this.patientSelector.getTitledPane().setExpanded(true);
        }
    }
    
    @Override
    @FXML
    public void setDiagnose() {
        this.prescription.setDiagnose(this.diagnoseSelector.getValue());
    }

    @Override
    @FXML
    public void setDrug() {
        if (this.drugField.isFocused()) {
            this.drugSelector.getTitledPane().setExpanded(true);
        }
    }

    @Override
    @FXML
    public void setDose() {
        try {
            this.prescription.setDose(Double.parseDouble(this.doseField.getText().replace(',', '.')));
            this.checkDose();
            if (this.doseField.getTooltip().isActivated()) {
                this.doseField.getTooltip().hide();
                this.doseField.getTooltip().setText(text.getString("doseHint"));
            }
        } catch (NumberFormatException ex) {
            if (!this.doseField.getText().isEmpty()) {
                this.doseField.getTooltip().setText(text.getString("falseEntry"));
                this.doseField.getTooltip().centerOnScreen();
                this.doseField.getTooltip().show(this.doseField.getScene().getWindow());
            }
        }
    }

    @Override
    @FXML
    public void setTimesADay() {
        try {
            this.prescription.setTimesADay(Integer.parseInt(this.timesADayField.getText()));
            this.checkDose();
            if (this.timesADayField.getTooltip().isActivated()) {
                this.timesADayField.getTooltip().hide();
                this.timesADayField.getTooltip().setText(text.getString("timesADayHint"));
            }
        } catch (NumberFormatException ex) {
            if (!this.timesADayField.getText().isEmpty()) {
                this.timesADayField.getTooltip().setText(text.getString("falseEntry"));
                this.timesADayField.getTooltip().centerOnScreen();
                this.timesADayField.getTooltip().show(this.timesADayField.getScene().getWindow());
            }
        }
    }

    @Override
    @FXML
    public void setStartDate() {
        this.prescription.setStartDate(Date.from(this.startDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        this.checkDose();
    }

    @Override
    @FXML
    public void setEndDate() {
        this.prescription.setEndDate(Date.from(this.endDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        this.checkDose();
    }
    
    @Override
    @FXML
    public void setInfo() {
        this.prescription.setInfo(this.infoField.getText());
    }
    
    @Override
    public void checkDose() {
        if (this.validator.isEvaluable(this.prescription)) {
            DoseStatus status = this.mediator.checkDoseLevel();
            switch (status) {
                case NULL:
                    this.setNullDoseMessage();
                    break;
                case INSUFFICIENT:
                    this.setInsuffucientDoseMessage();
                    break;
                case OPTIMAL:
                    this.setOptimalDoseMessage();
                    break;
                case OVER_OPTIMAL:
                    this.setOverOptimalDoseMessage();
                    break;
                case RISK_LIMIT:
                    this.setRiskLimitDoseMessage();
                    break;
                case OVERDOSE:
                    this.setOverdoseMessage();
                    break;
                case CUMULATIVE_OVERDOSE:
                    this.setCumulativeOverdoseMessage();
                    break;
            }
        }
    }
    
    @Override
    public void getOptimalDose() {
        if (this.validator.isCalculable(this.prescription) && !this.doseField.isFocused()) {
            this.doseField.setText(this.formatter.format(this.mediator.getOptimalDose()));
            this.prescription.setDose(Double.parseDouble(this.doseField.getText().replace(',', '.')));
        }
    }
    
    @Override
    public void checkAllergens() {
        if (this.validator.isCalculable(this.prescription)) {
            List<String> allergens = this.mediator.checkForAllergens();
            if (!allergens.isEmpty()) {
                this.setIsAllergicMessage(allergens);
            }
        }
    }

    @Override
    public void checkCrossReactions() {
        if (this.validator.isCalculable(this.prescription)) {
            HashMap crossReactions = this.mediator.checkForCrossReactions();
            if (!crossReactions.isEmpty()) {
                this.setCrossReactionMessage(crossReactions);
            }
        }
    }
    
    @Override
    public void performChecks() {
        this.getOptimalDose();
        this.checkDose();
        this.checkAllergens();
        this.checkCrossReactions();
    }
    
    @Override
    public void setCrossReactionMessage(HashMap crossReactions) {
        String prescribedDrug = " ";
        String crossReactionDrug = " ";
        Set set = crossReactions.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry)iterator.next();
            prescribedDrug += mentry.getKey()+"\n";
            crossReactionDrug += mentry.getValue() +"\n";
        }    
        this.alertMessage.showWarningAlert(this.text.getString("titleCrossReaction"),
                this.text.getString("warning"),
                this.text.getString("alertCrossReaction")
                        +text.getString("prescribedDrug")
                        +prescribedDrug
                        +this.text.getString("crossReactionDrug")+crossReactionDrug);
    }
    
    @Override
    public void setIsAllergicMessage(List<String> allergens) {
        String s = "";
        for (String a : allergens) {
            s += a + "\n";
        }
        alertMessage.showWarningAlert(text.getString("titleAllergen"),
                text.getString("warning"),text.getString("alertAllergin")+s);
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
        this.drugDoseField.setText(text.getString("overdose"));
        alertMessage.showWarningAlert(text.getString("titleDrugCalculator"),
                text.getString("warning"), text.getString("alertOverdose"));
    }

    @Override
    public void setCumulativeOverdoseMessage() {
        this.doseField.setStyle("-fx-background-color: rgba(255, 0, 0, 0.5);");
        this.drugDoseField.setText(text.getString("cumulativeOverdose"));
        alertMessage.showWarningAlert(text.getString("titleDrugCalculator"),
                text.getString("warning"), text.getString("alertCumulativeOverdose"));
    }

    @Override
    public void markUpdate() {
        this.infoField.appendText(text.getString("updated") + LocalDate.now(ZoneId.systemDefault()).toString());
        this.prescription.setInfo(this.infoField.getText());
    }
}

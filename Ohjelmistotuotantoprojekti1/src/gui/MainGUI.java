package gui;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;
import javafx.util.converter.DoubleStringConverter;
import model.Diagnose;
import model.Drug;
import model.Employee;
import model.Patient;
import model.Prescription;
import model.User;

/**
 * FXML Controller class
 *
 * @author joosiika
 */
public class MainGUI implements Initializable, MainGUI_IF {
    
    @FXML
    private AnchorPane root;
    @FXML
    private TabPane tabPane;
    private SideBarGUI_IF sideBar;
    private LoginGUI_IF login;
    private PrescriptionFormGUI prescriptionForm;
    private SideBarListView_IF<Patient> patientListView;
    private SideBarListView_IF<Drug> drugListView;
    private SideBarListView_IF<Prescription> prescriptionListView;
    private ListTabGUI_IF<String> prescriptionTab;
    private ListTabGUI_IF<Prescription> patientPrescriptionTab;
    private ListTabGUI_IF<String> diagnoseTab;
    private ListTabGUI_IF<Diagnose> patientDiagnoseTab;
    private SideBarListView_IF<String> messageListView;
    private SideBarListView_IF<User> userListView;
    private SideBarListView_IF<Employee> employeeListView;
    private SideBarListView_IF<String> databaseListView;
    
    private Controller controller;
    
    private AppStatus status;
    
    private DoubleStringConverter dsc;    
    
    //Load login-component on initalization
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.controller = new Controller(this);
        this.dsc = new DoubleStringConverter();
        setLogin();
        setStatus(AppStatus.IDLE);
    }
    
    public AppStatus getStatus() {
        return status;
    }

    public void setStatus(AppStatus status) {
        this.status = status;
    }
    
    //Creates a new instance of the LoginGUI and passes itself as a parameter
    //Adds the LoginGUI as a child-component to the MainGUI's anchor pane
    @Override
    public void setLogin() {
        this.login = new LoginGUI();
        this.login.getButton().setOnAction(e -> {
            this.controller.login(this.login.getUsername(), this.login.getPassword());
        });
        this.root.getChildren().add((LoginGUI)this.login);
    }
    
    //Set login failed message
    @Override
    public void setLoginFailed() {
        this.login.addMessage("Kirjautuminen epäonnistui. Väärä käyttäjätunnus tai salasana!");
    }
    
    //Set access denied message
    @Override
    public void setAccessDenied() {
        this.root.getChildren().clear();
        this.root.getChildren().add(new Label("Tunnuksesi on lukittu. Ota yhteys ylläpitäjään."));
    }
    
    //Removes the login component and loads the side bar component
    //Component type is defined by user's priviledges
    //1 - load nurses sidebar components
    //2 - load doctors sidebar components
    //3 - load administrators sidebar components
    //0 - should print out "access revoked" and instructions to contact the administrator
    @Override
    public void setSideBar() {
        this.root.getChildren().remove((LoginGUI) this.login);
        this.sideBar = new SideBarGUI(this);
        this.root.getChildren().add((SideBarGUI) this.sideBar);
    }

    //Loads the tab pane component depending of the side bar content selection
    @Override
    public <T> void loadTabPane(List<T> list) {
        ListTabGUI_IF<T> tab = new ListTabGUI("Potilaan tiedot");
        tab.setList(list);
        this.tabPane.getTabs().add((ListTabGUI)tab);
    }

    @Override
    public void setPatientList() {
        this.patientListView = new SideBarListViewGUI("Potilas");
        this.patientListView.getTitledPane().setOnMouseClicked(e -> {
            if (this.patientListView.isExpanded()) {
                this.patientListView.setList(this.controller.getPatients());
            }
        });
        this.patientListView.getListView().setOnMouseClicked(e -> {
            if (this.patientListView.getSelection() != null) {
                this.tabPane.getTabs().clear();
                this.controller.getPatientDetails();
                this.controller.getPatientDiagnoses();
                this.controller.getPatientPrescriptions();
            }
            else {
                this.tabPane.getTabs().clear();
            }
        });
        this.sideBar.addView((SideBarListViewGUI)this.patientListView);
    }

    @Override
    public void setDrugList() {
        this.drugListView = new SideBarListViewGUI("Lääkkeet");
        this.drugListView.getTitledPane().setOnMouseClicked(e -> {
            if (this.drugListView.isExpanded()) {
                this. drugListView.setList(this.controller.getDrugs());
            }
        });
        this.drugListView.getListView().setOnMouseClicked(e -> {
            if (this.drugListView.getSelection() != null) {
                //this.loadTabPane(drugListView.getSelection());
            }
            else {
                this.tabPane.getTabs().clear();
            }
        });
        this.sideBar.addView((SideBarListViewGUI)this.drugListView);
    }

    @Override
    public void setPrescriptionList() {
        this.prescriptionListView = new SideBarListViewGUI("Omat Reseptit");
        this.prescriptionListView.getTitledPane().setOnMouseClicked((event) -> {
            if (this.prescriptionListView.isExpanded()) {
                this.prescriptionListView.setList(this.controller.getPrescriptions());
            }
        });
        this.sideBar.addView((SideBarListViewGUI)this.prescriptionListView);
    }

    @Override
    public void setMessageList() {
        this.messageListView = new SideBarListViewGUI("Viestit");
        this.messageListView.getTitledPane().setOnMouseClicked((event) -> {
            if (this.messageListView.isExpanded()) {
                this.messageListView.setList(this.controller.getMessages());
            }
        });
        this.sideBar.addView((SideBarListViewGUI)this.messageListView);
    }

    //List all users
    @Override
    public void setUserList() {
        this.userListView = new SideBarListViewGUI("Käyttäjät");
        //Create custom cells with buttons to be able to easily set users accounts in locked state
        this.userListView.getListView().setCellFactory(p -> {
            ListCell<User> cell = new ListCell<User>(){
                @Override
                protected void updateItem(User u, boolean bln) {
                    super.updateItem(u, bln);
                    if (u != null) {
                        setText(u.toString());
                        Button button = new Button();
                        if (u.getPrivileges() != 0) {
                            button.setText("-");
                        }
                        else {
                            button.setText("+");
                        }
                        button.setOnAction((ActionEvent event) -> {
                            if (u.getPrivileges() != 0) {
                                controller.lockUser(u);
                                button.setText("+");
                            }
                            else {
                                controller.setUserPriviledges(u);
                                button.setText("-");
                            }
                        });
                        setGraphic(button);
                    }
                }
            };
            //cell.setContentDisplay(ContentDisplay.RIGHT);
            return cell;
        });
        this.userListView.getTitledPane().setOnMouseClicked(e -> {
            if (this.userListView.isExpanded()) {
                this.userListView.setList(this.controller.getUsers());
            }
        });
        this.userListView.getListView().setOnMouseClicked(e -> {
            if (this.userListView.getSelection() != null) {
                this.tabPane.getTabs().clear();
                this.controller.getUserDetails();
            }
            else {
                this.tabPane.getTabs().clear();
            }
        });
        this.sideBar.addView((SideBarListViewGUI)this.userListView);
    }

    @Override
    public void setEmployeeList() {
        this.employeeListView = new SideBarListViewGUI("Työntekijät");
        this.employeeListView.getTitledPane().setOnMouseClicked((event) -> {
            if (this.employeeListView.isExpanded()) {
                this.employeeListView.setList(this.controller.getEmployees());
            }
        });
        this.employeeListView.getListView().setOnMouseClicked(e -> {
            if (this.employeeListView.getSelection() != null) {
                this.tabPane.getTabs().clear();
                this.controller.getEmployeeDetails();
            }
            else {
                this.tabPane.getTabs().clear();
            }
        });
        this.sideBar.addView((SideBarListViewGUI)this.employeeListView);
    }

    @Override
    public void setDatabaseList() {
        this.databaseListView = new SideBarListViewGUI("Tietokannat");
        this.databaseListView.getTitledPane().setOnMouseClicked((event) -> {
            if (this.databaseListView.isExpanded()) {
                this.databaseListView.setList(this.controller.getDatabases());
            }
        });
        this.sideBar.addView((SideBarListViewGUI)this.databaseListView);
    }
    
    @Override
    public void setPrescriptionForm(Prescription prescription) {
        this.prescriptionForm = new PrescriptionFormGUI("Lääkemääräys");
        this.prescriptionForm.getCreationDateLabel().setText(prescription.getCreationDate().toString());
        //this.prescriptionForm.getDoctorNameLabel().setText(this.controller.getAppUser());
        this.prescriptionForm.getPrescriptionIdLabel().setText(String.valueOf(prescription.getId()));
        this.prescriptionForm.getCancelButton().setOnAction(e -> {
            this.tabPane.getTabs().remove(this.prescriptionForm);
        });
        this.prescriptionForm.getSaveButton().setOnAction(e -> {
            this.controller.savePrescription();
            this.tabPane.getTabs().remove(this.prescriptionForm);
        });
        this.prescriptionForm.getDoseField().setOnKeyTyped(e -> {
            if (this.dsc.fromString(this.prescriptionForm.getDoseField().getText()) != 0) {
                this.controller.checkDose();
            }
        });
        this.tabPane.getTabs().add(this.prescriptionForm);
    }
    
    @Override
    public void setPrescriptionTools() {
        Button createPrescription = new Button("Uusi lääkemääräys");
        createPrescription.setOnMouseClicked((event) -> {
            this.controller.createNewPrescription();
        });
        this.sideBar.getVbox().getChildren().add(createPrescription);
    }

    @Override
    public void setPatientDetails(Patient patient) {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("Sosiaaliturvatunnus: " + patient.getSSN());
        list.add("Etunimi: " + patient.getFirstName());
        list.add("Sukunimi: " + patient.getLastName());
        list.add("Sukupuoli: " + patient.getGender());
        list.add("Pituus: " + dsc.toString(patient.getHeight()) + " cm");
        list.add("Paino: " + dsc.toString(patient.getWeight()) + " kg");
        ListTabGUI listTab = new ListTabGUI("Potilaan tiedot");
        listTab.getListView().setItems(list);
        this.tabPane.getTabs().add(listTab);
        
    }

    @Override
    public void setPatientDiagnoses(List<Diagnose> list) {
        ObservableList<Diagnose> diagnoses = FXCollections.observableArrayList(list);
        this.patientDiagnoseTab = new ListTabGUI("Potilaan diagnoosit");
        this.patientDiagnoseTab.getListView().setOnMouseClicked(e -> {
            this.controller.getDiagnoseDetails();
        });
        this.patientDiagnoseTab.getListView().setItems(diagnoses);
        this.tabPane.getTabs().add((ListTabGUI)this.patientDiagnoseTab);
    }

    @Override
    public void setPatientPrescriptions(List<Prescription> list) {
        ObservableList<Prescription> prescriptions = FXCollections.observableArrayList(list);
        this.patientPrescriptionTab = new ListTabGUI("Potilaan reseptit");
        this.patientPrescriptionTab.getListView().setOnMouseClicked(e -> {
            this.controller.getPrescriptionDetails();
        });
        this.patientPrescriptionTab.getListView().setItems(prescriptions);
        this.tabPane.getTabs().add((ListTabGUI)this.patientPrescriptionTab);
    }

    @Override
    public void setPrescriptionDetails(Prescription prescription) {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("Tunnus: " + prescription.getId());
        list.add("Luontipäivä: " + prescription.getCreationDate());
        list.add("Potilas: " + prescription.getPatient().getLastName() + ", " + prescription.getPatient().getFirstName() + ", " + prescription.getPatient().getSSN());
        list.add("Lääkäri: " + prescription.getDoctor().getLastName() + ", " + prescription.getDoctor().getFirstName());
        list.add("Diagnoosi: " + prescription.getDiagnose().getId() + ": " + prescription.getDiagnose().getDisease());
        list.add("Lääke: " + prescription.getDrug().getName());
        list.add("Annostus: " + prescription.getDrug() + ", " + prescription.getDose() + " " + prescription.getDrug().getUnit() + " " + prescription.getTimesADay() + " kertaa päivässä.");
        list.add("Ohjeet: " + prescription.getInfo());
        list.add("Alkaen: " + prescription.getStartDate());
        list.add("Päättyen: " + prescription.getEndDate());
        this.tabPane.getTabs().remove(this.prescriptionTab);
        this.prescriptionTab = new ListTabGUI("Reseptin tiedot");
        this.prescriptionTab.getListView().setItems(list);
        this.tabPane.getTabs().add((ListTabGUI)this.prescriptionTab);
        this.tabPane.getSelectionModel().select((ListTabGUI)this.prescriptionTab);
    }
    
    @Override
    public void setDiagnoseDetails(Diagnose diagnose) {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("Tunnus: " + diagnose.getId());
        list.add("Luontipäivä: " + diagnose.getCreationDate());
        list.add("Potilas: " + diagnose.getPatient().getLastName() + ", " + diagnose.getPatient().getFirstName() + ", " + diagnose.getPatient().getSSN());
        list.add("Lääkäri: " + diagnose.getDoctor().getLastName() + ", " + diagnose.getDoctor().getFirstName());
        list.add("Sairaus: " + diagnose.getDisease());
        list.add("Epikriisi: " + diagnose.getEpicrisis());
        if (diagnose.getResolutionDate() != null) {
            list.add("Diagnoosin tila: hoidettu, " + diagnose.getResolutionDate());
        }
        else {
            list.add("Diagnoosin tila: ei hoidettu" );
        }
        this.tabPane.getTabs().remove(this.diagnoseTab);
        this.diagnoseTab = new ListTabGUI("Diagnoosin tiedot");
        this.diagnoseTab.getListView().setItems(list);
        this.tabPane.getTabs().add((ListTabGUI)this.diagnoseTab);
        this.tabPane.getSelectionModel().select((ListTabGUI)this.diagnoseTab);
    }
    
    @Override
    public void setUserDetails(User user) {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("Työntekijänumero: " + user.getId());
        list.add("Käyttäjätunnus: " + user.getUsername());
        list.add("Sähköposti: " + user.getEmail());
        switch (user.getPrivileges()) {
            case 0:
                list.add("Käyttöoikeudet: lukittu");
                break;
            case 1:
                list.add("Käyttöoikeudet: hoitaja");
                break;
            case 2:
                list.add("Käyttöoikeudet: lääkäri");
                break;
            case 3:
                list.add("Käyttöoikeudet: ylläpitäjä");
                break;
            default:
                break;
        }
        this.tabPane.getTabs().clear();
        ListTabGUI_IF listTab = new ListTabGUI("Käyttäjän tiedot");
        listTab.getListView().setItems(list);
        this.tabPane.getTabs().add((ListTabGUI)listTab);
    }
    
    @Override
    public void setEmployeeDetails(Employee employee) {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("Työntekijänumero: " + employee.getUserID());
        list.add("Etunimi: " + employee.getFirstName());
        list.add("Sukunimi: " + employee.getLastName());
        list.add("Titteli: " + employee.getTitle());
        list.add("Sähköposti: " + employee.getEmail());
        this.tabPane.getTabs().clear();
        ListTabGUI_IF listTab = new ListTabGUI("Työntekijän tiedot");
        listTab.getListView().setItems(list);
        this.tabPane.getTabs().add((ListTabGUI)listTab);
    }
    
    @Override
    public void setInsuffucientDoseMessage() {
        this.prescriptionForm.getDoseField().setId("insufficient");
    }

    @Override
    public void setOptimalDoseMessage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setOverOptimalDoseMessage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setRiskLimitDoseMessage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setOverdoseMessage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Patient getSelectedPatient() {
        return this.patientListView.getSelection();
    }

    @Override
    public Prescription getSelectedPrescription() {
        return this.patientPrescriptionTab.getSelection();
    }
    
    @Override
    public Diagnose getSelectedDiagnose() {
        return this.patientDiagnoseTab.getSelection();
    }
    
    @Override
    public User getSelectedUser() {
        return this.userListView.getSelection();
    }
    
    @Override
    public Employee getSelectedEmployee() {
        return this.employeeListView.getSelection();
    }

    @Override
    public Prescription getPrescriptionForm() {
        Prescription prescription = new Prescription();
        String patientName = this.prescriptionForm.getPatientField().getText();
        ObservableList <Patient> list = this.patientListView.getListView().getItems();
        list = list.filtered((Patient t) -> {
            if (t.getFirstName().contains(patientName)) {
                return true;
            }
            return false;
        });
        prescription.setPatient(list.get(0));
        prescription.setDoctorID(3);
        prescription.setDose(this.dsc.fromString(this.prescriptionForm.getDoseField().getText()));
        prescription.setTimesADay(Integer.parseInt(this.prescriptionForm.getTimesADayField().getText()));
        prescription.setDrug(this.drugListView.getSelection());
        prescription.setInfo(this.prescriptionForm.getInfoField().getText());
        prescription.setStartDate(Date.valueOf(this.prescriptionForm.getStartDatePicker().getValue()));
        prescription.setEndDate(Date.valueOf(this.prescriptionForm.getStartDatePicker().getValue()));
        return prescription;
    }
    
}

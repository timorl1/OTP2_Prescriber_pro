package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;
import javafx.util.converter.DoubleStringConverter;
import model.Diagnose;
import model.Drug;
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
    private SideBarListView_IF<Patient> patientListView;
    private SideBarListView_IF<Drug> drugListView;
    private SideBarListView_IF<Prescription> prescriptionListView;
    private ListTabGUI_IF<String> prescriptionTab;
    private ListTabGUI_IF<Prescription> patientPrescriptionTab;
    private ListTabGUI_IF<String> diagnoseTab;
    private ListTabGUI_IF<Diagnose> patientDiagnoseTab;
    private SideBarListView_IF<String> messageListView;
    private SideBarListView_IF<User> userListView;
    private SideBarListView_IF<String> employeeListView;
    private SideBarListView_IF<String> databaseListView;
    
    private Controller controller;
    
    private DoubleStringConverter dsc;
    //Load login-component on initalization
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.controller = new Controller(this);
        this.dsc = new DoubleStringConverter();
        setLogin();
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
    
    @Override
    public void setLoginFailed() {
        this.login.addMessage("Kirjautuminen epäonnistui. Väärä käyttäjätunnus tai salasana!");
    }
    
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
        ListTabGUI_IF<T> tab = new ListTabGUI("Potilaan tieddot");
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

    @Override
    public void setUserList() {
        this.userListView = new SideBarListViewGUI("Käyttäjät");
        this.userListView.getTitledPane().setOnMouseClicked((event) -> {
            if (this.userListView.isExpanded()) {
                this.userListView.setList(this.controller.getUsers());
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
        this.sideBar.addView((SideBarListViewGUI)this.employeeListView);
    }

    @Override
    public void setDatabaseList() {
        this.databaseListView = new SideBarListViewGUI("Tietokannat");
        this.databaseListView.getTitledPane().setOnMouseClicked((event) -> {
            if (this.databaseListView.isExpanded()) {
                this.databaseListView.setList(this.controller.getEmployees());
            }
        });
        this.sideBar.addView((SideBarListViewGUI)this.databaseListView);
    }

    @Override
    public Patient getSelectedPatient() {
        return this.patientListView.getSelection();
    }

    @Override
    public void setPatientDetails(List<String> list) {
        ObservableList<String> details = FXCollections.observableArrayList(list);
        ListTabGUI listTab = new ListTabGUI("Potilaan tiedot");
        listTab.getListView().setItems(details);
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
    public void setPrescriptionDetails(List<String> list) {
        ObservableList<String> details = FXCollections.observableArrayList(list);
        this.tabPane.getTabs().remove(this.prescriptionTab);
        this.prescriptionTab = new ListTabGUI("Reseptin tiedot");
        this.prescriptionTab.getListView().setItems(details);
        this.tabPane.getTabs().add((ListTabGUI)this.prescriptionTab);
        this.tabPane.getSelectionModel().select((ListTabGUI)this.prescriptionTab);
    }
    
    @Override
    public void setDiagnoseDetails(List<String> list) {
        ObservableList<String> details = FXCollections.observableArrayList(list);
        this.tabPane.getTabs().remove(this.diagnoseTab);
        this.diagnoseTab = new ListTabGUI("Diagnoosin tiedot");
        this.diagnoseTab.getListView().setItems(details);
        this.tabPane.getTabs().add((ListTabGUI)this.diagnoseTab);
        this.tabPane.getSelectionModel().select((ListTabGUI)this.diagnoseTab);
    }

    @Override
    public Prescription getSelectedPrescription() {
        return this.patientPrescriptionTab.getSelection();
    }
    
    @Override
    public Diagnose getSelectedDiagnose() {
        return this.patientDiagnoseTab.getSelection();
    }
    
    
}

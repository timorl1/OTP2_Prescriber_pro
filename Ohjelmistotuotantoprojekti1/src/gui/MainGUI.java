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
        SideBarListViewGUI<Drug> drugListView = new SideBarListViewGUI("Lääkkeet");
        drugListView.getTitledPane().setOnMouseClicked(e -> {
            if (drugListView.isExpanded()) {
                drugListView.setList(this.controller.getDrugs());
            }
        });
        drugListView.getListView().setOnMouseClicked(e -> {
            if (drugListView.getSelection() != null) {
                //this.loadTabPane(drugListView.getSelection());
            }
            else {
                this.tabPane.getTabs().clear();
            }
        });
        this.sideBar.addView(drugListView);
    }

    @Override
    public void setPrescriptionList() {
        SideBarListViewGUI<Prescription> prescriptionListView = new SideBarListViewGUI("Omat Reseptit");
        prescriptionListView.getTitledPane().setOnMouseClicked((event) -> {
            if (prescriptionListView.isExpanded()) {
                prescriptionListView.setList(this.controller.getPrescriptions());
            }
        });
        this.sideBar.addView(prescriptionListView);
    }

    @Override
    public void setMessageList() {
        SideBarListViewGUI<String> messageListView = new SideBarListViewGUI("Viestit");
        messageListView.getTitledPane().setOnMouseClicked((event) -> {
            if (messageListView.isExpanded()) {
                messageListView.setList(this.controller.getMessages());
            }
        });
        this.sideBar.addView(messageListView);
    }

    @Override
    public void setUserList() {
        SideBarListViewGUI<User> userListView = new SideBarListViewGUI("Käyttäjät");
        userListView.getTitledPane().setOnMouseClicked((event) -> {
            if (userListView.isExpanded()) {
                userListView.setList(this.controller.getUsers());
            }
        });
        this.sideBar.addView(userListView);
    }

    @Override
    public void setEmployeeList() {
        SideBarListViewGUI<String> employeeListView = new SideBarListViewGUI("Työntekijät");
        employeeListView.getTitledPane().setOnMouseClicked((event) -> {
            if (employeeListView.isExpanded()) {
                employeeListView.setList(this.controller.getEmployees());
            }
        });
        this.sideBar.addView(employeeListView);
    }

    @Override
    public void setDatabaseList() {
        SideBarListViewGUI<String> databaseListView = new SideBarListViewGUI("Tietokannat");
        databaseListView.getTitledPane().setOnMouseClicked((event) -> {
            if (databaseListView.isExpanded()) {
                databaseListView.setList(this.controller.getEmployees());
            }
        });
        this.sideBar.addView(databaseListView);
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
        ListTabGUI listTab = new ListTabGUI("Potilaan diagnoosit");
        listTab.getListView().setItems(diagnoses);
        this.tabPane.getTabs().add(listTab);
    }

    @Override
    public void setPatientPrescriptions(List<Prescription> list) {
        ObservableList<Prescription> prescriptions = FXCollections.observableArrayList(list);
        ListTabGUI listTab = new ListTabGUI("Potilaan reseptit");
        listTab.getListView().setItems(prescriptions);
        this.tabPane.getTabs().add(listTab);
    }
    
}

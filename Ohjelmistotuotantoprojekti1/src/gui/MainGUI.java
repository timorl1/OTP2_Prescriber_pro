package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;
import javafx.util.converter.DoubleStringConverter;
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
    
    private Controller controller;
    
    private DoubleStringConverter dsc;
    //Load login-component on initalization
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.controller = new Controller(this);
        this.dsc = new DoubleStringConverter();
        loadLogin();
    }
    
    //Creates a new instance of the LoginGUI and passes itself as a parameter
    //Adds the LoginGUI as a child-component to the MainGUI's anchor pane
    @Override
    public void loadLogin() {
        this.login = new LoginGUI();
        this.login.getButton().setOnAction((event) -> {
            this.controller.login(this.login.getUsername(), this.login.getPassword());
        });
        this.root.getChildren().add((LoginGUI)this.login);
    }
    
    //Removes the login component and loads the side bar component
    //Component type is defined by user's priviledges
    //1 - load nurses sidebar components
    //2 - load doctors sidebar components
    //3 - load administrators sidebar components
    //0 - should print out "access revoked" and instructions to contact the administrator
    @Override
    public void loadSideBar() {
        this.root.getChildren().remove((LoginGUI) this.login);
        this.sideBar = new SideBarGUI(this);
        this.root.getChildren().add((SideBarGUI) this.sideBar);
    }

    //Loads the tab pane component depending of the side bar content selection
    @Override
    public <T> void loadTabPane(T selection) {
        if (selection instanceof Patient) {
            Patient patient = this.controller.getBuiltPatient((Patient)selection);
            this.tabPane.getTabs().clear();
            ListTabGUI listTab = new ListTabGUI("Potilaan tiedot");
            ObservableList<String> list = FXCollections.observableArrayList();
            list.add("Etunimi: " + patient.getFirstName());
            list.add("Sukunimi: " + patient.getLastName());
            list.add("Sosiaaliturvatunnus: " + patient.getSSN());
            list.add("Sukupuoli: " + patient.getGender());
            list.add("Pituus: " + dsc.toString(patient.getHeight()) + " cm");
            list.add("Paino: " + dsc.toString(patient.getWeight()) + " kg");
            listTab.setList(list);
            this.tabPane.getTabs().add(listTab);
        }
        else if (selection instanceof Drug) {
            System.out.println("You selected drug " + selection);
        }
    }

    @Override
    public void loadPatientList() {
        SideBarListViewGUI<Patient> patientListView = new SideBarListViewGUI("Potilas");
        patientListView.getTitledPane().setOnMouseClicked((event) -> {
            if (patientListView.isExpanded()) {
                patientListView.setList(this.controller.getPatients());
            }
        });
        patientListView.getListView().setOnMouseClicked((event) -> {
            this.loadTabPane(patientListView.getSelection());
        });
        this.sideBar.addView(patientListView);
    }

    @Override
    public void loadDrugList() {
        SideBarListViewGUI<Drug> drugListView = new SideBarListViewGUI("Lääkkeet");
        drugListView.getTitledPane().setOnMouseClicked((event) -> {
            if (drugListView.isExpanded()) {
                drugListView.setList(this.controller.getDrugs());
            }
        });
        drugListView.getListView().setOnMouseClicked((event) -> {
            this.loadTabPane(drugListView.getSelection());
        });
        this.sideBar.addView(drugListView);
    }

    @Override
    public void loadPrescriptionList() {
        SideBarListViewGUI<Prescription> prescriptionListView = new SideBarListViewGUI("Omat Reseptit");
        prescriptionListView.getTitledPane().setOnMouseClicked((event) -> {
            if (prescriptionListView.isExpanded()) {
                prescriptionListView.setList(this.controller.getPrescriptions());
            }
        });
        this.sideBar.addView(prescriptionListView);
    }

    @Override
    public void loadMessageList() {
        SideBarListViewGUI<String> messageListView = new SideBarListViewGUI("Viestit");
        messageListView.getTitledPane().setOnMouseClicked((event) -> {
            if (messageListView.isExpanded()) {
                messageListView.setList(this.controller.getMessages());
            }
        });
        this.sideBar.addView(messageListView);
    }

    @Override
    public void loadUserList() {
        SideBarListViewGUI<User> userListView = new SideBarListViewGUI("Käyttäjät");
        userListView.getTitledPane().setOnMouseClicked((event) -> {
            if (userListView.isExpanded()) {
                userListView.setList(this.controller.getUsers());
            }
        });
        this.sideBar.addView(userListView);
    }

    @Override
    public void loadEmployeeList() {
        SideBarListViewGUI<String> employeeListView = new SideBarListViewGUI("Työntekijät");
        employeeListView.getTitledPane().setOnMouseClicked((event) -> {
            if (employeeListView.isExpanded()) {
                employeeListView.setList(this.controller.getEmployees());
            }
        });
        this.sideBar.addView(employeeListView);
    }

    @Override
    public void loadDatabaseList() {
        SideBarListViewGUI<String> databaseListView = new SideBarListViewGUI("Tietokannat");
        databaseListView.getTitledPane().setOnMouseClicked((event) -> {
            if (databaseListView.isExpanded()) {
                databaseListView.setList(this.controller.getEmployees());
            }
        });
        this.sideBar.addView(databaseListView);
    }
    
}

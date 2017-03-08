package gui;

import dao.DrugDAO;
import dao.PatientDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.converter.DoubleStringConverter;
import model.Allergen;
import model.Drug;
import model.Patient;
import model.Prescription;

/**
 * FXML Controller class
 *
 * @author joosiika
 */

//Tämä kontrolleri ei oikeastaan ole kontrolleri, vaan käsittelee käyttöliittymän logiikkaa
public class DemoViewController implements Initializable {

    @FXML
    private VBox sideBar;
    @FXML
    private TextField sideBarSearchField;
    @FXML
    private Label sideBarLabel1;
    @FXML
    private Label sideBarLabel2;
    @FXML
    private Label sideBarLabel3;
    @FXML
    private ListView<Patient> sideBarList1;
    @FXML
    private ListView<Drug> sideBarList2;
    @FXML
    private ListView<String> sideBarList3;
    @FXML
    private TabPane patientTabPane;
    @FXML
    private TabPane tabPane2;
    @FXML
    private ListView<String> tab1List;
    @FXML
    private ListView<String> tab1List2;
    @FXML
    private ListView<Allergen> tab2List2;
    
    private ObservableList<Patient> patients;
    private ObservableList<Drug> drugs;
    private ObservableList<String> messages;
    private final PatientDAO patientDB = new PatientDAO();
    private final DrugDAO drugDB = new DrugDAO();
    private final DoubleStringConverter dsConverter = new DoubleStringConverter();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Korvataan kontrollerin metodilla
        loadPatients(sideBarList1);
        //Korvataan kontrollerin metodilla
        loadDrugs(sideBarList2);
        loadMessages(sideBarList3);
        patientTabPane.setVisible(false);
        tabPane2.setVisible(false);
    }
    
    /*@FXML
    public void toggleVisible(MouseEvent event) {
        if (event.getSource() == sideBarLabel1) {
            sideBarList1.setVisible(!sideBarList1.isVisible());
            sideBarList2.setVisible(false);
            sideBarList3.setVisible(false);
            if (sideBarList1.isVisible()) {
                sideBarList1.setMaxHeight(sideBarList1.getItems().size()*24+24);
                sideBarList2.setMaxHeight(0);
                sideBarList3.setMaxHeight(0);
            }
            else {
                sideBarList1.setMaxHeight(0);
                patientTabPane.setVisible(false);
            }
        }
        if (event.getSource() == sideBarLabel2) {
            sideBarList2.setVisible(!sideBarList2.isVisible());
            sideBarList1.setVisible(false);
            sideBarList3.setVisible(false);
            if (sideBarList2.isVisible()) {
                sideBarList2.setMaxHeight(sideBarList2.getItems().size()*24+24);
                sideBarList1.setMaxHeight(0);
                sideBarList3.setMaxHeight(0);
            }
            else {
                sideBarList2.setMaxHeight(0);
            }
        }
        if (event.getSource() == sideBarLabel3) {
            sideBarList3.setVisible(!sideBarList3.isVisible());
            sideBarList1.setVisible(false);
            sideBarList2.setVisible(false);
            if (sideBarList3.isVisible()) {
                sideBarList3.setMaxHeight(sideBarList3.getItems().size()*24+24);
                sideBarList1.setMaxHeight(0);
                sideBarList2.setMaxHeight(0);
            }
            else {
                sideBarList3.setMaxHeight(0);
            }
        }
    }*/
    
    @FXML
    public void showPatientDetails(MouseEvent event) {
        ObservableList<String> list = FXCollections.observableArrayList();
        if (sideBarList1.getSelectionModel().getSelectedItem() != null) {
            Patient patient = sideBarList1.getSelectionModel().getSelectedItem();
            patientTabPane.setVisible(true);
            tabPane2.setVisible(false);
            list.add("Etunimi: " + patient.getFirstName());
            list.add("Sukunimi: " + patient.getLastName());
            list.add("Sosiaaliturvatunnus: " + patient.getSSN());
            list.add("Sukupuoli: " + patient.getGender());
            list.add("Pituus: " + dsConverter.toString(patient.getHeight()) + " cm");
            list.add("Paino: " + dsConverter.toString(patient.getHeight()) + " kg");
        }
        this.tab1List.setItems(list);
    }
    
    @FXML
    public void showDrugDetails(MouseEvent event) {
        ObservableList<String> list = FXCollections.observableArrayList();
        ObservableList<Allergen> list2 = FXCollections.observableArrayList();
        if (sideBarList2.getSelectionModel().getSelectedItem() != null) {
            Drug drug = sideBarList2.getSelectionModel().getSelectedItem();
            patientTabPane.setVisible(false);
            tabPane2.setVisible(true);
            list.add("Tuotenumero: " + drug.getSN());
            list.add("Nimi: " + drug.getName());
            list.add("Vaikuttavat aineet: " + drug.getDrugActiveAgents());
            list.add("Suositeltu annos: " + drug.getDrugActiveAgents() + drug.getUnit());
            list.add("Maksimiannos: " + drug.getDrugActiveAgents() + drug.getUnit());
            list2 = FXCollections.observableArrayList(drug.getAllergens());
        }
        this.tab1List2.setItems(list);
        this.tab2List2.setItems(list2);
        
    }
    
    @FXML
    public void filterPatients(KeyEvent event) {
        FilteredList<Patient> filteredPatients = new FilteredList(patients, p -> true);
        filteredPatients.setPredicate(patient -> {
            if (event.getText() == null) {
                return true;
            }
            String filter = event.getText();
            if (patient.getFirstName().toLowerCase().contains(filter)) {
                return true;
            }
            else if (patient.getLastName().toLowerCase().contains(filter)) {
                return true;
            }
            else if (patient.getSSN().toLowerCase().contains(filter)) {
                return true;
            }
            return false;
        });
    }

    //Korvataan kontrollerin metodilla
    public void loadPatients(ListView list) {
        this.patients = FXCollections.observableArrayList(patientDB.readPatients());
        list.setItems(this.patients);
    }

    //Korvataan kontrollerin metodilla
    public void loadDrugs(ListView list) {
        this.drugs = FXCollections.observableArrayList(drugDB.readDrugs());
        list.setItems(this.drugs);
    }
    
    public void loadMessages(ListView list) {
        List<String> messagesPlaceholder = new ArrayList();
        messagesPlaceholder.add("Sinulla ei ole uusia viestejä");
        this.messages = FXCollections.observableArrayList(messagesPlaceholder);
        list.setItems(this.messages);
    }
}

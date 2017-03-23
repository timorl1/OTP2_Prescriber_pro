package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
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
import model.Message;
import model.Patient;
import model.Prescription;
import model.User;
import model.User_IF;


/**
 * FXML Controller class
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class MainGUI implements Initializable, MainGUI_IF {
    
    @FXML
    private AnchorPane root;
    @FXML
    private TabPane tabPane;
    private SideBarGUI_IF sideBar;
    private LoginGUI_IF login;
    private PrescriptionFormGUI_IF prescriptionForm;
    private UserFormGUI_IF userForm;
    private MessageFormGUI_IF messageForm;
    private SideBarListView_IF<Patient> patientListView;
    private SideBarListView_IF<Drug> drugListView;
    private SideBarListView_IF<Prescription> prescriptionListView;
    private ListTabGUI_IF<String> prescriptionTab;
    private ListTabGUI_IF<String> drugTab;
    private ListTabGUI_IF<String> messageTab;
    private ListTabGUI_IF<Prescription> patientPrescriptionTab;
    private ListTabGUI_IF<String> diagnoseTab;
    private ListTabGUI_IF<Diagnose> patientDiagnoseTab;
    private SideBarListView_IF<Message> receivedMessageListView;
    private SideBarListView_IF<Message> sentMessageListView;    
    private SideBarListView_IF<User_IF> userListView;
    private SideBarListView_IF<Employee> employeeListView;
    private SideBarListView_IF<String> databaseListView;
    
    private Controller_IF controller;
    
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
        this.sideBar.getMessageButton().setOnMousePressed(m ->{
            this.controller.createNewMessage();
        });
        this.sideBar.getSearchField().setOnKeyReleased(e -> {
        if(this.patientListView != null){
            this.patientListView.filter(this.sideBar.getSearchField().getText());
        }
        if(this.drugListView != null){
            this.drugListView.filter(this.sideBar.getSearchField().getText());
        }
        if (this.employeeListView != null){
            this.employeeListView.filter(this.sideBar.getSearchField().getText());
        }    
        if (this.userListView != null){
        this.userListView.filter(this.sideBar.getSearchField().getText());
        }
        });
        this.root.getChildren().add((SideBarGUI) this.sideBar);
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
            if (this.patientListView.getSelection() != null && this.status == AppStatus.IDLE) {
                this.tabPane.getTabs().clear();
                this.setPatientDetails(this.getSelectedPatient());
                this.setPatientDiagnoses(this.controller.getPatientDiagnoses());
                this.setPatientPrescriptions(this.controller.getPatientPrescriptions());
            }
            else if (this.status == AppStatus.IDLE) {
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
                this.drugListView.setList(this.controller.getDrugs());
            }
        });
        this.drugListView.getListView().setOnMouseClicked(e -> {
            if (this.drugListView.getSelection() != null && this.status == AppStatus.IDLE) {
                this.tabPane.getTabs().clear();
                this.controller.getDrugDetails();
                //this.loadTabPane(drugListView.getSelection());
            }
            else if (this.status == AppStatus.IDLE) {
                this.tabPane.getTabs().clear();
            }
        });
        this.sideBar.addView((SideBarListViewGUI)this.drugListView);
    }

    @Override
    public void setPrescriptionList() {
        this.prescriptionListView = new SideBarListViewGUI("Omat Lääkemääräykset");
        this.prescriptionListView.getTitledPane().setOnMouseClicked((event) -> {
            if (this.prescriptionListView.isExpanded()) {
                this.prescriptionListView.setList(this.controller.getDoctorPrescriptions());
            }
        });
        this.prescriptionListView.getListView().setOnMouseClicked(e -> {
            if (this.prescriptionListView.getSelection() != null) {
                this.tabPane.getTabs().clear();
                this.controller.getPrescriptionDetails();
            }
            else {
                this.tabPane.getTabs().clear();
            }
        });
        this.sideBar.addView((SideBarListViewGUI)this.prescriptionListView);
    }

    @Override
    public void setReceivedMessageList() {
        this.receivedMessageListView = new SideBarListViewGUI("Vastaanotetut Viestit");
        this.receivedMessageListView.getTitledPane().setOnMouseClicked((event) -> {
            if (this.receivedMessageListView.isExpanded()) {
                this.receivedMessageListView.setList(this.controller.getReceivedMessages());
            }
        });
        this.receivedMessageListView.getListView().setOnMouseClicked(e -> {
            if (this.receivedMessageListView.getSelection() != null && this.status == AppStatus.IDLE) {
                this.tabPane.getTabs().clear();
                this.setMessageDetails(this.receivedMessageListView.getSelection());
            }
            else if (this.status == AppStatus.IDLE) {
                this.tabPane.getTabs().clear();
            }
        });
        this.sideBar.addView((SideBarListViewGUI)this.receivedMessageListView);
    }
    
    @Override
    public void setSentMessageList() {
        this.sentMessageListView = new SideBarListViewGUI("Lähetetyt Viestit");
        this.sentMessageListView.getTitledPane().setOnMouseClicked((event) -> {
            if (this.sentMessageListView.isExpanded()) {
                this.sentMessageListView.setList(this.controller.getSentMessages());
            }
        });
        this.sentMessageListView.getListView().setOnMouseClicked(e -> {
            if (this.sentMessageListView.getSelection() != null && this.status == AppStatus.IDLE) {
                this.tabPane.getTabs().clear();
                this.setMessageDetails(this.sentMessageListView.getSelection());
            }
            else if (this.status == AppStatus.IDLE) {
                this.tabPane.getTabs().clear();
            }
        });
        this.sideBar.addView((SideBarListViewGUI)this.sentMessageListView);
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
                        if (u.getUsertype() != 0) {
                            button.setText("-");
                        }
                        else {
                            button.setText("+");
                        }
                        button.setOnAction((ActionEvent event) -> {
                            if (u.getUsertype() != 0) {
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
            if (this.employeeListView.getSelection() != null && this.status == AppStatus.IDLE) {
                this.tabPane.getTabs().clear();
                this.controller.getEmployeeDetails();
            }
            else if(this.status == AppStatus.IDLE) {
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
        this.prescriptionForm = new PrescriptionFormGUI(this.patientListView, this.drugListView, "Lääkemääräys", prescription);
        this.prescriptionForm.getCancelButton().setOnAction(e -> {
            this.tabPane.getTabs().remove(this.prescriptionForm);
            this.setStatus(AppStatus.IDLE);
        });
        this.prescriptionForm.getSaveButton().setOnAction(e -> {
            if (this.status == AppStatus.EDIT) {
                this.prescriptionForm.markUpdate();
            }
            if (this.controller.savePrescription()) {
                this.tabPane.getTabs().remove(this.prescriptionForm);
                this.setStatus(AppStatus.IDLE);
            }
            else {
                //Some kind of alert message should be thrown
            }
        });
        this.prescriptionForm.getPatientField().textProperty().addListener((observable, oldValue, newValue) -> {
            if (this.getPrescriptionForm().getPatient() != null) {
                ObservableList<Diagnose> list = FXCollections.observableList(this.controller.getPatientDiagnoses());
                this.prescriptionForm.getDiagnoseSelector().setItems(list);
                this.prescriptionForm.getDiagnoseSelector().getSelectionModel().clearAndSelect(0);
                this.prescriptionForm.setDiagnose(this.prescriptionForm.getDiagnoseSelector().getSelectionModel().getSelectedItem());
            }
        });
        if (this.getSelectedPatient() != null) {
            ObservableList<Diagnose> list = FXCollections.observableList(this.controller.getPatientDiagnoses());
            this.prescriptionForm.getDiagnoseSelector().setItems(list);
            this.prescriptionForm.getDiagnoseSelector().getSelectionModel().clearAndSelect(0);
        }
        this.tabPane.getTabs().add((PrescriptionFormGUI)this.prescriptionForm);
    }
    
    @Override
    public void setMessageForm(Message message){
        this.status = AppStatus.CREATE;
        this.messageForm = new MessageFormGUI(this.controller.getUsers(), message, "Uusi viesti");
        this.messageForm.getCancelButton().setOnAction(e -> {
            this.tabPane.getTabs().remove(this.messageForm);
            this.setStatus(AppStatus.IDLE);
        });
        this.messageForm.getSendButton().setOnAction(e -> {
            if (this.controller.saveMessage()) {
                this.tabPane.getTabs().remove(this.messageForm);
                this.setStatus(AppStatus.IDLE);
            }
            else {
                //Some kind of alert message should be thrown
            }
        });
        this.tabPane.getTabs().add((MessageFormGUI)this.messageForm);
    }
    
    @Override
    public void setUserForm(User_IF user) {
        this.status = AppStatus.CREATE;
        this.userForm = new UserFormGUI(this.employeeListView, user, "Uusi käyttäjä");
        this.userForm.getCancelButton().setOnAction(e -> {
            this.tabPane.getTabs().remove(this.userForm);
            this.setStatus(AppStatus.IDLE);
        });
        this.userForm.getSaveButton().setOnAction(e -> {
           
            if (this.controller.saveUser()) {
                this.tabPane.getTabs().remove(this.userForm);
                this.setStatus(AppStatus.IDLE);
            }
            else {
                //Some kind of alert message should be thrown
            }
        });
      
        this.tabPane.getTabs().add((UserFormGUI)this.userForm);
    }
    
    
    
    @Override
    public void setPrescriptionTools() {
        Button createPrescription = new Button("Uusi lääkemääräys");
        createPrescription.setOnMouseClicked((event) -> {
            this.controller.createNewPrescription();
            this.setStatus(AppStatus.CREATE);
        });
        this.sideBar.getVbox().getChildren().add(createPrescription);
        Button updatePrescription = new Button("Muokkaa lääkemääräystä");
        BooleanBinding booleanBind = Bindings.isNull(this.prescriptionListView.getListView().getSelectionModel().selectedItemProperty());
        updatePrescription.disableProperty().bind(booleanBind);
        updatePrescription.setOnMouseClicked((event) -> {
            this.setPrescriptionForm(this.prescriptionListView.getSelection());
            this.setStatus(AppStatus.EDIT);
        });
        this.sideBar.getVbox().getChildren().add(updatePrescription);
    }

     @Override
    public void setUserTools() {
        Button createUser = new Button("Uusi käyttäjä");
        createUser.setOnMouseClicked((event) -> {
            this.controller.createNewUser();
            this.setStatus(AppStatus.CREATE);
        });
        this.sideBar.getVbox().getChildren().add(createUser);

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
    public void setDrugDetails(Drug drug) {
        String activeAgents = "";
        String allergens = "";
        String commonAdverseEffects = "";
        String rareAdverseEffects = "";
        
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("Tuotenumero: " + drug.getSN());
        list.add("Lääke: " + drug.getName());
        list.add("Vaikuttavat aineet:\n\n\t"+drug.getDrugActiveAgents().stream().
                map((a) -> a.getActiveAgent().getName()+" "+a.getConcentration()+"mg\n\t").reduce(activeAgents, String::concat));
        list.add("Lääkkeen allergeenit:\n\n\t"+drug.getAllergens().stream().
                map((a) -> a.getName()+"\n\t").reduce(allergens, String::concat));
        list.add("Yleiset haittavaikutukset:\n\n\t"+drug.getCommonAdverseEffects().stream().
                map((a) -> a.getName()+"\n\t").reduce(commonAdverseEffects, String::concat));
        list.add("Harvinaiset haittavaikutukset:\n\n\t"+drug.getRareAdverseEffects().stream().
                map((a) -> a.getName()+"\n\t").reduce(rareAdverseEffects, String::concat));
        
        this.tabPane.getTabs().remove(this.drugTab);
        this.drugTab = new ListTabGUI("Lääkkeen tiedot");
        this.drugTab.getListView().setItems(list);
        this.tabPane.getTabs().add((ListTabGUI)this.drugTab);
        this.tabPane.getSelectionModel().select((ListTabGUI)this.drugTab);
    }
    
    @Override
    public void setMessageDetails(Message message) {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("Lähettäjä: "+message.getSender().getFirstName()+" "+message.getSender().getLastName());
        list.add("Vastaanottaja: "+message.getReceiver().getFirstName()+" "+message.getReceiver().getLastName());
        list.add("Päiväys: "+message.getDate());
        list.add("Viesti: "+message.getMessage() );
        
        this.tabPane.getTabs().remove(this.messageTab);
        this.messageTab = new ListTabGUI(message.getTitle());
        this.messageTab.getListView().setItems(list);
        this.tabPane.getTabs().add((ListTabGUI)this.messageTab);
        this.tabPane.getSelectionModel().select((ListTabGUI)this.messageTab);
    }
    
    @Override
    public void setUserDetails(User_IF user) {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("Työntekijänumero: " + user.getUserID());
        list.add("Käyttäjätunnus: " + user.getUsername());
        list.add("Sähköposti: " + user.getEmail());
        switch (user.getUsertype()) {
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
    public Patient getSelectedPatient() {
        return this.patientListView.getSelection();
    }

    @Override
    public Prescription getSelectedPrescription() {
        //Fix this! If patient tab was selected and then trying to view doctor's
        //prescription, this method returns null.
        if(this.patientPrescriptionTab != null){
            return this.patientPrescriptionTab.getSelection();
        }else{
            return this.prescriptionListView.getSelection();
        }
    }
    
    @Override
    public Diagnose getSelectedDiagnose() {
        return this.patientDiagnoseTab.getSelection();
    }
    
    @Override
    public Drug getSelectedDrug() {
        return this.drugListView.getSelection();
    }
    
    @Override
    public Message getSelectedMessage() {
        return this.receivedMessageListView.getSelection();
    }
    
    @Override
    public User_IF getSelectedUser() {
        return this.userListView.getSelection();
    }
    
    @Override
    public Employee getSelectedEmployee() {
        return this.employeeListView.getSelection();
    }

    @Override
    public Prescription getPrescriptionForm() {
        return this.prescriptionForm.getPrescription();
    }

    @Override
    public Message getMessageForm() {
        return this.messageForm.getMessage();
    }

    
    
    @Override
    public User_IF getUserForm() {
        return this.userForm.getUser();
    
    }
}

package gui;

import resources.ListTabGUI_IF;
import resources.ListTabGUI;
import appuser.LoginGUI_IF;
import appuser.LoginGUI;
import resources.message.MessageFormGUI;
import resources.message.MessageFormGUI_IF;
import resources.user.UserFormGUI;
import resources.user.UserFormGUI_IF;
import resources.prescription.PrescriptionFormGUI_IF;
import resources.prescription.PrescriptionFormGUI;
import resources.SideBarListView_IF;
import resources.SideBarListViewGUI;
import static gui.Localisation.getInstance;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.util.converter.DoubleStringConverter;
import resources.patient.PatientListCell;
import resources.diagnose.Diagnose;
import resources.drug.Drug;
import resources.drug.DrugListCell;
import resources.employee.Employee;
import resources.employee.EmployeeListCell;
import resources.message.Message;
import resources.message.ReceivedMessageListCell;
import resources.message.SentMessageListCell;
import resources.patient.Patient;
import resources.prescription.Prescription;
import resources.prescription.PrescriptionListCell;
import resources.user.User;
import resources.user.User_IF;


/**
 * FXML Mediator class
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class MainGUI extends Parent implements Initializable, MainGUI_IF {
    ResourceBundle text;
    Localisation local = getInstance();
    private AlertMessage alertMessage = AlertMessage.getINSTANCE();
    
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
    @FXML
    private VBox vBox;
    @FXML
    private VBox menuButtons;
    @FXML
    private Accordion accordion;
    @FXML
    private VBox controls;
    private TextField searchField;
    
    private Mediator_IF mediator;
    
    private AppStatus status;
    
    private DoubleStringConverter dsc;
    
    //Load login-component on initalization
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.text = rb;
        this.mediator = new Mediator(this);
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
            this.login.addMessage(null);
            this.mediator.login(this.login.getUsername(), this.login.getPassword());
            this.login.clearPasswordField();
        });
        this.root.getChildren().add((LoginGUI)this.login);
    }
    
    //Set login failed message
    @Override
    public void setLoginFailed() {
        text = local.language();
        this.login.addMessage(text.getString("loginFail"));
    }
    
    //Set access denied message
    @Override
    public void setAccessDenied() {
        text = local.language();
        this.login.addMessage(text.getString("accessDenied"));
    }
    
    @Override
    public void setLogout(){
        //this.root.getChildren().remove((SideBarGUI)this.sideBar);
        this.tabPane.getTabs().clear();
        this.accordion.getPanes().clear();
        this.menuButtons.getChildren().clear();
        this.controls.getChildren().clear();
        this.root.getChildren().add((LoginGUI)this.login);
    }
    
    @Override
    public void setSideBar() {
        text = local.language();
        this.root.getChildren().remove((LoginGUI) this.login);
        this.sideBar = new SideBarGUI(this);
        /*this.sideBar.getSearchField().setOnKeyReleased(e -> {
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
        });*/
        this.root.getChildren().add((SideBarGUI) this.sideBar);
    }

    @Override
    public void setPatientList() {
        text = local.language();
        this.patientListView = new SideBarListViewGUI(text.getString("patients"));
        this.patientListView.getTitledPane().expandedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) {
                if (newValue != null && !newValue.equals(oldValue)) {
                    patientListView.setList(mediator.getPatients());
                    patientListView.getListView().setCellFactory(listView -> new PatientListCell(text));
                }
            }
        });
        this.patientListView.getListView().setOnMouseClicked(e -> {
            if (this.patientListView.getSelection() != null && this.status == AppStatus.IDLE) {
                this.tabPane.getTabs().clear();
                this.setPatientDetails(this.getSelectedPatient());
                this.setPatientDiagnoses(this.mediator.getPatientDiagnoses());
                this.setPatientPrescriptions(this.mediator.getPatientPrescriptions());
            }
            else if (this.status == AppStatus.IDLE) {
                this.tabPane.getTabs().clear();
            }
        });
        //this.sideBar.addView((SideBarListViewGUI)this.patientListView);
        this.accordion.getPanes().add((SideBarListViewGUI)this.patientListView);
    }

    @Override
    public void setDrugList() {
        text = local.language();
        this.drugListView = new SideBarListViewGUI(text.getString("drugs"));
        /*this.drugListView.getTitledPane().setOnMouseClicked(e -> {
            if (this.drugListView.isExpanded()) {
                this.drugListView.setList(this.mediator.getDrugs());
                this.drugListView.getListView().setCellFactory(listView -> new DrugListCell(text));
            }
        });*/
        this.drugListView.getTitledPane().expandedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) {
                if (newValue != null && !newValue.equals(oldValue)) {
                    drugListView.setList(mediator.getDrugs());
                    drugListView.getListView().setCellFactory(listView -> new DrugListCell(text));
                }
            }
        });
        this.drugListView.getListView().setOnMouseClicked(e -> {
            if (this.drugListView.getSelection() != null && this.status == AppStatus.IDLE) {
                this.tabPane.getTabs().clear();
                this.mediator.getDrugDetails();
                //this.loadTabPane(drugListView.getSelection());
            }
            else if (this.status == AppStatus.IDLE) {
                this.tabPane.getTabs().clear();
            }
        });
        //this.sideBar.addView((SideBarListViewGUI)this.drugListView);
        this.accordion.getPanes().add((SideBarListViewGUI)this.drugListView);
    }

    @Override
    public void setPrescriptionList() {
        text = local.language();
        this.prescriptionListView = new SideBarListViewGUI(text.getString("ownPrescriptions"));
        this.prescriptionListView.getTitledPane().setOnMouseClicked((event) -> {
            if (this.prescriptionListView.isExpanded()) {
                this.prescriptionListView.setList(this.mediator.getDoctorPrescriptions());
                this.prescriptionListView.getListView().setCellFactory(listView -> new PrescriptionListCell(text));
            }
        });
        this.prescriptionListView.getListView().setOnMouseClicked(e -> {
            if (this.prescriptionListView.getSelection() != null) {
                this.tabPane.getTabs().clear();
                this.mediator.getPrescriptionDetails();
            }
            else {
                this.tabPane.getTabs().clear();
            }
        });
        //this.sideBar.addView((SideBarListViewGUI)this.prescriptionListView);
        this.accordion.getPanes().add((SideBarListViewGUI)this.prescriptionListView);
    }

    @Override
    public void setReceivedMessageList() {
        text = local.language();
        this.receivedMessageListView = new SideBarListViewGUI(text.getString("receivedMessages"));
        this.receivedMessageListView.getTitledPane().setOnMouseClicked((event) -> {
            if (this.receivedMessageListView.isExpanded()) {
                this.receivedMessageListView.setList(this.mediator.getReceivedMessages());
                this.receivedMessageListView.getListView().setCellFactory(listView -> new ReceivedMessageListCell(text));
            }
        });
        this.receivedMessageListView.getListView().setOnMouseClicked(e -> {
            if (this.receivedMessageListView.getSelection() != null && this.status == AppStatus.IDLE) {
                this.tabPane.getTabs().clear();
                this.mediator.getReceivedMessageDetails();
            }
            else if (this.status == AppStatus.IDLE) {
                this.tabPane.getTabs().clear();
            }
        });
        //this.sideBar.addView((SideBarListViewGUI)this.receivedMessageListView);
        this.accordion.getPanes().add((SideBarListViewGUI)this.receivedMessageListView);
    }
    
    @Override
    public void setSentMessageList() {
        text = local.language();
        this.sentMessageListView = new SideBarListViewGUI(text.getString("sentMessages"));
        this.sentMessageListView.getTitledPane().setOnMouseClicked((event) -> {
            if (this.sentMessageListView.isExpanded()) {
                this.sentMessageListView.setList(this.mediator.getSentMessages());
                this.sentMessageListView.getListView().setCellFactory(listView -> new SentMessageListCell(text));
            }
        });
        this.sentMessageListView.getListView().setOnMouseClicked(e -> {
            if (this.sentMessageListView.getSelection() != null && this.status == AppStatus.IDLE) {
                this.tabPane.getTabs().clear();
                this.mediator.getSentMessageDetails();
            }
            else if (this.status == AppStatus.IDLE) {
                this.tabPane.getTabs().clear();
            }
        });
        //this.sideBar.addView((SideBarListViewGUI)this.sentMessageListView);
        this.accordion.getPanes().add((SideBarListViewGUI)this.sentMessageListView);
    }

    //List all users
    @Override
    public void setUserList() {
        text = local.language();
        this.userListView = new SideBarListViewGUI(text.getString("users"));
        //Move this to a separate UserListCell-class
        this.userListView.getListView().setCellFactory(listView -> {
            ListCell<User> cell = new ListCell<User>(){
                @Override
                protected void updateItem(User user, boolean bln) {
                    super.updateItem(user, bln);
                    Image locked = new Image(getClass().getResourceAsStream("/gui/img/padlock_closed.png"));
                    Image open = new Image(getClass().getResourceAsStream("/gui/img/padlock_open.png"));
                    if (user != null) {
                        setText(user.toString());
                        Button button = new Button();
                        
                        if (user.getUsertype() != 0) {
                            button.setGraphic(new ImageView(open));
                            button.setTooltip(new Tooltip(text.getString("lockUser")));
                        }
                        else {
                            button.setGraphic(new ImageView(locked));
                            button.setTooltip(new Tooltip(text.getString("unlockUser")));
                        }
                        button.setOnAction(e -> {
                            if (user.getUsertype() != 0) {
                               Optional<ButtonType> result = alertMessage.showConfirmationAlert(text.getString("lockUser"), text.getString("alertHeaderLockUser"),
                                        text.getString("alertContentTextLockUser"));
                                if (result.get() == ButtonType.OK){
                                    mediator.lockUser(user);
                                    button.setGraphic(new ImageView(locked));
                                    button.setTooltip(new Tooltip(text.getString("unlockUser")));                                
                                }      
                                
                            } else {
                                Optional<ButtonType> result = alertMessage.showConfirmationAlert(text.getString("unlockUser"), text.getString("alertHeaderUnlockUser"),
                                        text.getString("alertContentTextUnlockUser"));
                                if (result.get() == ButtonType.OK){
                                    mediator.setUserPriviledges(user);
                                    button.setGraphic(new ImageView(open));
                                    button.setTooltip(new Tooltip(text.getString("lockUser")));
                                }
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
                this.userListView.setList(this.mediator.getUsers());
            }
        });
        this.userListView.getListView().setOnMouseClicked(e -> {
            if (this.userListView.getSelection() != null) {
                this.tabPane.getTabs().clear();
                this.mediator.getUserDetails();
            }
            else {
                this.tabPane.getTabs().clear();
            }
        });
        //this.sideBar.addView((SideBarListViewGUI)this.userListView);
        this.accordion.getPanes().add((SideBarListViewGUI)this.userListView);
    }

    @Override
    public void setEmployeeList() {
        text = local.language();
        this.employeeListView = new SideBarListViewGUI(text.getString("employees"));
        /*this.employeeListView.getTitledPane().setOnMouseClicked((event) -> {
            if (this.employeeListView.isExpanded()) {
                this.employeeListView.setList(this.mediator.getEmployees());
                this.employeeListView.getListView().setCellFactory(listView -> new EmployeeListCell(text));
            }
        });*/
        this.employeeListView.getTitledPane().expandedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) {
                if (newValue != null && !newValue.equals(oldValue)) {
                    employeeListView.setList(mediator.getEmployees());
                    employeeListView.getListView().setCellFactory(listView -> new EmployeeListCell(text));
                }
            }
        });
        this.employeeListView.getListView().setOnMouseClicked(e -> {
            if (this.employeeListView.getSelection() != null && this.status == AppStatus.IDLE) {
                this.tabPane.getTabs().clear();
                this.mediator.getEmployeeDetails();
            }
            else if(this.status == AppStatus.IDLE) {
                this.tabPane.getTabs().clear();
            }
        });
        //this.sideBar.addView((SideBarListViewGUI)this.employeeListView);
        this.accordion.getPanes().add((SideBarListViewGUI)this.employeeListView);
    }

    @Override
    public void setDatabaseList() {
        text = local.language();
        this.databaseListView = new SideBarListViewGUI(text.getString("databases"));
        this.databaseListView.getTitledPane().setOnMouseClicked((event) -> {
            if (this.databaseListView.isExpanded()) {
                this.databaseListView.setList(this.mediator.getDatabases());
            }
        });
        //this.sideBar.addView((SideBarListViewGUI)this.databaseListView);
        this.accordion.getPanes().add((SideBarListViewGUI)this.databaseListView);
    }
    
    @Override
    public void setPrescriptionForm(Prescription prescription) {
        if(!this.tabPane.getTabs().contains(this.prescriptionForm)){
            this.prescriptionForm = new PrescriptionFormGUI(this.mediator, this.text, this.patientListView, this.drugListView, prescription);
            this.tabPane.getTabs().add((PrescriptionFormGUI)this.prescriptionForm);
            this.tabPane.getTabs().addListener(new ListChangeListener() {
                @Override
                public void onChanged(ListChangeListener.Change c) {
                    if (!c.getList().contains(prescriptionForm)) {
                        status = AppStatus.IDLE;
                        tabPane.getTabs().removeListener(this);
                        prescriptionForm = null;
                    }
                }
                
            });
        }
    }
    
    @Override
    public void setMessageForm(Message message){
        text = local.language();
        this.status = AppStatus.CREATE;
        if(!this.tabPane.getTabs().contains(this.messageForm)){
            this.messageForm = new MessageFormGUI(this.mediator.getUsers(), message, text.getString("newMessage"));

            this.messageForm.getCancelButton().setOnAction(e -> {
                this.tabPane.getTabs().remove(this.messageForm);
                this.setStatus(AppStatus.IDLE);
            });
            this.messageForm.getSendButton().setOnAction(e -> {
                if (this.mediator.saveMessage()) {
                    alertMessage.showInformationAlert(text.getString("message"), text.getString("messageSent"));
                    this.tabPane.getTabs().remove(this.messageForm);
                    this.setStatus(AppStatus.IDLE);
                }
                else {
                    alertMessage.showWarningAlert(text.getString("message"), text.getString("alertTextWarning"),
                            text.getString("alertTitleMessageNotSent"));
                }
            });
            this.tabPane.getTabs().add((MessageFormGUI)this.messageForm);

        }
    }
    @Override
    public void setUserForm(User_IF user) {
        text = local.language();
        this.status = AppStatus.CREATE;
        if(!this.tabPane.getTabs().contains(this.userForm)){
            this.userForm = new UserFormGUI(this.employeeListView, user, text.getString("newUser"));
            this.employeeListView.getTitledPane().setExpanded(true);
            if (this.employeeListView.isExpanded()) {
                this.employeeListView.setList(this.mediator.getEmployees());
                this.employeeListView.getListView().setCellFactory(listView -> new EmployeeListCell(text));
            }
            this.userForm.getCancelButton().setOnAction(e -> {
                this.tabPane.getTabs().remove(this.userForm);
                this.setStatus(AppStatus.IDLE);
            });
            this.userForm.getSaveButton().setOnAction(e -> {           
                if (this.mediator.saveUser()) {
                    alertMessage.showInformationAlert(text.getString("newUser"), text.getString("userAdded"));
                    this.tabPane.getTabs().remove(this.userForm);
                    this.setStatus(AppStatus.IDLE);
                }
                else {
                    alertMessage.showWarningAlert(text.getString("newUser"), text.getString("alertTextWarning"),
                            text.getString("alertTitleUserNotAdded"));
                }
            });

            this.tabPane.getTabs().add((UserFormGUI)this.userForm);
        }    
    }
    
    
    
    @Override
    public void setPrescriptionTools() {
        text = local.language();
        Button createPrescription = new Button(text.getString("newPrescription"));
        createPrescription.setOnMouseClicked((event) -> {
            this.mediator.createPrescription();
            this.setStatus(AppStatus.CREATE);
        });
        //this.sideBar.getButtonBox().getChildren().add(createPrescription);
        this.menuButtons.getChildren().add(createPrescription);
        Button updatePrescription = new Button(text.getString("updatePrescription"));
        BooleanBinding booleanBind = Bindings.isNull(this.prescriptionListView.getListView().getSelectionModel().selectedItemProperty());
        updatePrescription.disableProperty().bind(booleanBind);
        updatePrescription.setOnMouseClicked((event) -> {
            this.mediator.editPrescription(this.prescriptionListView.getSelection());
            this.setStatus(AppStatus.EDIT);
        });
        //this.sideBar.getButtonBox().getChildren().add(updatePrescription);
        this.menuButtons.getChildren().add(updatePrescription);
    }

    @Override
    public void setUserTools() {
        text = local.language();
        Button createUser = new Button(text.getString("newUser"));
        createUser.setOnMouseClicked((event) -> {
            this.mediator.createNewUser();
            this.setStatus(AppStatus.CREATE);
        });
        //this.sideBar.getButtonBox().getChildren().add(createUser);
        this.menuButtons.getChildren().add(createUser);

    }
    
    @Override
    public void setBasicTools() {
        this.searchField = new TextField();
        this.searchField.setPromptText(text.getString("search"));
        this.controls.getChildren().add(this.searchField);
        Button newMessageButton = new Button(text.getString("newMessage"));
        newMessageButton.setOnAction(b -> this.mediator.createNewMessage());
        //this.sideBar.getButtonBox().getChildren().add(newMessageButton);
        this.menuButtons.getChildren().add(newMessageButton);
        Button logoutButton = new Button(text.getString("logout"));
        logoutButton.setOnAction(b -> this.mediator.logout());
        //this.sideBar.getButtonBox().getChildren().add(logoutButton);
        this.menuButtons.getChildren().add(logoutButton);
    }
    @Override
    public void setPatientDetails(Patient patient) {
        text = local.language();
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add(text.getString("socialSecurityNumber")+": " + patient.getSSN());
        list.add(text.getString("firstname")+": " + patient.getFirstName());
        list.add(text.getString("lastname")+": " + patient.getLastName());
        list.add(text.getString("gender")+": " + patient.getGender());
        list.add(text.getString("height")+": " + dsc.toString(patient.getHeight()) + text.getString("heightUnit"));
        list.add(text.getString("weight")+": " + dsc.toString(patient.getWeight()) + text.getString("weightUnit"));
        ListTabGUI listTab = new ListTabGUI(text.getString("patientInfo"));
        listTab.getListView().setItems(list);
        this.tabPane.getTabs().add(listTab);
        
    }
    
    @Override
    public void setPatientDiagnoses(List<Diagnose> list) {
        text = local.language();
        ObservableList<Diagnose> diagnoses = FXCollections.observableArrayList(list);
        this.patientDiagnoseTab = new ListTabGUI(text.getString("patientsDiagnoses"));
        this.patientDiagnoseTab.getListView().setOnMouseClicked(e -> {
            this.mediator.getDiagnoseDetails();
        });
        this.patientDiagnoseTab.getListView().setItems(diagnoses);
        this.tabPane.getTabs().add((ListTabGUI)this.patientDiagnoseTab);
    }

    @Override
    public void setPatientPrescriptions(List<Prescription> list) {
        text = local.language();
        ObservableList<Prescription> prescriptions = FXCollections.observableArrayList(list);
        this.patientPrescriptionTab = new ListTabGUI(text.getString("patientsPrescriptions"));
        this.patientPrescriptionTab.getListView().setOnMouseClicked(e -> {
            this.mediator.getPrescriptionDetails();
        });
        this.patientPrescriptionTab.getListView().setItems(prescriptions);
        this.tabPane.getTabs().add((ListTabGUI)this.patientPrescriptionTab);
    }

    @Override
    public void setPrescriptionDetails(Prescription prescription) {
        text = local.language();
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add(text.getString("ID")+": " + prescription.getId());
        list.add(text.getString("creationDate")+": " + prescription.getCreationDate());
        list.add(text.getString("patient")+": " + prescription.getPatient().getLastName() + ", " + prescription.getPatient().getFirstName() + ", " + prescription.getPatient().getSSN());
        list.add(text.getString("doctor")+": " + prescription.getDoctor().getLastName() + ", " + prescription.getDoctor().getFirstName());
        list.add(text.getString("diagnose")+": " + prescription.getDiagnose().getId() + ": " + prescription.getDiagnose().getDisease());
        list.add(text.getString("drug")+": " + prescription.getDrug().getName());
        list.add(text.getString("dose")+": " + prescription.getDrug() + ", " + prescription.getDose() + " " + prescription.getDrug().getUnit() + " " + prescription.getTimesADay() + " kertaa päivässä.");
        list.add(text.getString("prescriptionInfo")+": " + prescription.getInfo());
        list.add(text.getString("startDate")+": " + prescription.getStartDate());
        list.add(text.getString("endDate")+": " + prescription.getEndDate());
        this.tabPane.getTabs().remove(this.prescriptionTab);
        this.prescriptionTab = new ListTabGUI(text.getString("prescriptionInfo"));
        this.prescriptionTab.getListView().setItems(list);
        this.tabPane.getTabs().add((ListTabGUI)this.prescriptionTab);
        this.tabPane.getSelectionModel().select((ListTabGUI)this.prescriptionTab);
    }
    
    @Override
    public void setDiagnoseDetails(Diagnose diagnose) {
        text = local.language();
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add(text.getString("ID")+": " + diagnose.getId());
        list.add(text.getString("creationDate") +": "+ diagnose.getCreationDate());
        list.add(text.getString("patient") +": "+ diagnose.getPatient().getLastName() + ", " + diagnose.getPatient().getFirstName() + ", " + diagnose.getPatient().getSSN());
        list.add(text.getString("doctor")+": " + diagnose.getDoctor().getLastName() + ", " + diagnose.getDoctor().getFirstName());
        list.add(text.getString("disease")+": " + diagnose.getDisease());
        list.add(text.getString("epicrisis")+": " + diagnose.getEpicrisis());
        if (diagnose.getResolutionDate() != null) {
            list.add(text.getString("diagnoseStateDone")+" " + diagnose.getResolutionDate());
        }
        else {
            list.add(text.getString("diagnoseStateNotDone") );
        }
        this.tabPane.getTabs().remove(this.diagnoseTab);
        this.diagnoseTab = new ListTabGUI(text.getString("diagnoseInfo"));
        this.diagnoseTab.getListView().setItems(list);
        this.tabPane.getTabs().add((ListTabGUI)this.diagnoseTab);
        this.tabPane.getSelectionModel().select((ListTabGUI)this.diagnoseTab);
    }
    
    @Override
    public void setDrugDetails(Drug drug) {
        text = local.language();
        String activeAgents = "";
        String allergens = "";
        String commonAdverseEffects = "";
        String rareAdverseEffects = "";
        
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add(text.getString("productNumber")+": " + drug.getSN());
        list.add(text.getString("drug")+": " + drug.getName());
        list.add(text.getString("activeAgents")+":\n\n\t"+drug.getDrugActiveAgents().stream().
                map((a) -> a.getActiveAgent().getName()+" "+a.getConcentration()+"mg\n\t").reduce(activeAgents, String::concat));
        list.add(text.getString("drugAllergens")+":\n\n\t"+drug.getAllergens().stream().
                map((a) -> a.getName()+"\n\t").reduce(allergens, String::concat));
        list.add(text.getString("commonAdverseEffects")+":\n\n\t"+drug.getCommonAdverseEffects().stream().
                map((a) -> a.getName()+"\n\t").reduce(commonAdverseEffects, String::concat));
        list.add(text.getString("rareAdverseEffects")+":\n\n\t"+drug.getRareAdverseEffects().stream().
                map((a) -> a.getName()+"\n\t").reduce(rareAdverseEffects, String::concat));
        
        this.tabPane.getTabs().remove(this.drugTab);
        this.drugTab = new ListTabGUI(text.getString("drugInfo"));
        this.drugTab.getListView().setItems(list);
        this.tabPane.getTabs().add((ListTabGUI)this.drugTab);
        this.tabPane.getSelectionModel().select((ListTabGUI)this.drugTab);
    }
    
    @Override
    public void setMessageDetails(Message message) {
        text = local.language();
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add(text.getString("sender")+": "+message.getSender().getFirstName()+" "+message.getSender().getLastName());
        list.add(text.getString("receiver")+": "+message.getReceiver().getFirstName()+" "+message.getReceiver().getLastName());
        list.add(text.getString("date")+": "+message.getDate());
        list.add(text.getString("subject")+": "+message.getTitle() );
        list.add(text.getString("message")+": "+message.getMessage() );
        
        this.tabPane.getTabs().remove(this.messageTab);
        this.messageTab = new ListTabGUI(message.getTitle());
        this.messageTab.getListView().setItems(list);
        this.tabPane.getTabs().add((ListTabGUI)this.messageTab);
        this.tabPane.getSelectionModel().select((ListTabGUI)this.messageTab);
    }
    
    @Override
    public void setUserDetails(User_IF user) {
        text = local.language();
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add(text.getString("userNumber") +": "+ user.getUserID());
        list.add(text.getString("username") +": "+ user.getUsername());
        list.add(text.getString("email")+": " + user.getEmail());
        switch (user.getUsertype()) {
            case 0:
                list.add(text.getString("privilegesLocked"));
                break;
            case 1:
                list.add(text.getString("privilegesNurse"));
                break;
            case 2:
                list.add(text.getString("privilegesDoctor"));
                break;
            case 3:
                list.add(text.getString("privilegesAdmin"));
                break;
            default:
                break;
        }
        this.tabPane.getTabs().clear();
        ListTabGUI_IF listTab = new ListTabGUI(text.getString("userInfoTab"));
        listTab.getListView().setItems(list);
        this.tabPane.getTabs().add((ListTabGUI)listTab);
    }
    
    @Override
    public void setEmployeeDetails(Employee employee) {
        text = local.language();
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add(text.getString("userNumber")+": " + employee.getUserID());
        list.add(text.getString("firstname")+": " + employee.getFirstName());
        list.add(text.getString("lastname")+": " + employee.getLastName());
        list.add(text.getString("employeeTitle")+": "+ employee.getTitle());
        list.add(text.getString("email")+": " + employee.getEmail());
        this.tabPane.getTabs().clear();
        ListTabGUI_IF listTab = new ListTabGUI(text.getString("employeeInfoTab"));
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
    public Message getSelectedReceivedMessage() {
        return this.receivedMessageListView.getSelection();
    }
    
    @Override
    public Message getSelectedSentMessage() {
        return this.sentMessageListView.getSelection();
    }
    
    @Override
    public User_IF getSelectedUser() {
        return this.userListView.getSelection();
    }
    
    @Override
    public Employee getSelectedEmployee() {
        return this.employeeListView.getSelection();
    }

    /*@Override
    public Prescription getPrescriptionForm() {
        return this.prescriptionForm.getPrescription();
    }*/

    @Override
    public Message getMessageForm() {
        return this.messageForm.getMessage();
    }

    
    
    @Override
    public User_IF getUserForm() {
        return this.userForm.getUser();
    
    }

    @Override
    public User_IF getAuthenticatedUser() {
        return this.mediator.getAuthenticatedUser();
    }
}

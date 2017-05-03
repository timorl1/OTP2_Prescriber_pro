/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.List;
import appuser.AppUser;
import clientresources.ClientResources;
import clientresources.ClientResources_IF;
import resources.diagnose.Diagnose;
import resources.drug.Drug;
import resources.drug.DrugResources;
import resources.drug.DrugResources_IF;
import resources.employee.Employee;
import resources.message.Message;
import resources.message.Messenger;
import resources.message.Messenger_IF;
import resources.patient.Patient;
import resources.prescription.Prescription;
import resources.prescription.PrescriptionMaker;
import resources.prescription.PrescriptionMaker_IF;
import resources.user.User;
import resources.user.User_IF;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class Controller implements Controller_IF {
    private MainGUI_IF gui;
    private AppUser auth;
    private ClientResources_IF clientRes;
    private DrugResources_IF drugRes;
    private PrescriptionMaker_IF prescriptionMaker;
    private Messenger_IF messenger;
    
    public Controller(MainGUI_IF gui) {
        this.gui = gui;
        this.auth = new AppUser();
        this.clientRes = new ClientResources();
        this.drugRes = new DrugResources();
        this.prescriptionMaker = new PrescriptionMaker();
        this.messenger = new Messenger();
    }

    @Override
    public void login(String username, String password) {
        if(this.auth.setUser(username)){
            this.auth.authenticate(password);
            if (this.auth.isAuthenticated()) {
                this.gui.setSideBar();
                switch(this.auth.getUserPrivileges()) {
                    case 0:
                        this.gui.setAccessDenied();
                        break;
                    case 1:
                        this.gui.setPatientList();
                        this.gui.setDrugList();
                        this.gui.setReceivedMessageList();
                        this.gui.setSentMessageList();
                        this.gui.setBasicTools();
                        break;
                    case 2:
                        this.gui.setPatientList();
                        this.gui.setDrugList();
                        this.gui.setPrescriptionList();
                        this.gui.setReceivedMessageList();
                        this.gui.setSentMessageList();
                        this.gui.setPrescriptionTools();
                        this.gui.setBasicTools();
                        break;
                    case 3:
                        this.gui.setUserList();
                        this.gui.setEmployeeList();
                        this.gui.setReceivedMessageList();
                        this.gui.setSentMessageList();
                        this.gui.setUserTools();
                        this.gui.setBasicTools();
                        break;
                }
            }
            else {
                this.gui.setLoginFailed();
            }
        }
        else {
            this.gui.setLoginFailed();
        }
    }
    
    @Override
    public void logout(){
        this.auth.setAuthenticate(false);
        this.gui.setLogout();
    }
    @Override
    public List<Patient> getPatients() {
        return this.clientRes.getPatients();
    }
    
    @Override
    public List<Diagnose> getPatientDiagnoses() {
        return this.clientRes.getPatientDiagnoses(this.gui.getSelectedPatient());
    }

    @Override
    public List<Prescription> getPatientPrescriptions() {
        return this.clientRes.getPatientPrescriptions(this.gui.getSelectedPatient());
    }

    @Override
    public List<Drug> getDrugs() {
        return drugRes.getDrugs();
    }

    @Override
    public List<Prescription> getDoctorPrescriptions() {
        return clientRes.getPrescriptionsByDoctor(auth.getUser());
        
    }
    
    @Override
    public void getPrescriptionDetails() {
        this.gui.setPrescriptionDetails(this.clientRes.getPrescriptionDetails(this.gui.getSelectedPrescription()));
    }
    
    @Override
    public void getDiagnoseDetails() {
        this.gui.setDiagnoseDetails(this.clientRes.getDiagnoseDetails(this.gui.getSelectedDiagnose()));
    }

    @Override
    public List<User_IF> getUsers() {
        return this.clientRes.getUsers();
    }
    
    @Override
    public void getUserDetails() {
        this.gui.setUserDetails((User) this.clientRes.getUserDetails(this.gui.getSelectedUser()));
    }

    @Override
    public List<String> getDatabases() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getDrugDetails() {
        this.gui.setDrugDetails(this.gui.getSelectedDrug());
    }

    @Override
    public List<Message> getReceivedMessages() {
        return this.auth.getUser().getReceivedMessages();
    }
    
    @Override
    public List<Message> getSentMessages() {
        return this.auth.getUser().getSentMessages();
    }
    
    @Override
    public void getSentMessageDetails() {
        this.gui.setMessageDetails(this.gui.getSelectedSentMessage());
    }
    
    @Override
    public void getReceivedMessageDetails() {
        Message message = this.gui.getSelectedReceivedMessage();
        message.setOpened(true);
        this.messenger.saveMessage(message);
        this.gui.setMessageDetails(message);
    }
    
    @Override
    public List<Employee> getEmployees() {
        return this.clientRes.getEmployees();
    }
    
    @Override
    public void getEmployeeDetails() {
        this.gui.setEmployeeDetails(this.clientRes.getEmployeeDetails(this.gui.getSelectedEmployee()));
    }

    @Override
    public void lockUser(User_IF user) {
        this.clientRes.lockUser(user);
    }

    @Override
    public void setUserPriviledges(User_IF user) {
        this.clientRes.setUserPriviledges(user);
    }
    
    @Override
    public void createNewPrescription() {
        this.gui.setPrescriptionForm(this.prescriptionMaker.createPrescription(this.auth.getUser()));
    }
    
    @Override
    public boolean savePrescription() {
        return this.prescriptionMaker.savePrescription(this.gui.getPrescriptionForm());
    }  
    
    @Override
    public boolean saveMessage(){
        return this.messenger.saveMessage(this.gui.getMessageForm());
    }
    
     @Override
    public boolean saveUser(){
        return this.clientRes.saveUser(this.gui.getUserForm());
    }
    
    @Override
    public void createNewUser() {
        this.gui.setUserForm(this.clientRes.createNewUser());
    }

    @Override
    public void createNewMessage() {
        this.gui.setMessageForm(this.messenger.createMessage(this.auth.getUser()));
    }

    @Override
    public void updateChecker() {
        //this.aeChecker.setPrescriptions(this.clientRes.getPrescriptionsByDoctor(this.auth.getUser()));
    }
    
    
}

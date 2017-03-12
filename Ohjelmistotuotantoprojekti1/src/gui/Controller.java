/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.List;
import javafx.fxml.FXML;
import model.AppUser;
import model.ClientResources;
import model.DoseStatus;
import model.Drug;
import model.DrugResources;
import model.Employee;
import model.Patient;
import model.Prescription;
import model.User;
import model.User_IF;


public class Controller implements Controller_IF {
    private MainGUI_IF gui;
    private AppUser auth;
    private ClientResources clientRes;
    private DrugResources drugRes;
    
    public Controller(MainGUI_IF gui) {
        this.gui = gui;
        this.auth = new AppUser();
        this.clientRes = new ClientResources();
        this.drugRes = new DrugResources();
    }

    @Override
    public void login(String username, String password) {
        this.auth.setUser(username);
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
                    this.gui.setMessageList();
                    break;
                case 2:
                    this.gui.setPatientList();
                    this.gui.setDrugList();
                    this.gui.setPrescriptionList();
                    this.gui.setMessageList();
                    this.gui.setPrescriptionTools();
                    break;
                case 3:
                    this.gui.setUserList();
                    this.gui.setEmployeeList();
                    this.gui.setMessageList();
                    break;
            }
        }
        else {
            this.gui.setLoginFailed();
        }
    }

    @Override
    public List<Patient> getPatients() {
        return this.clientRes.getPatients();
    }
    
    @FXML
    @Override
    public void filterPatients() {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getPatientDetails() {
        this.gui.setPatientDetails(this.clientRes.getPatientDetails(this.gui.getSelectedPatient()));
    }

    @Override
    public void getPatientDiagnoses() {
        this.gui.setPatientDiagnoses(this.clientRes.getPatientDiagnoses(this.gui.getSelectedPatient()));
    }

    @Override
    public void getPatientPrescriptions() {
        this.gui.setPatientPrescriptions(this.clientRes.getPatientPrescriptions(this.gui.getSelectedPatient()));
    }

    @Override
    public List<Drug> getDrugs() {
        return drugRes.getDrugs();
    }

    @Override
    public List<Prescription> getPrescriptions() {
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
    public List<String> getMessages() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getMessageDetails() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public void checkDose() {
        DoseStatus status = this.clientRes.evaluateDose(this.gui.getSelectedPatient(), this.gui.getSelectedDrug(), this.gui.getPrescriptionForm().getDose() * this.gui.getPrescriptionForm().getTimesADay());
        switch (status) {
            case NULL:
                this.gui.setNullDoseMessage();
                break;
            case INSUFFICIENT:
                this.gui.setInsuffucientDoseMessage();
                break;
            case OPTIMAL:
                this.gui.setOptimalDoseMessage();
                break;
            case OVER_OPTIMAL:
                this.gui.setOverOptimalDoseMessage();
                break;
            case RISK_LIMIT:
                this.gui.setRiskLimitDoseMessage();
                break;
            case OVERDOSE:
                this.gui.setOverdoseMessage();
                break;
        }
    }

    @Override
    public void createNewPrescription() {
        this.gui.setPrescriptionForm(this.clientRes.addNewPrescription(this.auth.getUser()));
    }

    @Override
    public void savePrescription() {
        this.clientRes.savePrescription(this.gui.getPrescriptionForm());
    }
    
}

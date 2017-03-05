/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.List;
import javafx.fxml.FXML;
import javafx.util.converter.DoubleStringConverter;
import model.AppUser;
import model.ClientResources;
import model.Drug;
import model.DrugResources;
import model.Patient;
import model.PatientBuilder;
import model.Prescription;
import model.User;


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
            switch(this.auth.getUserPriviledges()) {
                case 0:
                    this.gui.setAccessDenied();
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
                    break;
                case 3:
                    this.gui.setUserList();
                    this.gui.setEmployeeList();
                    this.gui.setMessageList();
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getDatabases() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getDrugDetails() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public List<String> getEmployees() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

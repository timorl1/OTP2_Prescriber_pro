/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Authenticator;
import model.ClientResources;
import model.Drug;
import model.DrugResources;
import model.Patient;
import model.Prescription;
import model.User;


public class Controller implements Controller_IF {
    private MainGUI_IF gui;
    private Authenticator auth;
    private ClientResources clientRes;
    private DrugResources drugRes;
    
    private User user;
    
    public Controller(MainGUI_IF gui) {
        this.gui = gui;
        this.auth = new Authenticator();
        this.clientRes = new ClientResources();
        this.drugRes = new DrugResources();
    }

    @Override
    public void login(String username, String password) {
        this.user = this.auth.authenticateUser(username, password);
        if (this.user != null) {
            this.gui.loadSideBar();
            switch(this.user.getPriviledges()) {
                case 1:
                    this.gui.loadPatientList();
                    this.gui.loadDrugList();
                    this.gui.loadMessageList();
                    break;
                case 2:
                    this.gui.loadPatientList();
                    this.gui.loadDrugList();
                    this.gui.loadPrescriptionList();
                    this.gui.loadMessageList();
                    break;
                case 3:
                    this.gui.loadUserList();
                    this.gui.loadEmployeeList();
                    this.gui.loadMessageList();
            }
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
    public List<String> getPatientDetails() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getPatientDiagnoses() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getPatientPrescriptions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

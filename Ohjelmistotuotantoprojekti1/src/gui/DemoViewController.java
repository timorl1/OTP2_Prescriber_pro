/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.DrugDAO;
import dao.PatientDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import model.Drug;
import model.Patient;

/**
 * FXML Controller class
 *
 * @author joosiika
 */
public class DemoViewController implements Initializable {

    @FXML
    private TreeView tree1;
    @FXML
    private TreeView tree2;
    private final PatientDAO db = new PatientDAO();
    private final DrugDAO drugdb = new DrugDAO();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadPatients(tree1);
        //handleTreeViewClick(tree1);
        loadDrugs();
    }
    
    public void handleTreeViewClick(TreeView tree) {
        tree.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    tree.setMaxHeight(40);
                } catch (Exception e) {
                }
            }
        });
    }

    public void loadPatients(TreeView tree) {
        TreeItem<String> root = new TreeItem<String>("Potilaat");
        try {
            List<Patient> patients = db.readPatients();
            root.setExpanded(false);
            for (Patient patient : patients) {
                root.getChildren().add(new TreeItem<String>(patient.getLastName() + ", " + patient.getFirstName()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DemoViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tree.setRoot(root);
    }

    public void loadDrugs() {
        TreeItem<String> root = new TreeItem<String>("Lääkkeet");
        List<Drug> drugs = drugdb.readDrugs();
        root.setExpanded(false);
        for (Drug drug : drugs) {
            root.getChildren().add(new TreeItem<String>(drug.getName() + ", " + drug.getActiveAgents()));
        }

        tree2.setRoot(root);
    }
}

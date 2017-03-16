/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author joosiika
 */
public class SideBarGUI extends AnchorPane implements SideBarGUI_IF {

    @FXML
    private VBox vBox;
    @FXML
    private TextField searchField;
    @FXML
    private Accordion accordion;
    @FXML
    private Button messageButton;
    
    MainGUI_IF root;
    FXMLLoader loader;
    
    public SideBarGUI(MainGUI_IF root) {
        this.root = root;
        try {
            loader = new FXMLLoader(getClass().getResource("SideBar.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException exc) {
            // handle exception
        }
    }
    
    @FXML
    public void filterPatients(KeyEvent event) {
        /*FilteredList<Patient> filteredPatients = new FilteredList(patients, p -> true);
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
        });*/
    }
    
    @Override
    public TextField getSearchField() {
        return searchField;
    }
    
    @Override
    public void addView(TitledPane pane) {
        this.accordion.getPanes().add(pane);
    }

    @Override
    public VBox getVbox() {
        return this.vBox;
    }

    @Override
    public Button getMessageButton() {
        return this.messageButton;
    }
    
}

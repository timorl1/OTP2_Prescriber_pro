/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author joosiika
 */
public class ParentController implements Initializable {
    
    private ResourceBundle rb;

    @FXML
    private AnchorPane root;
    @FXML
    private VBox vBox;
    @FXML
    private VBox controls;
    @FXML
    private ChoiceBox<ResourceBundle> localizationSelector;
    @FXML
    private Accordion accordion;
    @FXML
    private VBox menuButtons;
    @FXML
    private TabPane tabPane;

    /**
     * Initializes the controller class.
     * @param url Location
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.rb = rb;
    }
    
    @FXML
    public void changeLocalization() {
        Localisation.getInstance().chooseLanguage("eng");
    }
    
}

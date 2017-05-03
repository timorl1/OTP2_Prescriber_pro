/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static gui.Localisation.getInstance;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class SideBarGUI extends AnchorPane implements SideBarGUI_IF {
    
    ResourceBundle text;
    Localisation local = getInstance();
    
    @FXML
    private VBox vBox;
    @FXML
    private TextField searchField;
    @FXML
    private Accordion accordion;
    @FXML
    private VBox buttonBox;
    
    FXMLLoader loader;
    
    public SideBarGUI(Parent parent) {
        text = local.language();
        try {
            loader = new FXMLLoader(getClass().getResource("SideBar.fxml"));
            loader.setController(this);
            loader.setRoot(parent);
            loader.load();
            searchField.setPromptText(text.getString("search"));
        } catch (IOException exc) {
            // handle exception
        }
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
    public VBox getButtonBox() {
        return this.buttonBox;
    }
    
    
}

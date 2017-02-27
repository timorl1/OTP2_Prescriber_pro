package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import model.Drug;
import model.Patient;
import model.User;

/**
 * FXML Controller class
 *
 * @author joosiika
 */
public class MainGUI implements Initializable, MainGUI_IF {
    
    @FXML
    private AnchorPane root;
    
    private SideBarGUI sideBar;
    
    private LoginGUI_IF login;
    
    //Load login-component on initalization
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadLogin();
    }
    
    //Creates a new instance of the LoginGUI and passes itself as a parameter
    //Adds the LoginGUI as a child-component to the MainGUI's anchor pane
    @Override
    public void loadLogin() {
        this.login = new LoginGUI(this);
        this.root.getChildren().add((LoginGUI)this.login);
    }
    
    //Removes the login component and loads the side bar component
    //Component type is defined by user's priviledges
    //1 - load nurses sidebar components
    //2 - load doctors sidebar components
    //3 - load administrators sidebar components
    //0 - should print out "access revoked" and instructions to contact the administrator
    @Override
    public void loadSideBar(User user) {
        if (user.getPriviledges() == 1) {
            this.root.getChildren().remove((LoginGUI)this.login);
            this.sideBar = new SideBarGUI(this);
            SideBarListViewGUI<Patient> patientListView = new SideBarListViewGUI("Patient", "Potilas", this);
            this.sideBar.addView(patientListView);
            SideBarListViewGUI<Drug> drugListView = new SideBarListViewGUI("Drug", "Lääkkeet", this);
            this.sideBar.addView(drugListView);
            this.root.getChildren().add(this.sideBar);
        }
        else if (user.getPriviledges() == 3) {
            this.root.getChildren().remove((LoginGUI)this.login);
            this.sideBar = new SideBarGUI(this);
            SideBarListViewGUI<String> dbListView = new SideBarListViewGUI("Database", "Aseta tietokanta", this);
            this.sideBar.addView(dbListView);
            this.root.getChildren().add(this.sideBar);
        }
    }

    //Loads the tab pane component depending of the side bar content selection
    @Override
    public <T> void loadTabPane(T selection) {
        if (selection instanceof Patient) {
            System.out.println("You selected patient " + selection);
        }
        else if (selection instanceof Drug) {
            System.out.println("You selected drug " + selection);
        }
    }
    
}

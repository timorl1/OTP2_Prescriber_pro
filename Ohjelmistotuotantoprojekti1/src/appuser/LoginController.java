/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appuser;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class LoginController implements Initializable {
    
    private AppUser_IF appUser = new AppUser();

    @FXML
    private AnchorPane root;
    @FXML
    private Label loginLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private Label passwordLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label messageLabel;
    @FXML
    private Button loginButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void login() {
        this.appUser.authenticate(this.getPassword());
    }
    
    public void setUser() {
        this.appUser.setUser(this.getUsername());
    }
    
    public String getUsername() {
        return this.usernameField.getText();
    }
    
    public String getPassword() {
        return this.passwordField.getText();
    }
    
}

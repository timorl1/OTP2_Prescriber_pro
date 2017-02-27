/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.LoginController;
import controller.LoginController_IF;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.Authenticator;
import model.Authenticator_IF;

/**
 * FXML Controller class
 *
 * @author joosiika
 */
public class LoginGUI extends AnchorPane implements LoginGUI_IF {
    
    FXMLLoader loader;
    MainGUI_IF root;
    LoginController_IF loginController;
    
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button loginButton;
    
    //Constructor takes the MainGUI as a parameter to be used as root for this LoginGUI
    //Creates an instance of the LoginController and passes the root and itself as a parameter
    //Loads the custom login-object from fxml-file
    LoginGUI(MainGUI_IF root) {
        this.root = root;
        this.loginController = new LoginController(this.root, this);
        try {
            loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException exc) {
            // handle exception
        }
    }
    
    //Gets input from the username field and returns it to the caller
    public String getUsername() {
        return this.usernameField.getText();
    }
    
    //Gets input from the password field and returns it to the caller
    public String getPassword() {
        return this.passwordField.getText();
    }
    
    //When login button is clicked, this method calls the LoginControllers login method
    @FXML
    public void onLogin() {
        this.loginController.login();
    }
    
}

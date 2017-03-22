/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author joosiika
 */
public class LoginGUI extends AnchorPane implements LoginGUI_IF {
    
    FXMLLoader loader;
    
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button loginButton;
    
    //Constructor takes the MainGUI as a parameter to be used as root for this LoginGUI
    //Creates an instance of the LoginController and passes the root and itself as a parameter
    //Loads the custom login-object from fxml-file
    LoginGUI() {
        try {
            loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (Exception exc) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Sisäänkirjatumis ikkunan latautuminen epäonnistui,\nkäynnistä ohjelma uudeestaan.");
            alert.setTitle("Virhe");
            alert.setHeaderText("Varoitus:");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("prescriptionform.css").toExternalForm());
            alert.showAndWait();
        }
    }
    
    //Gets input from the username field and returns it to the caller
    @Override
    public String getUsername() {
        return this.usernameField.getText();
    }
    
    //Gets input from the password field and returns it to the caller
    @Override
    public String getPassword() {
        return this.passwordField.getText();
    }

    @Override
    public Button getButton() {
        return this.loginButton;
    }

    @Override
    public void addMessage(String message) {
        this.getChildren().add(new Label(message));
    }
    
}

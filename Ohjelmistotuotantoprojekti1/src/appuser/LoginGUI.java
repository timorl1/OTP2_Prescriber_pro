/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appuser;

import gui.AlertMessage;
import gui.Localisation;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class LoginGUI extends AnchorPane implements LoginGUI_IF {
    ResourceBundle text;
    Localisation local = Localisation.getInstance();
   // private static LoginGUI_IF INSTANCE = null;
    
    FXMLLoader loader;
    
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label messageLabel;
    
    private AlertMessage alertMessage = AlertMessage.getINSTANCE();
    public LoginGUI() {
        text = local.language();
        try {
            loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
            loginLabel.setText(text.getString("login"));
            usernameLabel.setText(text.getString("username")+":");
            usernameField.setPromptText(text.getString("username"));
            passwordLabel.setText(text.getString("password")+":");
            passwordField.setPromptText(text.getString("password"));
            loginButton.setText(text.getString("login"));
            loginButton.disableProperty().bind(this.createBooleanBinding());
        } catch (IOException exc) {
            alertMessage.showWarningAlert(text.getString("alertTitleError"),
                    text.getString("alertTextWarning")+":", text.getString("loadingFail"));
        }
    }
    
  /*  public synchronized static LoginGUI_IF getInstance(){
        if (INSTANCE == null){
            INSTANCE = new LoginGUI();
        }
        return INSTANCE;
    } */
    
    @Override
    public String getUsername() {
        return this.usernameField.getText();
    }
    
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
        this.messageLabel.setText(message);
    }
    
    @Override
    public void clearPasswordField(){
        this.passwordField.clear();
    }
    
    private BooleanBinding createBooleanBinding() {
        BooleanBinding booleanBinding = new BooleanBinding() {
            {
                super.bind(usernameField.textProperty(),
                        passwordField.textProperty());
            }
            @Override
            protected boolean computeValue() {
                return (usernameField.getText().isEmpty() || passwordField.getText().isEmpty());
            }
        };
        return booleanBinding;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appuser;

import gui.Localisation;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class LoginGUI extends AnchorPane implements LoginGUI_IF {
    ResourceBundle text;
    Localisation local = Localisation.getInstance();
    private static LoginGUI_IF INSTANCE = null;
    
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
    
    private LoginGUI() {
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
        } catch (IOException exc) {
            Alert alert = new Alert(Alert.AlertType.WARNING, text.getString("loadingFail"));
            alert.setTitle(text.getString("alertTitleError"));
            alert.setHeaderText(text.getString("alertTextWarning")+":");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("login.css").toExternalForm());
            alert.showAndWait();
        }
    }
    
    public synchronized static LoginGUI_IF getInstance(){
        if (INSTANCE == null){
            INSTANCE = new LoginGUI();
        }
        return INSTANCE;
    }
    
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
        this.getChildren().add(new Label(message));
    }
    
    @Override
    public void clearPasswordField(){
        this.passwordField.clear();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class AlertMessage {
    private static final AlertMessage INSTANCE = new AlertMessage();
    private Alert alert;
    
    private AlertMessage(){};

    public static AlertMessage getINSTANCE() {
        return INSTANCE;
    }
    

    public Optional showConfirmationAlert(String title, String header, String message) {
        this.alert = new Alert(Alert.AlertType.CONFIRMATION, message);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("alertmessage.css").toExternalForm());
        return alert.showAndWait();
    }
    
    public void showInformationAlert(String title, String message){
        this.alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.setTitle(title);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("alertmessage.css").toExternalForm());
        alert.showAndWait();
    }
    
    public void showWarningAlert(String title, String header, String message){
        this.alert = new Alert(Alert.AlertType.WARNING, message);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("alertmessage.css").toExternalForm());
        alert.showAndWait();
    }
    
}

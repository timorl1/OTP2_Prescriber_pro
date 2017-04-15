/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 *
 * @author joosiika
 */
public class AlertMessage {
    
    private Alert alert;

    public AlertMessage(String title, String header, String message) {
        this.alert = new Alert(Alert.AlertType.WARNING, message);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("alertmessage.css").toExternalForm());
    }
    
    public void showAlert() {
        alert.showAndWait();
    }
    
}

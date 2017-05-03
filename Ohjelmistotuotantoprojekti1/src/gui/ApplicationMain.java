/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static gui.Localisation.getInstance;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class ApplicationMain extends Application {
    
    AnchorPane root;
    AnchorPane login;
    Localisation local = getInstance();
    ResourceBundle text;
    String choice;
    
    private void restart(Stage stage) throws Exception {
        Stage newStage = new Stage();
        stage.close();
        start(newStage);
    }
       
    @Override
    public void start(Stage primaryStage) throws Exception {
        local.chooseLanguage(local.getSelectedLanguage());
        text = local.language();
        this.root = FXMLLoader.load(getClass().getResource("MainRoot_1.fxml"), text);
        primaryStage.setTitle(text.getString("appLabel"));
        ComboBox languageChoice = new ComboBox(FXCollections.observableArrayList(local.getLanguageList()));
        languageChoice.setEditable(false);
        languageChoice.setPromptText(text.getString("selectLanguage"));
        AnchorPane.setTopAnchor(languageChoice, 2.0);
        AnchorPane.setRightAnchor(languageChoice, 15.0);
        root.getChildren().add(languageChoice);
        languageChoice.setOnAction((Event e) -> {
            
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle(text.getString("confirmationTitleLanguage"));
            alert.setHeaderText(text.getString("confirmationHeaderTextLanguage"));
            alert.setContentText(text.getString("confirmationContentTextLanguage"));
            alert.initStyle(StageStyle.UNDECORATED);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("warning.css").toExternalForm());
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                local.setSelectedLanguage((String) languageChoice.getSelectionModel().getSelectedItem());
                try {
                    restart(primaryStage);
                } catch (Exception ex) {
                    Logger.getLogger(ApplicationMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }           
        });     
        Scene scene = new Scene(root);        
        primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight()-50);
        primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth()-50);
        primaryStage.centerOnScreen();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        System.exit(0);
    }
    
}

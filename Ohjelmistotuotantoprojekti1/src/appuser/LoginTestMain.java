/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appuser;

import gui.Localisation;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author joosiika
 */
public class LoginTestMain extends Application {
    
    private Parent root;
    private ResourceBundle rb;
    
    @Override
    public void start(Stage primaryStage) {
        Localisation.getInstance().chooseLanguage("eng");
        this.rb = Localisation.getInstance().language();
        try {
            this.root = FXMLLoader.load(getClass().getResource("LoginStandalone.fxml"), rb);
        } catch (IOException ex) {
            Logger.getLogger(LoginTestMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

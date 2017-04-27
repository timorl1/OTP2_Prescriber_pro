/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class PrescriberProMain extends Application {
    
    Parent root;
    Localisation local = Localisation.getInstance();
    ResourceBundle rb;
    Scene scene;
       
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.local.chooseLanguage("sve");
        this.rb = local.language();
        this.root = FXMLLoader.load(getClass().getResource("Parent.fxml"), this.rb);
        primaryStage.setTitle(this.rb.getString("appLabel"));    
        scene = new Scene(root);        
        primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight()-50);
        primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth()-50);
        primaryStage.centerOnScreen();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void addSidebar() {
        //this.root.getChildren().add(new SideBarGUI());
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        System.exit(0);
    }
    
}

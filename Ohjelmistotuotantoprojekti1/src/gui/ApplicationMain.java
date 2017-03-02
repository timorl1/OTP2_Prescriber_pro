/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author joosiika
 */
public class ApplicationMain extends Application {
    
    private final MainGUI mainGUI = new MainGUI();
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = FXMLLoader.load(getClass().getResource("MainRoot.fxml"));
        primaryStage.setTitle("Prescriber Pro v.1.0");
        final Scene scene = new Scene(root);
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
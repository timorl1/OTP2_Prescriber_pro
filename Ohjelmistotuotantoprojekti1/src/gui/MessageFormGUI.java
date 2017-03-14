/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.Message;
import model.User_IF;

/**
 *
 * @author Timo
 */
public class MessageFormGUI extends Tab {
    
    @FXML
    private GridPane gridPane;
    @FXML
    private Label titleLabel;
    @FXML
    private Label messageLabel;
    @FXML 
    private ChoiceBox receiverSelector;
    @FXML
    private TextField titleField;
    @FXML
    private TextArea messageField;
    @FXML 
    private ButtonBar buttonBar;
    @FXML
    private Button cancel;
    @FXML
    private Button send;
    @FXML
    private Label mainTitle;
    @FXML
    private Label receiverLabel;
    
    FXMLLoader loader;
    private Message message;
    private Controller controller;
    
    public MessageFormGUI(List<User_IF> users){
        
        try {
            loader = new FXMLLoader(getClass().getResource("MessageForm.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
            ObservableList<User_IF> list = FXCollections.observableArrayList(users);
            this.receiverSelector.setItems(list);
            //this.initializeFields();
            //this.initializeBasicListeners();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public ChoiceBox getReceiverSelector() {
        return receiverSelector;
    }

    
    public TextField getTitleField() {
        return titleField;
    }

    
    public TextArea getMessageField() {
        return messageField;
    }

    
    public Button getCancel() {
        return cancel;
    }


    public Button getSend() {
        return send;
    }


    public Message getMessage() {
        return message;
    }
}

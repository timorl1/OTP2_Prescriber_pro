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
import model.User;
import model.User_IF;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class MessageFormGUI extends Tab implements MessageFormGUI_IF{
    
    @FXML
    private GridPane gridPane;
    @FXML
    private Label titleLabel;
    @FXML
    private Label messageLabel;
    @FXML 
    private ChoiceBox<User_IF> receiverSelector;
    @FXML
    private TextField titleField;
    @FXML
    private TextArea messageField;
    @FXML 
    private ButtonBar buttonBar;
    @FXML
    private Button cancelButton;
    @FXML
    private Button sendButton;
    @FXML
    private Label mainTitle;
    @FXML
    private Label receiverLabel;
    
    FXMLLoader loader;
    private Message message;
    private ObservableList<User_IF> list;
    
    public MessageFormGUI(List<User_IF> users, Message message, String title){
        this.setText(title);
        this.message = message;
        try {
            loader = new FXMLLoader(getClass().getResource("MessageForm.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
            this.list = FXCollections.observableArrayList(users);
            this.receiverSelector.setItems(this.list);
            this.titleField.setOnKeyReleased(e -> this.message.setTitle(this.titleField.getText()));
            this.messageField.setOnKeyReleased(e -> this.message.setMessage(this.messageField.getText()));
            this.receiverSelector.setOnAction(e -> this.message.setReceiver((User) this.receiverSelector.getSelectionModel().getSelectedItem()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Button getCancelButton() {
        return cancelButton;
    }


    @Override
    public Button getSendButton() {
        return sendButton;
    }


    @Override
    public Message getMessage() {
        return message;
    }
}

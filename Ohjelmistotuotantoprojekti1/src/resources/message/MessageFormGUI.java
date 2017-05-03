/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.message;

import gui.Localisation;
import static gui.Localisation.getInstance;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import resources.user.User;
import resources.user.User_IF;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class MessageFormGUI extends Tab implements MessageFormGUI_IF{
    
    ResourceBundle text;
    Localisation local = getInstance();
    @FXML
    private GridPane gridPane;
    @FXML
    private Label titleLabel;
    @FXML
    private Label messageLabel;
    @FXML 
    private ComboBox<User_IF> receiverSelector;
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
    @FXML
    private Label counter;
    
    FXMLLoader loader;
    private Message message;
    private ObservableList<User_IF> list;
    FilteredList<User_IF> filteredList;
    
    public MessageFormGUI(List<User_IF> users, Message message, String title){
        text = local.language();
        this.setText(title);
        this.message = message;
        try {
            loader = new FXMLLoader(getClass().getResource("MessageForm.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
            titleLabel.setText(text.getString("subject")+":");
            messageLabel.setText(text.getString("message")+":");
            receiverLabel.setText(text.getString("chooseReceiver")+":");
            titleField.setPromptText(text.getString("subject"));
            messageField.setPromptText(text.getString("messagewritefield"));
            cancelButton.setText(text.getString("cancel"));
            sendButton.setText(text.getString("send"));
            mainTitle.setText(text.getString("message"));
            receiverSelector.setEditable(false);
            receiverSelector.setPromptText(text.getString("chooseReceiver"));
            messageField.setWrapText(true);
            counter.setText(text.getString("counter"));
            this.list = FXCollections.observableArrayList(users);
            this.receiverSelector.setItems(this.list);
            this.titleField.setOnKeyReleased(e -> this.message.setTitle(this.titleField.getText()));
            this.messageField.setOnKeyReleased(e -> {
                    String s = this.messageField.getText();
                    final int maxlength = 1000;
                    int length = this.messageField.getText().length();
                    this.counter.setText(Integer.toString(length)+ text.getString("counter"));
                    if(s.length() >= maxlength){
                        this.messageField.setText(s.substring(0, maxlength));
                        this.messageField.positionCaret(s.length());
                    } else {
                        this.message.setMessage(this.messageField.getText());}
            });
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

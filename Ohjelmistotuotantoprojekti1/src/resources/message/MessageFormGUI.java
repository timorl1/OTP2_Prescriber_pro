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
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import resources.message.Message;
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
            messageField.setPromptText(text.getString("message"));
            cancelButton.setText(text.getString("cancel"));
            sendButton.setText(text.getString("send"));
            mainTitle.setText(text.getString("message"));
            messageField.setWrapText(true);
            receiverSelector.setEditable(true);
            receiverSelector.setPromptText("TEst");
            this.list = FXCollections.observableArrayList(users);
            filteredList = new FilteredList<>(this.list, p -> true);
            
            // Add a listener to the textProperty of the combobox editor. The
            // listener will simply filter the list every time the input is changed
            // as long as the user hasn't selected an item in the list.
            receiverSelector.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
                final TextField editor = receiverSelector.getEditor();
                final User_IF selected = receiverSelector.getSelectionModel().getSelectedItem();

                // This needs run on the GUI thread to avoid the error described
                // here: https://bugs.openjdk.java.net/browse/JDK-8081700.
                Platform.runLater(() -> {
                    // If the no item in the list is selected or the selected item
                    // isn't equal to the current input, we refilter the list.
                    if (selected == null || !selected.equals(editor.getText())) {
                        filteredList.setPredicate(item -> {
                            // We return true for any items that starts with the
                            // same letters as the input. We use toUpperCase to
                            // avoid case sensitivity.
                            if (item.getUsername().toUpperCase().startsWith(newValue.toUpperCase())) {
                                return true;
                            } else {
                                return false;
                            }
                        });
                    }
                });
            });

            this.receiverSelector.setItems(this.filteredList);
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

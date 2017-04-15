/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.message;

import resources.drug.*;
import java.util.ResourceBundle;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 *
 * @author joosiika
 */
public class SentMessageListCell extends ListCell<Message> {
    
    private ResourceBundle text;

    public SentMessageListCell(ResourceBundle text) {
        this.text = text;
    }

    @Override
    protected void updateItem(Message message, boolean empty) {
        super.updateItem(message, empty);

        if (empty || message == null) {
            setText(null);
            setGraphic(null);
        } else {
            GridPane gridPane = new GridPane();
            Label receiverLabel = new Label();
            Label subjectLabel = new Label();
            Label dateLabel = new Label();
            Circle unread = new Circle(5);
            
            unread.setFill(Color.CORNFLOWERBLUE);

            receiverLabel.setText(message.getReceiver().getFirstName() + " " + message.getReceiver().getLastName());
            dateLabel.setText(message.getDate().toString());
            
            subjectLabel.setText(message.getTitle());

            gridPane.add(receiverLabel, 0, 0);
            gridPane.add(dateLabel, 1, 0);
            gridPane.add(subjectLabel, 0, 1);
            
            if (!message.isOpened()) {
                gridPane.add(unread, 2, 0);
            }
            
            this.setGraphic(gridPane);

        }

    }
}

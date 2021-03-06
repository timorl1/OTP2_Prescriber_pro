/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.message;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public abstract class MessageListCell extends ListCell<Message> implements Observer {
    
    private ResourceBundle text;
    private SimpleDateFormat dateFormatter;

    public MessageListCell(ResourceBundle text) {
        this.text = text;
        this.dateFormatter = new SimpleDateFormat(this.text.getString("messageDateTimeFormat"), this.text.getLocale());
    }
    
    public abstract String getNameLabel();
    
    public abstract String getNameValue(Message message);

    @Override
    protected void updateItem(Message message, boolean empty) {
        super.updateItem(message, empty);
        
        if (empty || message == null) {
            setText(null);
            setGraphic(null);
        }
        else {
            if (message.countObservers() == 0) {
                message.addObserver(this);
            }
            GridPane gridPane = new GridPane();
            Label nameLabel = new Label();
            Label subjectLabel = new Label();
            Label dateLabel = new Label();
            Text nameValue = new Text();
            Text subjectValue = new Text();
            Text dateValue = new Text();
            Circle unread = new Circle(5);
            
            unread.setFill(Color.CORNFLOWERBLUE);
            
            nameLabel.setText(this.getNameLabel() + ": ");
            nameValue.setText(this.getNameValue(message));
            
            subjectLabel.setText(text.getString("subject") + ": ");
            subjectValue.setText(message.getTitle());
            
            dateLabel.setText(text.getString("date") + ": ");
            dateValue.setText(dateFormatter.format(message.getDate()));
            
            gridPane.add(nameLabel, 0, 0);
            gridPane.add(nameValue, 1, 0);
            gridPane.add(subjectLabel, 0, 1);
            gridPane.add(subjectValue, 1, 1);
            gridPane.add(dateLabel, 0, 2);
            gridPane.add(dateValue, 1, 2);
            
            if (!message.isOpened()) {
                gridPane.add(unread, 2, 0);
            }
            
            this.setGraphic(gridPane);
        }
    }
    
}

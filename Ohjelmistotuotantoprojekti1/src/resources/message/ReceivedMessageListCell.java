/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.message;

import java.util.ResourceBundle;

/**
 *
 * @author joosiika
 */
public class ReceivedMessageListCell extends MessageListCell {
    
    private ResourceBundle text;

    public ReceivedMessageListCell(ResourceBundle text) {
        super(text);
        this.text = text;
    }

    @Override
    public String getNameLabel() {
        return this.text.getString("sender");
    }

    @Override
    public String getNameValue(Message message) {
        return message.getSender().getFirstName() + " " + message.getSender().getLastName();
    }
}

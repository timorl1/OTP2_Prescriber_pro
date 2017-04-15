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
public class SentMessageListCell extends MessageListCell {
    
    private ResourceBundle text;

    public SentMessageListCell(ResourceBundle text) {
        super(text);
        this.text = text;
    }

    @Override
    public String getNameLabel() {
        return this.text.getString("receiver");
    }

    @Override
    public String getNameValue(Message message) {
        return message.getReceiver().getFirstName() + " " + message.getReceiver().getLastName();
    }
}

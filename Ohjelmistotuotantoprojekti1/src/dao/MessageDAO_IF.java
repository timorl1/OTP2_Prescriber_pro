/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Message;
import model.User;
import model.User_IF;

/**
 * Interface that defines methods for CRUD-operations for messages in database
 * @author Johanna
 */
public interface MessageDAO_IF {
    
    /**
     * Gets message by id from database
     * @param id
     * @return message object
     */
    public abstract Message readMessage(int id);

    /**
     * Gets list of sent messages by user
     * @param user
     * @return list of message objects
     */
    public abstract List<Message> readSentMessages(User user);

    /**
     * Gets list of received messages by user
     * @param user
     * @return list of message objects
     */
    public abstract List<Message> readReceivedMessages(User user);

    /**
     * Creates a message
     * @param message
     * @return true if message is created, false if not
     */
    public abstract boolean createMessage(Message message);
    
    
}

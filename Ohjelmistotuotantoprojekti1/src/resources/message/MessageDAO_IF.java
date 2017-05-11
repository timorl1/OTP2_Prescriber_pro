/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.message;

import java.util.List;
import resources.user.User;

/**
 * Interface that defines methods for CRUD-operations for messages in database
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public interface MessageDAO_IF {
    
    /**
     * Gets message by id from database
     * @param id message's id
     * @return message object
     */
    public abstract Message readMessage(int id);

    /**
     * Gets list of sent messages by user
     * @param user user that sent messages
     * @return list of message objects
     */
    public abstract List<Message> readSentMessages(User user);

    /**
     * Gets list of received messages by user
     * @param user user that received messages
     * @return list of message objects
     */
    public abstract List<Message> readReceivedMessages(User user);

    /**
     * Creates a message
     * @param message message to be sent
     * @return true if message is created, false if not
     */
    public abstract boolean createMessage(Message message);
    
    
}

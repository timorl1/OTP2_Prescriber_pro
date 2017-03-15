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
 *
 * @author Johanna
 */
public interface MessageDAO_IF {
    
    /**
     *
     * @param id
     * @return
     */
    public abstract Message readMessage(int id);

    /**
     *
     * @param user
     * @return
     */
    public abstract List<Message> readSentMessages(User user);

    /**
     *
     * @param user
     * @return
     */
    public abstract List<Message> readReceivedMessages(User user);

    /**
     *
     * @param message
     * @return
     */
    public abstract boolean createMessage(Message message);
    
    
}

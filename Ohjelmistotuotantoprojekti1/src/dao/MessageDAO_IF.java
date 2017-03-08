/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Message;

/**
 *
 * @author Johanna
 */
public interface MessageDAO_IF {
    
    public abstract Message readMessage(int id);
    public abstract List<Message> readMessages(String username);
    public abstract boolean createMessage(Message message);
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.message;

import java.util.List;
import resources.user.User_IF;


public class Messenger implements Messenger_IF {
    
    private final MessageFactory_IF messageFactory = new MessageFactory();
    private final MessageDAO_IF messageDAO = new MessageDAO();

    @Override
    public Message newMessage() {
        return this.messageFactory.createMessage();
    }

    @Override
    public boolean saveMessage(Message message) {
        return this.messageDAO.createMessage(message);
    }

    @Override
    public List<Message> getSentMessagesByUser(User_IF user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Message> getReceivedMessagesByUser(User_IF user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

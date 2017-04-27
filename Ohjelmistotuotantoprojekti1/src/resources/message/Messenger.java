/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.message;

import java.time.LocalDate;
import resources.user.User;
import resources.user.User_IF;

public class Messenger implements Messenger_IF {
    private final MessageDAO_IF messageDAO = new MessageDAO();

    @Override
    public Message createMessage(User_IF user) {
        Message message = new Message();
        message.setSender((User)user);
        message.setDate(java.sql.Date.valueOf(LocalDate.now()));
        return message;
    }

    @Override
    public boolean saveMessage(Message message) {
        return this.messageDAO.createMessage(message);
    }
}

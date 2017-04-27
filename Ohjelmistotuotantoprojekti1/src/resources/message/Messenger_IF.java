/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.message;

import resources.user.User_IF;

/**
 *
 * @author joosiika
 */
public interface Messenger_IF {
    public abstract Message createMessage(User_IF user);
    public abstract boolean saveMessage(Message message);
}

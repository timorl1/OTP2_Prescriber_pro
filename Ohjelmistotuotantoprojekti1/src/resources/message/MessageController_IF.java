/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.message;

/**
 *
 * @author joosiika
 */
public interface MessageController_IF {
    public abstract void listSentMessages();
    public abstract void listReceivedMessages();
    public abstract void displayMessage();
    public abstract void newMessage();
}

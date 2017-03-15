/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 * Interface defines methods for users
 * @author Timo
 */

public interface User_IF {

    /**
     * Sets username to a user-object
     * @param username user's username
     */
    public abstract void setUsername(String username);

    /**
     * Gets user-object's username
     * @return username
     */
    public abstract String getUsername();

    /**
     * Sets userID to a user-object
     * @param userID user's id
     */
    public abstract void setUserID(int userID);

    /**
     * Gets user-object's userID
     * @return user's id
     */
    public abstract int getUserID();

    /**
     * Sets password to a user-object
     * @param password user's password
     */
    public abstract void setPassword(String password);

    /**
     * Gets user-object's password
     * @return user's password
     */
    public abstract String getPassword();

    /**
     * Sets email to a user-object
     * @param email user's email
     */
    public abstract void setEmail(String email);

    /**
     * Gets user-object's email
     * @return user's email
     */
    public abstract String getEmail();

    /**
     * Sets privileges to a user-object
     * @param privileges user's privileges
     */
    public abstract void setUsertype(int privileges);

    /**
     * Gets user-object's usertype
     * @return user's usertype
     */
    public abstract int getUsertype();

    /**
     * Sets first name to a user-object
     * @param firstName user's first name
     */
    public abstract void setFirstName(String firstName);

    /**
     * Gets user-object's fist name
     * @return user's first name
     */
    public abstract String getFirstName();

    /**
     * Sets last name to a user-object
     * @param lastName user's last name
     */
    public abstract void setLastName(String lastName);

    /**
     * Gets user-object's last name
     * @return user's last name
     */
    public abstract String getLastName();

    /**
     * Gets user-object's sent messages
     * @return list of message-objects
     */
    public abstract List<Message> getSentMessages();

    /**
     * Gets user-object's received messages
     * @return list of message-objects
     */
    public abstract List<Message> getReceivedMessages();
    
}

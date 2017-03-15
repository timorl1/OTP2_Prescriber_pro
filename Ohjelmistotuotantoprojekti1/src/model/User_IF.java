/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Timo
 */

public interface User_IF {

    /**
     *
     * @param username
     */
    public abstract void setUsername(String username);

    /**
     *
     * @return
     */
    public abstract String getUsername();

    /**
     *
     * @param userID
     */
    public abstract void setUserID(int userID);

    /**
     *
     * @return
     */
    public abstract int getUserID();

    /**
     *
     * @param password
     */
    public abstract void setPassword(String password);

    /**
     *
     * @return
     */
    public abstract String getPassword();

    /**
     *
     * @param email
     */
    public abstract void setEmail(String email);

    /**
     *
     * @return
     */
    public abstract String getEmail();

    /**
     *
     * @param privileges
     */
    public abstract void setUsertype(int privileges);

    /**
     *
     * @return
     */
    public abstract int getUsertype();

    /**
     *
     * @param firstName
     */
    public abstract void setFirstName(String firstName);

    /**
     *
     * @return
     */
    public abstract String getFirstName();

    /**
     *
     * @param lastName
     */
    public abstract void setLastName(String lastName);

    /**
     *
     * @return
     */
    public abstract String getLastName();

    /**
     *
     * @return
     */
    public abstract List<Message> getSentMessages();

    /**
     *
     * @return
     */
    public abstract List<Message> getReceivedMessages();
    
}

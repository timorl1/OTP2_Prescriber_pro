/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.*;

/**
 *
 * @author Timo
 */

public interface User_IF {
    public abstract void setUsername(String username);
    public abstract String getUsername();
    public abstract void setUserID(int userID);
    public abstract int getUserID();
    public abstract void setPassword(String password);
    public abstract String getPassword();
    public abstract void setEmail(String email);
    public abstract String getEmail();
    public abstract void setUsertype(int privileges);
    public abstract int getUsertype();
    public abstract void setFirstName(String firstName);
    public abstract String getFirstName();
    public abstract void setLastName(String lastName);
    public abstract String getLastName();
    
}

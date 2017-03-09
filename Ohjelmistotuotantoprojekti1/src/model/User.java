/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.*;

/**
 *
 * @author Johanna, Timo
 */
@Entity(name="user")
@Table(name="user")
public class User implements User_IF{
    
    @Id
    @Column(name="username")
    private String username;
    @Column(name="userID")
    private int userID;
    @Column(name="firstname")
    private String firstName;
    @Column(name="lastname")
    private String lastName;
    @Column(name="email")
    private String email;
    @Column(name="usertype")
    private int usertype;
    @Column(name="password")
    private String password;
    
    
    public User(){}
    
    @Override
    public int getUserID() {
        return userID;
    }
    
    @Override
    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    @Override
    public String getFirstName() {
        return firstName;
    }
    
    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    @Override
    public String getLastName() {
        return lastName;
    }
    
    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    @Override
    public String getUsername() {
        return username;
    }
    
    @Override
    public void setUsername(String username) {
        this.username = username;
    }
    
    @Override
    public String getEmail() {
        return email;
    }
    
    @Override
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public int getUsertype() {
        return usertype;
    }
    
    @Override
    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }
    
    @Override
    public String getPassword() {
        return password;
    }
    
    @Override
    public void setPassword(String password) {
        this.password = password;
    }    
}

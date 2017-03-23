/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
@Entity(name="user")
@Table(name="user")
public class User implements User_IF{
    
    private String username;
    private int userID;
    private String firstName;
    private String lastName;
    private String email;
    private int usertype;
    private String password;
    
    
    
    private List<Message> sentMessages = new ArrayList();
    
    //@JoinTable(name="receiver", joinColumns = @JoinColumn(name = "username"), inverseJoinColumns = @JoinColumn(name = "MessageID",referencedColumnName = "MessageID"))
    private List<Message> receivedMessages = new ArrayList();
    
    public User(){}
    
    @Column(name="userID")
    @Override
    public int getUserID() {
        return userID;
    }
    
    @Override
    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    @Column(name="firstname")
    @Override
    public String getFirstName() {
        return firstName;
    }
    
    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    @Column(name="lastname")
    @Override
    public String getLastName() {
        return lastName;
    }
    
    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    @Id
    @Column(name="username")
    @Override
    public String getUsername() {
        return username;
    }
    
    @Override
    public void setUsername(String username) {
        this.username = username;
    }
    
    @Column(name="email")
    @Override
    public String getEmail() {
        return email;
    }
    
    @Override
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name="usertype")
    @Override
    public int getUsertype() {
        return usertype;
    }
    
    @Override
    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }
    
    @Column(name="password")
    @Override
    public String getPassword() {
        return password;
    }
    
    @Override
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString(){
        return this.userID+": "+this.username;
    }

    @OneToMany
    @JoinColumn(name="sender")
    @Override
    public List<Message> getSentMessages() {
        return sentMessages;
    }
    @OneToMany
    @JoinColumn(name="receiver")
    @Override
    public List<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public void setSentMessages(List<Message> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public void setReceivedMessages(List<Message> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }
}

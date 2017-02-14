/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.*;

/**
 *
 * @author Johanna
 */

@Entity
@Table
public class User {
    
    @Column(name="userid")
    private int id;
    @Id
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String priviledges;
    @Column
    private String email;
    
    
    public User(){}
    
    public User(int id, String username, String passw, String priv, String email){
        this.id = id;
        this.username = username;
        this.password = passw;
        this.priviledges = priv;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPriviledges() {
        return priviledges;
    }

    public void setPriviledges(String priviledges) {
        this.priviledges = priviledges;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}

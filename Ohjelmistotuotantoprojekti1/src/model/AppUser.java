/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.UserDAO;
import dao.UserDAO_IF;

/**
 *
 * @author joosiika
 */
public class AppUser implements AppUser_IF{
    private final UserDAO_IF userdb;
    private User_IF user;
    //private User user;
    
    private boolean authenticated;

    public AppUser() {
        this.userdb = new UserDAO();
        this.authenticated = false;
    }
    
    //Gets authentication from the server and returns the the authenticated user to the controller
    //If authentication fails, returns null
    @Override
    public void setUser(String username) {
        this.user = this.userdb.getUser(username);
    }

    @Override
    public int getUserPrivileges() {
        return this.user.getPrivileges();
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void authenticate(String password) {
        if (this.user != null) {
            if (this.user.getPassword().equals(password)) {
                this.authenticated = true;
            }
        }
    }

    @Override
    public User_IF getUser() {
        return this.user;
    }
}

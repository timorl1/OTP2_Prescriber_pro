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
public class Authenticator implements Authenticator_IF{
    private final UserDAO_IF userdb;

    public Authenticator() {
        this.userdb = new UserDAO();
    }
    
    //Gets authentication from the server and returns the the authenticated user to the controller
    //If authentication fails, returns null
    @Override
    public User authenticateUser(String username, String password) {
        User user = userdb.getUser(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                System.out.println(user.getEmail());
                return user;
            }
        }
        return null;
    }
}

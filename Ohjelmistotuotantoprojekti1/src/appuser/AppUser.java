/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appuser;

import java.util.Observable;
import resources.user.User_IF;
import resources.user.User;
import resources.user.UserDAO;
import resources.user.UserDAO_IF;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class AppUser extends Observable implements AppUser_IF {
    private final UserDAO_IF userdb;
    private User_IF user = null;
    
    private boolean authenticated;
    private AppUserState state;

    public AppUser() {
        this.userdb = new UserDAO();
        this.authenticated = false;
        //this.state = LoggedOut.getInstance();
    }
    
    protected void changeState(AppUserState state) {
        this.state = state;
        this.setChanged();
        this.notifyObservers(this.state);
    }
    
    //Gets authentication from the server and returns the the authenticated user to the controller
    //If authentication fails, returns null
    @Override
    public boolean setUser(String username) {
        this.user = (User)this.userdb.getUser(username);
        if(this.user != null){
            return true;
        }
        return false;   
        
    }

    @Override
    public int getUserPrivileges() {
        return this.user.getUsertype();
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

    @Override
    public void setAuthenticate(boolean authenticated) {
        this.authenticated = authenticated;
    }
}

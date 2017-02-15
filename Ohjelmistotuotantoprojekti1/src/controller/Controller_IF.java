package controller;

import model.User;

/**
 *
 * @author Timo
 */
public interface Controller_IF {
    public abstract User[] getUsers();
    public abstract User getUser(String username);
    public abstract void calculateDose();
    public abstract void checkDose();
    public abstract void setPriviledges();
    public abstract void createUser();
    public abstract void deleteUser();
}

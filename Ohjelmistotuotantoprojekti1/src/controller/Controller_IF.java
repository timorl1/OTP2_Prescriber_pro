package controller;

import java.util.List;
import model.User;

/**
 *
 * @author Timo
 */
public interface Controller_IF {
    public abstract List<User> getUsers();
    public abstract User getUser();
    public abstract void calculateDose();
    public abstract void checkDose();
    public abstract void setPriviledges();
    public abstract void createUser();
    public abstract void deleteUser();
}

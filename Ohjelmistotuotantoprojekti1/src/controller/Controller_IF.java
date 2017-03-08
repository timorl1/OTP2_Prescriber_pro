package controller;

import java.util.List;
import model.User;
import model.User_IF;

/**
 *
 * @author Timo
 */
public interface Controller_IF {
    public abstract List<User_IF> getUsers();
    public abstract User_IF getUser();
    public abstract void calculateDose();
    public abstract void checkDose();
    public abstract void setPriviledges();
    public abstract void createUser();
    public abstract void deleteUser();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.User_IF;


/**
 * Interface that defines methods for CRUD-operations for users in database
 * @author Timo
 */
public interface UserDAO_IF {

    /**
     * Updates a user
     * @param user
     * @return true if user is updated, false if not
     *
     * @param user
     * @return
     */
    public abstract boolean updateUser(User_IF user);

    /**
     * Gets all users from database
     * @return list of user objects
     */
    public abstract List <User_IF> getUsers();

    /**
     */
    public abstract User_IF getUser(String username);

    /**
     * Gets user by username
     * @param username
     * @return user object
     */
    public abstract boolean deleteUser(User_IF user);

    /**
     * Deletes user from database
     * @param user
     * @return true if user is deleted, false if not
     */
    public abstract boolean createUser(User_IF user);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.user;

import java.util.List;


/**
 * Interface that defines methods for CRUD-operations for users in database
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public interface UserDAO_IF {

    /**
     * Updates a user
     * @param user user to be updated
     * @return true if user is updated, false if not
     */
    public abstract boolean updateUser(User_IF user);

    /**
     * Gets all users from database
     * @return list of user objects
     */
    public abstract List <User_IF> getUsers();

    /**
     * Gets user by username
     * @param username user's username
     * @return user object
     */
    public abstract User_IF getUser(String username);

    
    /**
     * Deletes user from database
     * @param user user to be deleted
     * @return true if user is deleted, false if not
     */
    public abstract boolean deleteUser(User_IF user);
    
    /**
     * Creates user
     * @param user user to be created
     * @return true if user is created, false if not
     */
    public abstract boolean createUser(User_IF user);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.User;
import model.User_IF;


/**
 *
 * @author Timo
 */
public interface UserDAO_IF {

    /**
     *
     * @param user
     * @return
     */
    public abstract boolean updateUser(User_IF user);

    /**
     *
     * @return
     */
    public abstract List <User_IF> getUsers();

    /**
     *
     * @param username
     * @return
     */
    public abstract User_IF getUser(String username);

    /**
     *
     * @param user
     * @return
     */
    public abstract boolean deleteUser(User_IF user);

    /**
     *
     * @param user
     * @return
     */
    public abstract boolean createUser(User_IF user);
}

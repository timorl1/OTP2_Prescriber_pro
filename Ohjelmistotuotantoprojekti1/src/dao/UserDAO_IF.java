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
    public abstract boolean updateUser(User_IF user);
    public abstract List <User_IF> getUsers();
    public abstract User_IF getUser(String username);
    public abstract boolean deleteUser(User_IF user);
    public abstract boolean createUser(User_IF user);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.User;

/**
 *
 * @author Timo
 */
public interface UserDAO_IF {
    public abstract boolean updateUser(User user);
    public abstract List<User> getUsers();
    public abstract User getUser(String username);
    public abstract boolean deleteUser(User user);
    public abstract boolean createUser(User user);
}

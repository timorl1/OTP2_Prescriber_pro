package controller;

import dao.UserDAO_IF;
import gui.MainGUI_IF;
import java.util.List;
import model.User;


/**
 *
 * @author Timo
 */
public class Controller implements Controller_IF {
    
    private UserDAO_IF userdao;
    private MainGUI_IF gui;
    
    public Controller(MainGUI_IF gui, UserDAO_IF userdao){
        this.gui = gui;
        this.userdao = userdao;
    }

    @Override
    public List<User> getUsers() {
        return userdao.getUsers();
    }

    @Override
    public User getUser() {
        User user = userdao.getUser("HAKEE OLION USERNAMELLA, SE TULEE GUISTA");
        return user;
    }

    @Override
    public void calculateDose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void checkDose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPriviledges() {
        User user = userdao.getUser("USERNAME; TÄMÄ TULEE GUISTA");
        user.setPrivileges(0); // TÄHÄN TULEE KANSSA GUISTA ARVO
        if(userdao.updateUser(user)){
         //TRUE, MENEE VARMAAN JOKU VIESTI GUI   
        }else{
            //FALSE, MENEE VARMAAN JOKU VIESTI GUI
        }
    }
    
    @Override
    public void createUser() {
        
        
    }

    @Override
    public void deleteUser() {
        User user =userdao.getUser("USERNAME; TÄMÄ TULEE GUISTA");
        if(userdao.deleteUser(user)){
            //TRUE, VIESTI GUILLE POISTOSTA
        }else{
            //FALSE, VIESTI GUILLE POISTOSTA
        }
    }
    
    
}

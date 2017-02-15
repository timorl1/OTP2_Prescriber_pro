/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consoletests;

import dao.PatientDAO;
import dao.UserDAO;
import dao.UserDAO_IF;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Diagnose;
import model.Drug;
import model.Drugs;
import model.Patient;
import model.Prescription;
import model.User;

/**
 *
 * @author Timo
 */
public class UserDAOCRUDTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        char select;
        UserDAO_IF userdao = new UserDAO();
        User user = new User();
        
        do {
            System.out.println("\n\t\t\t1. Lisää käyttäjä.");
            System.out.println("\t\t\t2. Näytä käyttäjät.");
            System.out.println("\t\t\t3. Muokkaa käyttäjää.");
            System.out.println("\t\t\t4. Poista käyttäjä.");
            System.out.println("\t\t\t5. Lopeta.");
            System.out.print("\n\n"); // tehdään tyhjiä rivejä
            select = Reader.readChar();
            switch (select) {
                case '1':
                    System.out.println("Valitse potilas: ");
                    
                    break;
                case '2':
                    for(User users : userdao.getUsers()){
                        System.out.println("Id:"+users.getId()+", username:"+users.getUsername()+
                                ", priviledges:"+users.getPriviledges());
                    }
                    break;
                case '3':
                    System.out.println("Valitse muokattava käyttäjänimi: ");
                    for(User users : userdao.getUsers()){
                        System.out.println("Id: "+users.getId()+", username: "+users.getUsername()+
                                ", priviledges: "+users.getPriviledges());
                    }
                    user = userdao.getUser(Reader.readLine());
                    System.out.println("Anna uusi käyttöoikeus");
                    user.setPriviledges(Reader.readInt());
                    if(userdao.updateUser(user) == !false){
                    System.out.println("Muokkaus onnistui");
                    }else{
                        System.out.println("Muokkaus epäonnistui");
                    }                    
                    break;
                    
                case '4':
                    System.out.println("Valitse poistettava käyttäjä käyttäjänimen perusteella: ");
                    
                    for(User users : userdao.getUsers()){
                        System.out.println("Id: "+users.getId()+", username: "+users.getUsername()+
                                ", priviledges: "+users.getPriviledges());
                    }
                    user = userdao.getUser(Reader.readLine());
                    boolean result = userdao.deleteUser(user);
                    
                    
                    if (result == true){
                        System.out.println("poisto onnistui");
                    } else {
                        System.out.println("poisto epäonnistui");
                    }
                    
                case '5':
                    break;
            }
        } while (select != '5');
        System.exit(0);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consoletests;

import dao.UserDAO;
import dao.UserDAO_IF;
import model.User;
import dao.EmployeeDAO;
import dao.EmployeeDAO_IF;
import model.Employee;
import java.util.List;
import java.util.ArrayList;
import model.User_IF;


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
        User_IF user = new User();
        EmployeeDAO employeedao = new EmployeeDAO();
        Employee employee = new Employee();
        
        
        
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
                    
                    System.out.println("Valitse lisättävä käyttäjä (id): ");
                    //employeedao = new EmployeeDAO();
                    for  (Employee e: employeedao.readEmployees()){
                        System.out.println("id: " + e.getUserID() + " " + e.getFirstName() +" "+ e.getLastName());
                    } 
                    
                                        
                    int id = Reader.readInt();
                    employee = employeedao.readEmployee(id);
                    System.out.println("\n Valittu käyttäjä: "+employee.getFirstName()+" "+employee.getLastName());
                    user.setUserID(id);
                    user.setFirstName(employee.getFirstName());
                    user.setLastName(employee.getLastName());
                    System.out.println("Valitse käyttäjätunnus: ");
                    user.setUsername(Reader.readLine());
                    
                    System.out.println("Valitse salasana: ");
                    user.setPassword(Reader.readLine());
                    
                    user.setEmail(employee.getEmail());
                    
                    if (employee.getTitle().equals("Hoitaja")){
                        user.setPrivileges(1);
                    }else if (employee.getTitle().equals("Lääkäri")){
                        user.setPrivileges(2);
                    }else if (employee.getTitle().equals("Admin")){
                        user.setPrivileges(3);
                    } else {
                        System.out.println("EI käyttöoikeutta");
                    }
                   
                    
                    userdao.createUser(user);
                    
                    if(userdao.createUser(user) == !false){
                        System.out.println("Lisäys onnistui");
                        System.out.println("Luodun käyttäjän tiedot: \n id: "+ user.getUserID()+", käyttäjänimi: "+user.getUsername()+", Salasana: " +user.getPassword()+", sähköposti: " + user.getEmail()+ ", käyttöoikeus: " + user.getPrivileges());
                    } else{ 
                           System.out.println("Lisäys epäonnistui"); 
                    }                   
                    
                    break;
                  
                case '2':
                    for(User_IF users : userdao.getUsers()){
                        System.out.println("Id:"+users.getUserID()+", username:"+users.getUsername()+
                                ", priviledges:"+users.getPrivileges());
                    }
                    break;
                case '3':
                    for(User_IF users : userdao.getUsers()){
                        System.out.println("Id: "+users.getUserID()+", username: "+users.getUsername()+
                                ", priviledges: "+users.getPrivileges());
                    }
                    System.out.println("Valitse muokattava käyttäjänimi: ");
                    user = userdao.getUser(Reader.readLine());
                    System.out.println("Anna uusi käyttöoikeus");
                    user.setPrivileges(Reader.readInt());
                    if(userdao.updateUser(user)){
                    System.out.println("Muokkaus onnistui");
                    }else{
                        System.out.println("Muokkaus epäonnistui");
                    }                    
                    break;
                    
                case '4':
                    System.out.println("Valitse poistettava käyttäjä käyttäjänimen perusteella: ");
                    
                    for(User_IF users : userdao.getUsers()){
                        System.out.println("Id: "+users.getUserID()+", username: "+users.getUsername()+
                                ", priviledges: "+users.getPrivileges());
                    }
                    user = userdao.getUser(Reader.readLine());
                    
                    
                    
                    if (userdao.deleteUser(user)){
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

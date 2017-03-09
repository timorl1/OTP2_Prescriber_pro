/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consoletests;

import dao.MessageDAO;
import dao.MessageDAO_IF;
import java.util.List;
import model.Message;
import model.User;

/**
 *
 * @author Johanna
 */
public class MessageDAOTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MessageDAO_IF messagedao = new MessageDAO();
            
        char select;
         do {
            System.out.println("\n\t\t\t1. Lue viesti");
            System.out.println("\t\t\t2. Näytä viestit");
            System.out.println("\t\t\t3. Muokkaa käyttäjää.");
            System.out.println("\t\t\t4. Poista käyttäjä.");
            System.out.println("\t\t\t5. Lopeta.");
            System.out.print("\n\n"); // tehdään tyhjiä rivejä
            select = Reader.readChar();
            switch (select) {
                
                case '1':
                    
                    System.out.println("Valitse luettava viesti (id): ");
                    int id = Reader.readInt();
                    Message m = messagedao.readMessage(id);
                    
                    System.out.println("Message id : "+m.getMessageID());
                    System.out.println("Message: "+m.getMessage());
                    System.out.println("Meddage date: "+m.getDate());
                    System.out.println("sent"+ m.getReceivedMessages());

                    break;
                  
                case '2':
                    
                    System.out.println("Anna käyttäjätunnus : ");
                    String uname = Reader.readLine();
                    
                    List<User> test = messagedao.readMessages(uname);
                    
                    for(User me : test){
                        System.out.println(me.toString());
                    }
                    
                    break;
                case '3':
                           
                    break;
                    
                case '4':
                 
                 break;   
                case '5':
                    break;
            }
        } while (select != '5');
        System.exit(0);
    }
    
    
    
}

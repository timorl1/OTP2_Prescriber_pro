/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consoletests;

import dao.MessageDAO;
import dao.MessageDAO_IF;
import dao.UserDAO;
import dao.UserDAO_IF;
import java.util.List;
import model.Message;
import model.User;
import model.User_IF;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class MessageDAOTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MessageDAO_IF messagedao = new MessageDAO();
        UserDAO_IF userDAO = new UserDAO();
        char select;
        String uname;
         do {
            System.out.println("\n\t\t\t1. Lue viesti");
            System.out.println("\t\t\t2. Näytä lähetyt viestit");
            System.out.println("\t\t\t3. Näytä vastaanotetut viestit.");
            System.out.println("\t\t\t4. Lisää viesti.");
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
                    System.out.println("Message date: "+m.getDate());
                    System.out.println("sent: "+ m.getSender().getFirstName()+" "+m.getSender().getLastName());

                    break;
                  
                case '2':
                    
                    System.out.println("Anna käyttäjätunnus : ");
                    uname = Reader.readLine();
                    User user = (User) userDAO.getUser(uname);
                    List<Message> test = messagedao.readSentMessages(user);
                    
                    for(Message me : test){
                        System.out.println(me.toString());
                    }
                    
                    break;
                case '3':
                    System.out.println("Anna käyttäjätunnus : ");
                    uname = Reader.readLine();
                    User user1 = (User) userDAO.getUser(uname);
                    List<Message> test1 = messagedao.readReceivedMessages(user1);
                    
                    for(Message me : test1){
                        System.out.println(me.toString());
                    }
                    break;
                case '4':
                    System.out.println("Anna käyttäjätunnuksesi: ");
                    String sName = Reader.readLine();
                    User userSender = (User) userDAO.getUser(sName);
                    System.out.println("Anna vastaanottaja tunnus: ");
                    String rName = Reader.readLine();
                    User userReceiver = (User) userDAO.getUser(rName);
                    System.out.println("Anna aihe: ");
                    String title = Reader.readLine();
                    System.out.println("Kirjoita viesti: ");
                    String message = Reader.readLine();
                    Message mes = new Message();
                    mes.setMessage(message);
                    mes.setTitle(title);
                    mes.setSender(userSender);
                    mes.setReceiver(userReceiver);
                    messagedao.createMessage(mes);
                    break;   
                case '5':
                    break;
            }
        } while (select != '5');
        System.exit(0);
    }
    
    
    
}

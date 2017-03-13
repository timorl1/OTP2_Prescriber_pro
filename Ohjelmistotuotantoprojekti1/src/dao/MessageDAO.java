/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Message;
import model.User;
import model.User_IF;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author Johanna
 */
public class MessageDAO implements MessageDAO_IF{
    
    SessionFactory sf;
    final StandardServiceRegistry reg;
//    final StandardServiceRegistry reg2;
    
    private Session session;
    private Transaction transaction;
    
    

    public MessageDAO() {
        sf = null;
        reg = new StandardServiceRegistryBuilder().configure("applicationdb.cfg.xml").build();

      //  reg2 = new StandardServiceRegistryBuilder().configure("applicationdbjenkins.cfg.xml").build();
        try {
            sf = new MetadataSources(reg).buildMetadata().buildSessionFactory();
        }catch (Exception e){
            System.out.println("Session failed to initialize.");
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(reg);
                 /*   try{
                        System.out.println("Trying to connect with Jenkins");
                        
                        sf = new MetadataSources(reg2).buildMetadata().buildSessionFactory();
                    }catch (Exception e3){
                        System.err.println("Session failed to initialize.");
                        e3.printStackTrace();
                        StandardServiceRegistryBuilder.destroy(reg2);
                        System.exit(-1);
                    } */
        }
    }
    @Override
    public void finalize() {
        boolean success = false;
        do {
            try {
                if (reg != null) {
                    StandardServiceRegistryBuilder.destroy(reg);
                }
                success = true;
            } catch (Exception e) {
                System.out.println("DB connection not shutting down. Retrying...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        } while (!success);
        System.out.println("DB connection shut down.");
    }


    @Override
    public Message readMessage(int id) {
        Message message = null;
        session = sf.openSession();
        try {
            session.beginTransaction();
            session.load(message = new Message(), id);
            session.getTransaction().commit();
            Hibernate.initialize(message.getSender());
            Hibernate.initialize(message.getReceiver());
        } catch (Exception e) {
            System.out.println("Caught an error while reading resources.");
        } finally {
            session.close();
        }
        return message;
    }

    @Override
    public List<Message> readSentMessages(User user) {   
        List<Message> result = null;
        session = sf.openSession();
        try {
            session.beginTransaction();
            
            result = session.createQuery("from message where sender = "+"'"+user.getUsername()+"'").getResultList();
            session.getTransaction().commit();
            for(Message messages : result){
                Hibernate.initialize(messages.getReceiver());
                Hibernate.initialize(messages.getSender());
                Hibernate.initialize(messages.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Caught an error while reading resources.");
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }
    @Override
    public List<Message> readReceivedMessages(User user) {   
        List<Message> result = null;
        session = sf.openSession();
        try {
            session.beginTransaction();
            
            result = session.createQuery("from message where receiver = "+"'"+user.getUsername()+"'").getResultList();
            session.getTransaction().commit();
            for(Message messages : result){
                Hibernate.initialize(messages.getReceiver());
                Hibernate.initialize(messages.getSender());
                Hibernate.initialize(messages.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Caught an error while reading resources.");
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public boolean createMessage(Message message) {
        session = sf.openSession();
        transaction = null;
        boolean success = false;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(message);
            transaction.commit();
            success = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Inserting an entry to DB failed.");
        } finally {
            session.close();
        }
        return success;
    }
    
}

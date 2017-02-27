/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author Timo
 */
public class UserDAO implements UserDAO_IF {
    
    SessionFactory sf;
    final StandardServiceRegistry reg;

    private Session session;
    private Transaction transaction;
    
    // Builds session factory
    public UserDAO(){
        sf = null;
        reg = new StandardServiceRegistryBuilder().configure("applicationdb.cfg.xml").build();

        try {
            sf = new MetadataSources(reg).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            System.err.println("Session failed to initialize.");
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(reg);
            System.exit(-1);
        }
    }
    
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
    
    //Used for updating user information to database
    @Override
    public boolean updateUser(User user) {
        session = sf.openSession();
        transaction = null;
        
        try {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Updating an entry in DB failed.");
        } finally {
            session.close();
        }
        return false;
    }

    // Gets all users from database and returns them as array
    @Override
    public List<User> getUsers() {
        List<User> users = null;
        session = sf.openSession();
        try {
            session.beginTransaction();
            users = session.createQuery("from user").getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Lukeminen ei onnistunut");
        } finally {
            session.close();
        }
        return users;
    }
    
    // Gets user from database identified by username
    @Override
    public User getUser(String username) {
        User user = null;
        session = sf.openSession();
	transaction = session.beginTransaction();
            try{
                user = new User();
                session.load(user, username);
                session.getTransaction().commit();
			
		}catch(Exception e){
			System.out.println("Lukeminen ei onnistunut");
		}finally{
			session.close();
		}
        return user;
    }
    
    //Deletes user from database identified by username 
    @Override
    public boolean deleteUser(User user) {
        
        session = sf.openSession();
        boolean success = false;
        try {
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
            success = true;
        } catch (Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Deleting an entry from DB failed.");
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return success;
    }

    @Override
    public boolean createUser(User user) {
        return false;
    }

    
}

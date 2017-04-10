/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.app;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class UserDAO implements UserDAO_IF {
    
    SessionFactory sf = ApplicationDBConnection.getInstance().getSessionFactory();
    
    private Session session;
    private Transaction transaction;
    
   
    public UserDAO(){
    }
       
    
    
    //Used for updating user information to database
    @Override
    public boolean updateUser(User_IF user) {
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
    public List<User_IF> getUsers() {
        List<User_IF> users = null;
        session = sf.openSession();
        try {
            session.beginTransaction();
            users = session.createQuery("from user").getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Caught an error while reading resources.");
        } finally {
            session.close();
        }
        return users;
    }
    
    // Gets user from database identified by username
    @Override
    public User_IF getUser(String username) {
        User_IF user = null;
        session = sf.openSession();
	transaction = session.beginTransaction();
            try{
                user = new User();
                session.load(user, username);
                session.getTransaction().commit();
                Hibernate.initialize(user.getSentMessages());
                Hibernate.initialize(user.getReceivedMessages());
		return user;
		}catch(Exception e){
			System.out.println("Caught an error while reading resources.");
                        return null;
		}finally{
			session.close();
		}
    }
    
    //Deletes user from database identified by username 
    @Override
    public boolean deleteUser(User_IF user) {
        
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
    public boolean createUser(User_IF user) {
            
        session = sf.openSession();
        transaction = null;
        boolean success = false;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
            success = true;
        } catch (Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("\"Inserting an entry in DB failed.\"");
            e.printStackTrace();
        } finally {
            session.close();
        }
        return success;
    }  
    
  
}


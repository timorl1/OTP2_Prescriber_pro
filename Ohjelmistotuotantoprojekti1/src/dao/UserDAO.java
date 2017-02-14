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

    @Override
    public User[] getUsers() {
        User [] users = null ;
		session = sf.openSession();
		transaction = session.beginTransaction();
		try{
                    @SuppressWarnings("unchecked")
                    List<User> result = session.createQuery( "from user" ).getResultList();
                    users = result.toArray(new User[result.size()]);
                    session.getTransaction().commit();
			
		}catch(Exception e){
			System.out.println("Lukeminen ei onnistunut");
			throw e;
		}finally{
			session.close();
		}
	return users;
    }

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
			throw e;
		}finally{
			session.close();
		}
        return user;
    }
}

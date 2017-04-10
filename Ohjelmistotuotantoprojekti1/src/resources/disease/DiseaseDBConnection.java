/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.disease;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *Class is used to open hibernate session factory
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class DiseaseDBConnection {
    
    private static final DiseaseDBConnection INSTANCE = new DiseaseDBConnection();
    private SessionFactory sf;
    final StandardServiceRegistry reg;
    final StandardServiceRegistry reg2;
    /**
     * Opens sessionfactory for disease database
     */
    private DiseaseDBConnection() {
        sf = null;
        reg = new StandardServiceRegistryBuilder().configure("diseasedb.cfg.xml").build();

        reg2 = new StandardServiceRegistryBuilder().configure("diseasedbjenkins.cfg.xml").build();
        try {
            sf = new MetadataSources(reg).buildMetadata().buildSessionFactory();
        }catch (Exception e){
            System.out.println("Session failed to initialize.");
            StandardServiceRegistryBuilder.destroy(reg);
                    try{
                        System.out.println("Trying to connect with Jenkins");
                        
                        sf = new MetadataSources(reg2).buildMetadata().buildSessionFactory();
                    }catch (Exception e3){
                        System.err.println("Session failed to initialize.");
                        e3.printStackTrace();
                        StandardServiceRegistryBuilder.destroy(reg2);
                        System.exit(-1);
                    }
        }
    }
    @Override
    public void finalize(){
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
    
    public static DiseaseDBConnection getInstance() {
        return INSTANCE;
    }
    
    public SessionFactory getSessionFactory(){
        return this.sf;
    }
}

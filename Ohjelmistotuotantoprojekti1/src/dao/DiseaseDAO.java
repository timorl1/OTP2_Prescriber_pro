package dao;

import java.util.List;
import model.Diagnose;
import model.Disease;
import model.Drug;
import model.Patient;
import org.hibernate.Hibernate;
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
public class DiseaseDAO implements DiseaseDAO_IF {
    SessionFactory sf;
    final StandardServiceRegistry reg;

    private Session session;
    private Transaction transaction;

    public DiseaseDAO(){
        sf = null;
        reg = new StandardServiceRegistryBuilder().configure("diseasedb.cfg.xml").build();

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
    public Disease getDisease(int diseaseId) {
        Disease disease=null;
        session = sf.openSession();
        try {
            session.beginTransaction();
            session.load(disease = new Disease(), diseaseId);
            session.getTransaction().commit();
            Hibernate.initialize(disease.getId());
            Hibernate.initialize(disease.getDiseaseName());
            Hibernate.initialize(disease.getDiseaseDesc());
            Hibernate.initialize(disease.getAllergenList());
        } catch (Exception e) {
            System.out.println("Caught an error while reading resources.");
        } finally {
            session.close();
        }
        return disease;
    }

    
    
}

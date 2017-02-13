package dao;

import java.util.List;
import model.Drug;
import model.Drugs;
import org.hibernate.*;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.*;
import org.hibernate.boot.MetadataSources;

/**
 *
 * @author joosiika
 */
public class DrugDAO implements DrugDAO_IF{
    SessionFactory sf;
    final StandardServiceRegistry reg;

    private Session session;
    private Transaction transaction;
    
    public DrugDAO() {
        sf = null;
        reg = new StandardServiceRegistryBuilder().configure("drugdb.cfg.xml").build();

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
    public Drug readDrug(int SN) {
        Drug drug = null;
        session = sf.openSession();
        try {
            session.beginTransaction();
            session.load(drug = new Drug(), SN);
            session.getTransaction().commit();
            Hibernate.initialize(drug.getActiveAgents());
            Hibernate.initialize(drug.getAllergens());
            Hibernate.initialize(drug.getCommonAdverseEffects());
            Hibernate.initialize(drug.getRareAdverseEffects());
        } catch (Exception e) {
            System.out.println("Caught an error while reading resources.");
        } finally {
            session.close();
        }
        return drug;
    }

    @Override
    public Drugs readDrugs() {
        List<Drug> result = null;
        session = sf.openSession();
        try {
            session.beginTransaction();
            result = session.createQuery("from lääke").getResultList();
            session.getTransaction().commit();
            for (Drug drug : result) {
                Hibernate.initialize(drug.getActiveAgents());
                Hibernate.initialize(drug.getAllergens());
                Hibernate.initialize(drug.getCommonAdverseEffects());
                Hibernate.initialize(drug.getRareAdverseEffects());
            }
        } catch (Exception e) {
            System.out.println("Caught an error while reading resources.");
            e.printStackTrace();
        } finally {
            session.close();
        }
        Drugs drugs = new Drugs(result);
        return drugs;
    }
    
}

package resources.data;

import java.util.List;
import org.hibernate.*;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.*;
import org.hibernate.boot.MetadataSources;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class DrugDAO implements DrugDAO_IF {

    SessionFactory sf;
    final StandardServiceRegistry reg;
    final StandardServiceRegistry reg2;

    private Session session;
    private Transaction transaction;

    //Builds session factory
    public DrugDAO() {
        sf = null;
        reg = new StandardServiceRegistryBuilder().configure("drugdb.cfg.xml").build();
        reg2 = new StandardServiceRegistryBuilder().configure("drugdbjenkins.cfg.xml").build();
        try {
            sf = new MetadataSources(reg).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            System.out.println("Session failed to initialize.");
            StandardServiceRegistryBuilder.destroy(reg);
                    try{
                        System.out.println("Trying with jenkins");
                        
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

    //Get a single drug from database identified by serial number
    @Override
    public Drug readDrug(int SN) {
        Drug drug = null;
        session = sf.openSession();
        try {
            session.beginTransaction();
            session.load(drug = new Drug(), SN);
            session.getTransaction().commit();
            Hibernate.initialize(drug.getDrugActiveAgents());
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

    //Gets all drugs in database and returns them as a List
    @Override
    public List<Drug> readDrugs() {
        List<Drug> drugs = null;
        session = sf.openSession();
        try {
            session.beginTransaction();
            drugs = session.createQuery("from lääke").getResultList();
            session.getTransaction().commit();
            for (Drug drug : drugs) {
                Hibernate.initialize(drug.getDrugActiveAgents());
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
        return drugs;
    }

}

package resources.data;

import resources.data.Disease;
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
public class DiseaseDAO implements DiseaseDAO_IF {
    SessionFactory sf;
    final StandardServiceRegistry reg;
    final StandardServiceRegistry reg2;

    private Session session;
    private Transaction transaction;

    public DiseaseDAO(){
        sf = null;
        reg = new StandardServiceRegistryBuilder().configure("diseasedb.cfg.xml").build();

        reg2 = new StandardServiceRegistryBuilder().configure("diseasedbjenkins.cfg.xml").build();
        try {
            sf = new MetadataSources(reg).buildMetadata().buildSessionFactory();
        }catch (Exception e){
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

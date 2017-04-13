package resources.disease;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class DiseaseDAO implements DiseaseDAO_IF {
    SessionFactory sf = DiseaseDBConnection.getInstance().getSessionFactory();
    

    private Session session;
    private Transaction transaction;

    public DiseaseDAO(){
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

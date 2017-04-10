package resources.drug;

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

    SessionFactory sf = DrugDBConnection.getInstance().getSessionFactory();
    

    private Session session;
    private Transaction transaction;

    public DrugDAO() {
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

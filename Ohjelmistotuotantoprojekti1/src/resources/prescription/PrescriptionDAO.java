package resources.prescription;

import java.util.List;
import resources.diagnose.Diagnose;
import resources.patient.Patient;
import org.hibernate.*;
import resources.app.ApplicationDBConnection;
import resources.user.User_IF;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class PrescriptionDAO implements PrescriptionDAO_IF{
    SessionFactory sf = ApplicationDBConnection.getInstance().getSessionFactory();
    
    
    private Session session;
    private Transaction transaction;
    
    

    public PrescriptionDAO() {
    }
    

    @Override
    public boolean createPrescription(Prescription prescription) {
        session = sf.openSession();
        transaction = null;
        boolean success = false;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(prescription);
            transaction.commit();
            success = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Inserting an entry to DB failed.");
        } finally {
            session.close();
        }
        return success;
    }

    @Override
    public Prescription readPrescription(int id) {
        Prescription prescription = null;
        session = sf.openSession();
        try {
            session.beginTransaction();
            session.load(prescription = new Prescription(), id);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Caught an error while reading resources.");
        } finally {
            session.close();
        }
        return prescription;
    }
    
    @Override
    public List<Prescription> getPrescriptionsByPatient(Patient patient) {
        List<Prescription> result = null;
        session = sf.openSession();
        try {
            session.beginTransaction();
            result = session.createQuery("from prescription where patientID = "+"'"+patient.getSSN()+"'").getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Caught an error while reading resources.");
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<Prescription> readPrescriptions() {
        List<Prescription> prescriptions = null;
        session = sf.openSession();
        try {
            session.beginTransaction();
            prescriptions = session.createQuery("from prescription").getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Caught an error while reading resources.");
            e.printStackTrace();
        } finally {
            session.close();
        }
        return prescriptions;
    }

    @Override
    public List<Prescription> getPrescriptionsByDoctor(User_IF user) {
        List<Prescription> prescriptions = null;
        session = sf.openSession();
        try {
            session.beginTransaction();
            prescriptions = session.createQuery("from prescription where username = " + "'"+user.getUsername()+"'").getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Caught an error while reading resources.");
            e.printStackTrace();
        } finally {
            session.close();
        }
        return prescriptions;
    }

    @Override
    public Prescription getPrescriptionByDiagnose(Diagnose diagnose) {
        Prescription prescription = null;
        session = sf.openSession();
        try {
            session.beginTransaction();
            session.load(prescription = new Prescription(), diagnose.getId());
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Caught an error while reading resources.");
        } finally {
            session.close();
        }
        return prescription;
    }

    @Override
    public boolean updatePrescription(Prescription prescription) {
        session = sf.openSession();
        transaction = null;
        boolean success = false;
        try {
            transaction = session.beginTransaction();
            session.update(prescription);
            transaction.commit();
            success = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Updating an entry in DB failed.");
        } finally {
            session.close();
        }
        return success;
    }

    @Override
    public boolean deletePrescription(Prescription prescription) {
        session = sf.openSession();
        transaction = null;
        boolean success = false;
        try {
            transaction = session.beginTransaction();
            session.delete(prescription);
            transaction.commit();
            success = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Deleting an entry from DB failed.");
        } finally {
            session.close();
        }
        return success;
    }
}

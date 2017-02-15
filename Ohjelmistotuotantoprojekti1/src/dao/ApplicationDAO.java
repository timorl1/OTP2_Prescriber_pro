package dao;

import java.util.List;
import model.Diagnose;
import model.Doctor;
import model.Patient;
import model.Prescription;
import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.*;

/**
 *
 * @author joosiika
 */
public class ApplicationDAO implements ApplicationDAO_IF{
    SessionFactory sf;
    final StandardServiceRegistry reg;

    private Session session;
    private Transaction transaction;
    
    private DrugDAO drugdb = new DrugDAO();

    public ApplicationDAO() {
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
    
    @Override
    public void shutDown() {
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
    public List<Prescription> getPrescriptionsByDoctor(Doctor doctor) {
        List<Prescription> prescriptions = null;
        session = sf.openSession();
        try {
            session.beginTransaction();
            prescriptions = session.createQuery("from prescription where doctorID = " + doctor.getId()).getResultList();
            session.getTransaction().commit();
            /*for (Prescription prescription : result) {
                Hibernate.initialize(drug.getActiveAgents());
                Hibernate.initialize(drug.getAllergens());
                Hibernate.initialize(drug.getCommonAdverseEffects());
                Hibernate.initialize(drug.getRareAdverseEffects());
            }*/
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

package dao;

import java.util.List;
import model.Diagnose;
import model.Patient;
import model.Prescription;
import model.User_IF;

/**
 *
 * @author joosiika
 */
public interface PrescriptionDAO_IF {
    public abstract boolean createPrescription(Prescription prescription);
    public abstract Prescription readPrescription(int id);
    public abstract List<Prescription> readPrescriptions();
    public abstract List<Prescription> getPrescriptionsByPatient(Patient patient);
    public abstract List<Prescription> getPrescriptionsByDoctor(User_IF user);
    public abstract Prescription getPrescriptionByDiagnose(Diagnose diagnose);
    public abstract boolean updatePrescription(Prescription prescription);
    public abstract boolean deletePrescription(Prescription prescription);
    public abstract void shutDown();
    
}

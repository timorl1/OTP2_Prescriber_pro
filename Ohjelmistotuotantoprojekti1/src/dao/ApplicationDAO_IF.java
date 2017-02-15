package dao;

import java.util.List;
import model.Diagnose;
import model.Doctor;
import model.Patient;
import model.Prescription;

/**
 *
 * @author joosiika
 */
public interface ApplicationDAO_IF {
    public abstract boolean createPrescription(Prescription prescription);
    public abstract Prescription readPrescription(int id);
    public abstract List<Prescription> readPrescriptions();
    public abstract List<Prescription> getPrescriptionsByPatient(Patient patient);
    public abstract List<Prescription> getPrescriptionsByDoctor(Doctor doctor);
    public abstract Prescription getPrescriptionByDiagnose(Diagnose diagnose);
    public abstract boolean updatePrescription(Prescription prescription);
    public abstract boolean deletePrescription(Prescription prescription);
    public abstract void shutDown();
    
}

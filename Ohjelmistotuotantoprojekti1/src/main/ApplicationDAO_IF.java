package main;

/**
 *
 * @author joosiika
 */
public interface ApplicationDAO_IF {
    public abstract boolean createPrescription(Prescription prescription);
    public abstract Prescription readPrescription(int id);
    public abstract Prescriptions readPrescriptions();
    public abstract Prescriptions getPrescriptionsByPatient(Patient patient);
    public abstract Prescriptions getPrescriptionsByDoctor(Doctor doctor);
    public abstract Prescription getPrescriptionByDiagnose(Diagnose diagnose);
    public abstract boolean updatePrescription(Prescription prescription);
    public abstract boolean deletePrescription(Prescription prescription);
    
}

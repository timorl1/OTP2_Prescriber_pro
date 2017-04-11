package resources.prescription;

import java.util.List;
import resources.diagnose.Diagnose;
import resources.patient.Patient;
import resources.prescription.Prescription;
import resources.user.User_IF;
import resources.user.User_IF;

/**
 * Interface that defines methods for CRUD-operations for prescriptions in
 * database
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public interface PrescriptionDAO_IF {

    /**
     * Creates a new prescription
     * @param prescription prescription object
     * @return true if prescription is created, false if not
     */
    public abstract boolean createPrescription(Prescription prescription);

    /**
     * Gets prescription by id from database
     * @param id prescription's id
     * @return prescription object
     */
    public abstract Prescription readPrescription(int id);

    /**
     * Gets all of prescriptions from database
     * @return list of prescription objects
     */
    public abstract List<Prescription> readPrescriptions();

    /**
     * Gets prescriptions by patient from database
     * @param patient patient object
     * @return list of prescription objects
     */
    public abstract List<Prescription> getPrescriptionsByPatient(Patient patient);

    /**
     * Gets prescriptions by doctor from database
     * @param user user object
     * @return list of prescription objects
     */
    public abstract List<Prescription> getPrescriptionsByDoctor(User_IF user);

    /**
     * Gets prescriptions by diagnose from database
     * @param diagnose diagnose object
     * @return list of prescription objects
     */
    public abstract Prescription getPrescriptionByDiagnose(Diagnose diagnose);

    /**
     * Updates a prescription
     * @param prescription prescription object
     * @return true if prescription is updated, false if not
     */
    public abstract boolean updatePrescription(Prescription prescription);

    /**
     * Deletes a prescription from database
     * @param prescription prescription object
     * @return true if prescription is deleted, false if not
     */
    public abstract boolean deletePrescription(Prescription prescription);

    /**
     * Shuts down the connection
     */
    public abstract void shutDown();
    
}

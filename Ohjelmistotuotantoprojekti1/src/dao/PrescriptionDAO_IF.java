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

    /**
     *
     * @param prescription
     * @return
     */
    public abstract boolean createPrescription(Prescription prescription);

    /**
     *
     * @param id
     * @return
     */
    public abstract Prescription readPrescription(int id);

    /**
     *
     * @return
     */
    public abstract List<Prescription> readPrescriptions();

    /**
     *
     * @param patient
     * @return
     */
    public abstract List<Prescription> getPrescriptionsByPatient(Patient patient);

    /**
     *
     * @param user
     * @return
     */
    public abstract List<Prescription> getPrescriptionsByDoctor(User_IF user);

    /**
     *
     * @param diagnose
     * @return
     */
    public abstract Prescription getPrescriptionByDiagnose(Diagnose diagnose);

    /**
     *
     * @param prescription
     * @return
     */
    public abstract boolean updatePrescription(Prescription prescription);

    /**
     *
     * @param prescription
     * @return
     */
    public abstract boolean deletePrescription(Prescription prescription);

    /**
     *
     */
    public abstract void shutDown();
    
}

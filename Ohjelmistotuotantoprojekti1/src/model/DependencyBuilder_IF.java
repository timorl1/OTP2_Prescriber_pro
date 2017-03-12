package model;

import java.util.List;

/**
 *
 * @author joosiika
 */
public interface DependencyBuilder_IF {

    /**
     *
     * @param patient
     * @return
     */
    public abstract Patient buildPatient(Patient patient);

    /**
     *
     * @param doctor
     * @return
     */
    public abstract List<Prescription> buildDoctorPrescriptions(User_IF doctor);

    /**
     *
     * @param diagnose
     * @return
     */
    public abstract Diagnose buildDiagnose(Diagnose diagnose);

    /**
     *
     * @param prescription
     * @return
     */
    public abstract Prescription buildPrescription(Prescription prescription);
}

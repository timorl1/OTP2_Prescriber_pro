package model;

import java.util.List;

/**
 *
 * @author joosiika
 */
public interface DependencyBuilder_IF {
    public abstract Patient buildPatient(Patient patient);
    public abstract List<Prescription> buildDoctorPrescriptions(User_IF doctor);
    public abstract Diagnose buildDiagnose(Diagnose diagnose);
    public abstract Prescription buildPrescription(Prescription prescription);
}

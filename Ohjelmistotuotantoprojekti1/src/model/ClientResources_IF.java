package model;

import java.util.List;

/**
 *
 * @author joosiika
 */
public interface ClientResources_IF {
    public abstract List<Patient> getPatients();
    public abstract List<String> getPatientDetails(Patient patient);
    public abstract List<Diagnose> getPatientDiagnoses(Patient patient);
    public abstract List<Prescription> getPatientPrescriptions(Patient patient);
    public abstract List<String> getPrescriptionDetails(Prescription prescription);
    public abstract List<String> getEmployees();
    public abstract String getEmployeeDetails(String SSN);
}

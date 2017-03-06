package model;

import java.util.List;

/**
 *
 * @author joosiika
 */
public interface PrescriberPro_IF {
    public abstract List<Patient> getPatients();
    public abstract List<Diagnose> getPatientDiagnoses(Patient patient);
    public abstract List<Prescription> getPatientPrescriptions(Patient patient);
    public abstract List<Prescription> getDoctorPrescriptions(Doctor doctor);
    public abstract List<String> getPatientDetails(Patient patient);
    public abstract List<String> getDiagnoseDetails(Diagnose diagnose);
    public abstract List<String> getPrescriptionDetails(Prescription prescription);
    public abstract List<Employee> getEmployees();
    public abstract List<String> getEmployeeDetails(Employee employee);
    public abstract List<User> getUsers();
    public abstract List<String> getUserDetails();
    public abstract List<Message> getUserMessages();
    public abstract List<String> getMessageDetails();
    public abstract List<String> getDatabases();
    public abstract List<String> getDatabaseProperties();
    public abstract Prescription createPrescription();
}

package model;

import java.util.List;

/**
 *
 * @author joosiika
 */
public interface ClientResources_IF {
    public abstract List<Patient> getPatients();
    public abstract Patient getPatientDetails(Patient patient);
    public abstract List<Diagnose> getPatientDiagnoses(Patient patient);
    public abstract Diagnose getDiagnoseDetails(Diagnose diagnose);
    public abstract List<Prescription> getPatientPrescriptions(Patient patient);
    public abstract Prescription getPrescriptionDetails(Prescription prescription);
    public abstract List<Employee> getEmployees();
    public abstract Employee getEmployeeDetails(Employee employee);
    public abstract List<User> getUsers();
    public abstract User getUserDetails(User user);
    public abstract void setUserPriviledges(User user);
    public abstract void lockUser(User user);
}

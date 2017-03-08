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
    public abstract List<User_IF> getUsers();
    public abstract User_IF getUserDetails(User_IF user);
    public abstract void setUserPriviledges(User_IF user);
    public abstract void lockUser(User_IF user);
    public abstract List<Prescription> getPrescriptionsByDoctor(User_IF user);
}

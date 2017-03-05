package gui;

import java.util.List;
import model.Drug;
import model.Patient;
import model.Prescription;
import model.User;

/**
 *
 * @author joosiika
 */
public interface Controller_IF {
    public abstract void login(String username, String password);
    public abstract List<Patient> getPatients();
    public abstract void filterPatients();
    public abstract void getPatientDetails();
    public abstract void getPatientDiagnoses();
    public abstract void getPatientPrescriptions();
    public abstract List<Drug> getDrugs();
    public abstract List<String> getDrugDetails();
    public abstract List<Prescription> getPrescriptions();
    public abstract void getPrescriptionDetails();
    public abstract void getDiagnoseDetails();
    public abstract List<String> getMessages();
    public abstract List<String> getMessageDetails();
    public abstract List<User> getUsers();
    public abstract List<String> getEmployees();
    public abstract List<String> getDatabases();
}

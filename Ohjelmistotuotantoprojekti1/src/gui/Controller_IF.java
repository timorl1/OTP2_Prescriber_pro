package gui;

import java.util.List;
import model.Drug;
import model.Employee;
import model.Patient;
import model.Prescription;
import model.User;
import model.User_IF;

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
    public abstract void getDrugDetails();
    public abstract List<Prescription> getPrescriptions();
    public abstract void getPrescriptionDetails();
    public abstract void getDiagnoseDetails();
    public abstract List<String> getMessages();
    public abstract List<String> getMessageDetails();
    public abstract List<User_IF> getUsers();
    public abstract void getUserDetails();
    public abstract List<Employee> getEmployees();
    public abstract void getEmployeeDetails();
    public abstract List<String> getDatabases();
    public abstract void checkDose();
    public abstract void createNewPrescription();
    public abstract void savePrescription();
    public abstract void lockUser(User_IF user);
    public abstract void setUserPriviledges(User_IF user);
}

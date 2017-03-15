package gui;

import java.util.List;
import model.Diagnose;
import model.Drug;
import model.Employee;
import model.Message;
import model.Patient;
import model.Prescription;
import model.User;
import model.User_IF;

/**
 *
 * @author joosiika
 */
public interface Controller_IF {

    /**
     *
     * @param username
     * @param password
     */
    public abstract void login(String username, String password);

    /**
     *
     * @return
     */
    public abstract List<Patient> getPatients();
    public abstract List<Diagnose> getPatientDiagnoses();
    public abstract List<Prescription> getPatientPrescriptions();
    public abstract List<Drug> getDrugs();

    /**
     *
     */
    public abstract void getDrugDetails();
    public abstract List<Prescription> getDoctorPrescriptions();
    public abstract void getPrescriptionDetails();

    /**
     *
     */
    public abstract void getDiagnoseDetails();

    /**
     *
     * @return
     */
    public abstract List<Message> getReceivedMessages();

    /**
     *
     * @return
     */
    public abstract List<Message> getSentMessages();

    /**
     *
     */
    public abstract void getMessageDetails();

    /**
     *
     * @return
     */
    public abstract List<User_IF> getUsers();

    /**
     *
     */
    public abstract void getUserDetails();

    /**
     *
     * @return
     */
    public abstract List<Employee> getEmployees();

    /**
     *
     */
    public abstract void getEmployeeDetails();

    /**
     *
     * @return
     */
    public abstract List<String> getDatabases();

    /**
     *
     */
    public abstract void createNewPrescription();

    /**
     *
     * @return
     */
    public abstract boolean savePrescription();

    /**
     *
     * @param user
     */
    public abstract void lockUser(User_IF user);

    /**
     *
     * @param user
     */
    public abstract void setUserPriviledges(User_IF user);

    /**
     *
     * @return
     */
    public abstract boolean saveMessage();

    /**
     *
     */
    public abstract void createNewMessage();
}

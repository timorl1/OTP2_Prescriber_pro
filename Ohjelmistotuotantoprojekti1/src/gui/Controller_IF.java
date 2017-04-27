package gui;

import java.util.List;
import resources.diagnose.Diagnose;
import resources.drug.Drug;
import resources.employee.Employee;
import resources.message.Message;
import resources.patient.Patient;
import resources.prescription.Prescription;
import resources.user.User_IF;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public interface Controller_IF {

    /**
     * Method to set the current user.
     * @param username username entered by the user
     * @param password password entered by the user
     */
    public abstract void login(String username, String password);

    /**
     * Method to serve patients from the backend to the view.
     * @return list of patients
     */
    
    /**
     * Method that logsout current user
     */
    public abstract void logout();
    
    public abstract List<Patient> getPatients();
    /**
     * Method to serve patient's diagnoses from the backend to the view.
     * @return list of diagnoses
     */
    public abstract List<Diagnose> getPatientDiagnoses();
    
    /**
     * Method to serve patient's prescriptions from the backend to the view.
     * @return list of prescriptions
     */
    public abstract List<Prescription> getPatientPrescriptions();
    
    /**
     * Method to serve drugs from the backend to the view.
     * @return list of drugs
     */
    public abstract List<Drug> getDrugs();

    /**
     * Method to serve drug details from the backend to the view.
     */
    public abstract void getDrugDetails();
    
    /**
     * Method to serve prescriptions made by current user from the backend to the view.
     * @return list of prescriptions
     */
    public abstract List<Prescription> getDoctorPrescriptions();
    
    /**
     * Method to serve prescription details from the backend to the view.
     */
    public abstract void getPrescriptionDetails();

    /**
     * Method to serve diagnose details from the backend to the view.
     */
    public abstract void getDiagnoseDetails();

    /**
     * Method to serve current user's received messages from the backend to the view.
     * @return list of received messages
     */
    public abstract List<Message> getReceivedMessages();

    /**
     * Method to serve current user's sent messages from the backend to the view.
     * @return list of sent messages
     */
    public abstract List<Message> getSentMessages();

    /**
     * Method to serve message details from the backend to the view.
     */
    public abstract void getSentMessageDetails();
    
    /**
     * Method to serve message details from the backend to the view.
     */
    public abstract void getReceivedMessageDetails();

    /**
     * Method to serve users from the backend to the view.
     * @return list of users
     */
    public abstract List<User_IF> getUsers();

    /**
     * Method to serve user details from the backend to the view.
     */
    public abstract void getUserDetails();

    /**
     * Method to serve employees from the backend to the view.
     * @return list of employees
     */
    public abstract List<Employee> getEmployees();

    /**
     * Method to serve employees details from the backend to the view.
     */
    public abstract void getEmployeeDetails();

    /**
     * Method to serve list of databases from the backend to the view.
     * @return list of database names
     */
    public abstract List<String> getDatabases();

    /**
     * Method to create a new prescription in the backend and set it into the prescription form.
     */
    public abstract void createNewPrescription();

    /**
     * Method to get a prescription from view and serve it to the backend for writing to database.
     * @return success state of the save or update from the backend
     */
    public abstract boolean savePrescription();

    /**
     * Method to serve a user from the view to the backend for setting user privileges to locked state.
     * @param user to be locked
     */
    public abstract void lockUser(User_IF user);

    /**
     * Method to serve a user from the view to the backend for restoring user's privileges.
     * @param user to be unlocked
     */
    public abstract void setUserPriviledges(User_IF user);

    /**
     * Method to serve a message from the view to the backend to be written to the database.
     * @return success state from the backend
     */
    public abstract boolean saveMessage();

    /**
     * Method to create a new message in the backend and set it into the message form.
     */
    public abstract void createNewMessage();
    
    /**
     * Method to create a new user in the backend and set it into the user form.
     */
    public abstract void createNewUser();
    
    /**
     * Method to get a user from user form view and serve it to the backend for writing to database.
     * @return success state of the save or update from the backend
     */
    public abstract boolean saveUser();
    
    public abstract void updateChecker();
}

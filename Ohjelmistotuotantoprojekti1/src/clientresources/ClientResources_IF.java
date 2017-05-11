package clientresources;

import resources.diagnose.Diagnose;
import resources.employee.Employee;
import resources.message.Message;
import resources.prescription.Prescription;
import resources.user.User_IF;
import resources.patient.Patient;
import java.util.List;

/**
 *Gathers all the information for application
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public interface ClientResources_IF {

    /**
     *Gets all the patients from database
     * @return all the patients as a Patient objects List
     */
    public abstract List<Patient> getPatients();

   
    /**
     *Lists all single patients diagnoses
     * @param patient patient of whom diagnoses needs to be listed
     * @return all the of single patients Diagnose objects List
     */
    public abstract List<Diagnose> getPatientDiagnoses(Patient patient);

    /**
     *Gets details about diagnose
     * @param diagnose that you want details from
     * @return details about diagnose
     */
    public abstract Diagnose getDiagnoseDetails(Diagnose diagnose);

    /**
     *Lists patients prescriptions
     * @param patient whos prescriptions needs to be listed
     * @return list of Prescription objects
     */
    public abstract List<Prescription> getPatientPrescriptions(Patient patient);

    /**
     *Details about prescription
     * @param prescription which you want details from
     * @return details about prescription
     */
    public abstract Prescription getPrescriptionDetails(Prescription prescription);

    /**
     *Lists all the employees from database
     * @return list of employee objects
     */
    public abstract List<Employee> getEmployees();

    /**
     *Single employee from database
     * @param employee whos details you want to get
     * @return employee object 
     */
    public abstract Employee getEmployeeDetails(Employee employee);

    /**
     *Gets all the users from  database
     * @return list of users
     */
    public abstract List<User_IF> getUsers();

    /**
     *Single users details
     * @param user whos details you want to get
     * @return user object
     */
    public abstract User_IF getUserDetails(User_IF user);

    /**
     *Sets user privileges
     * @param user whos privileges are altered
     */
    public abstract void setUserPriviledges(User_IF user);

    /**
     *Sets users privileges to 0
     * @param user who you need to lock out from application
     */
    public abstract void lockUser(User_IF user);   

    /**
     *Sets up new user
     * 
     * @return new user object
     */
    public abstract User_IF createNewUser();

    /**
     *Saves the new user to database
     * @param user object that is saved
     * @return true if user is saved succesfully, false it it fails
     */
    public abstract boolean saveUser(User_IF user);
    
    public abstract Prescription createPrescription(User_IF user);
    
    public abstract boolean savePrescription(Prescription prescription);

    /**
     *List of prescriptions made by doctor that is currently logged in
     * @param user object of the user
     * @return list of prescription objects
     */

    public abstract List<Prescription> getPrescriptionsByDoctor(User_IF user);
}

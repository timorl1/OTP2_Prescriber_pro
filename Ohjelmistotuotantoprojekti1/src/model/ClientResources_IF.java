package model;

import java.util.List;

/**
 *Gathers all the information for application
 * @author joosiika, Timo
 */
public interface ClientResources_IF {

    /**
     *Gets all the patients from database
     * @return all the patients as a Patient objects List
     */
    public abstract List<Patient> getPatients();

   
    /**
     *Lists all single patients diagnoses
     * @param patient
     * @return all the of single patients Diagnose objects List
     */
    public abstract List<Diagnose> getPatientDiagnoses(Patient patient);

    /**
     *
     * @param diagnose
     * @return
     */
    public abstract Diagnose getDiagnoseDetails(Diagnose diagnose);

    /**
     *
     * @param patient
     * @return
     */
    public abstract List<Prescription> getPatientPrescriptions(Patient patient);

    /**
     *
     * @param prescription
     * @return
     */
    public abstract Prescription getPrescriptionDetails(Prescription prescription);

    /**
     *
     * @return
     */
    public abstract List<Employee> getEmployees();

    /**
     *
     * @param employee
     * @return
     */
    public abstract Employee getEmployeeDetails(Employee employee);

    /**
     *
     * @return
     */
    public abstract List<User_IF> getUsers();

    /**
     *
     * @param user
     * @return
     */
    public abstract User_IF getUserDetails(User_IF user);

    /**
     *
     * @param user
     */
    public abstract void setUserPriviledges(User_IF user);

    /**
     *
     * @param user
     */
    public abstract void lockUser(User_IF user);

    /**
     *
     * @param user
     * @return
     */
    public abstract Prescription addNewPrescription(User_IF user);

    /**
     *
     * @param prescription
     * @return
     */
    public abstract boolean savePrescription(Prescription prescription);    

    /**
     *
     * @param user
     * @return
     */
    public abstract User_IF addNewUser(User_IF user);

    /**
     *
     * @param user
     * @return
     */
    public abstract boolean saveUser(User_IF user);

    /**
     *
     * @param user
     * @return
     */

    public abstract List<Prescription> getPrescriptionsByDoctor(User_IF user);

    /**
     *
     * @param message
     * @return
     */
    public abstract boolean saveMessage(Message message);

    /**
     *
     * @param user
     * @return
     */
    public abstract Message addNewMessage(User_IF user);
}

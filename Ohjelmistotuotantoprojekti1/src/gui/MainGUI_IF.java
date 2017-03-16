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
 * Interface for Main View JavaFX element with anchor pane as root
 * @author joosiika
 */
public interface MainGUI_IF {

    /**
     * Method to set the login view.
     */
    public abstract void setLogin();

    /**
     * Method to set a login failed message.
     */
    public abstract void setLoginFailed();

    /**
     * Method to set an access denied message.
     */
    public abstract void setAccessDenied();

    /**
     * method to set a sidebar main element.
     */
    public abstract void setSideBar();

    /**
     * Method to set the patient list view.
     */
    public abstract void setPatientList();

    /**
     * Method to set the drug list view.
     */
    public abstract void setDrugList();

    /**
     * Method to set the prescription list view.
     */
    public abstract void setPrescriptionList();

    /**
     * Method to set the received message list view.
     */
    public abstract void setReceivedMessageList();

    /**
     * Method to set the sent message list view.
     */
    public abstract void setSentMessageList();

    /**
     * Method to set the user list view.
     */
    public abstract void setUserList();

    /**
     * Method to set the employee list view.
     */
    public abstract void setEmployeeList();

    /**
     * Method to set the database list view.
     */
    public abstract void setDatabaseList();

    /**
     * Method to set the prescription creation or update view
     * @param prescription the prescription to be manipulated by the user
     */
    public abstract void setPrescriptionForm(Prescription prescription);

    /**
     * Method to set the message creation view
     * @param message the message to be manipulated by the user
     */
    public abstract void setMessageForm(Message message);

    /**
     * Method to set the additional buttons for users with privileges to create prescriptions.
     */
    public abstract void setPrescriptionTools();

    /**
     * Method to set patient details to the view.
     * @param patient the patient whose details the user wants to view
     */
    public abstract void setPatientDetails(Patient patient);

    /**
     * Method to set the patient's diagnose list to the view.
     * @param list patient's diagnose list
     */
    public abstract void setPatientDiagnoses(List<Diagnose> list);

    /**
     * Method to set the patient's prescription list to the view.
     * @param list patient's prescription list
     */
    public abstract void setPatientPrescriptions(List<Prescription> list);

    /**
     * Method to set prescription's details to the view.
     * @param prescription prescription to be shown in detail
     */
    public abstract void setPrescriptionDetails(Prescription prescription);

    /**
     * Method to set diagnose's details to the view.
     * @param diagnose diagnose to be shown in detail
     */
    public abstract void setDiagnoseDetails(Diagnose diagnose);

    /**
     * Method to set user's details to the view.
     * @param user user to be shown in detail
     */
    public abstract void setUserDetails(User_IF user);

    /**
     * Method to set employees's details to the view.
     * @param employee employee to be shown in detail
     */
    public abstract void setEmployeeDetails(Employee employee);

    /**
     * Method to get the selected patient from the view.
     * @return patient selected in patient list view
     */
    public abstract Patient getSelectedPatient();

    /**
     * Method to get the selected prescription from the view.
     * @return prescription selected in prescription list view
     */
    public abstract Prescription getSelectedPrescription();

    /**
     * Method to get the selected diagnose from the list view.
     * @return diagnose selected in diagnose list view
     */
    public abstract Diagnose getSelectedDiagnose();

    /**
     * Method to get the selected drug from the list view.
     * @return drug selected in drug list view
     */
    public abstract Drug getSelectedDrug();

    /**
     * Method to get the selected user from the list view.
     * @return user selected in user list view
     */
    public abstract User_IF getSelectedUser();

    /**
     * Method to get the selected drug from the list view.
     * @return employee selected in employee list view
     */
    public abstract Employee getSelectedEmployee();

    /**
     * Method to get the prescription manipulated by the prescription form view.
     * @return prescription updated by the user
     */
    public abstract Prescription getPrescriptionForm();

    /**
     * Method to set drug's details to the view.
     * @param drug to be shown in detail
     */
    public abstract void setDrugDetails(Drug drug);

    /**
     * Method to set message's details to the view.
     * @param message message to be shown in detail
     */
    public abstract void setMessageDetails(Message message);

    /**
     * Method to get the selected message from list view.
     * @return message selected in the message list view
     */
    public abstract Message getSelectedMessage();

    /**
     * Method to get the message manipulated by the message form view.
     * @return message updated by the message form
     */
    public abstract Message getMessageForm();

    /**
     * Method to set the user creation or update form to the view.
     * @param user user to be manipulated by the form
     */
    public abstract void setUserForm(User_IF user);
    
    /**
     * Method to set the administrator tools to the view.
     */
    public abstract void setUserTools();
    
    /**
     * Method to get the user manipulated by the user form view.
     * @return user updated by the user form view.
     */
    public abstract User_IF getUserForm();
}

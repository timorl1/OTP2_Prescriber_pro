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
public interface MainGUI_IF {

    /**
     *
     */
    public abstract void setLogin();

    /**
     *
     */
    public abstract void setLoginFailed();

    /**
     *
     */
    public abstract void setAccessDenied();

    /**
     *
     */
    public abstract void setSideBar();

    /**
     *
     */
    public abstract void setPatientList();

    /**
     *
     */
    public abstract void setDrugList();

    /**
     *
     */
    public abstract void setPrescriptionList();

    /**
     *
     */
    public abstract void setReceivedMessageList();

    /**
     *
     */
    public abstract void setSentMessageList();

    /**
     *
     */
    public abstract void setUserList();

    /**
     *
     */
    public abstract void setEmployeeList();

    /**
     *
     */
    public abstract void setDatabaseList();

    /**
     *
     * @param prescription
     */
    public abstract void setPrescriptionForm(Prescription prescription);

    /**
     *
     * @param message
     */
    public abstract void setMessageForm(Message message);

    /**
     *
     */
    public abstract void setPrescriptionTools();

    /**
     *
     * @param patient
     */
    public abstract void setPatientDetails(Patient patient);

    /**
     *
     * @param list
     */
    public abstract void setPatientDiagnoses(List<Diagnose> list);

    /**
     *
     * @param list
     */
    public abstract void setPatientPrescriptions(List<Prescription> list);

    /**
     *
     * @param prescription
     */
    public abstract void setPrescriptionDetails(Prescription prescription);

    /**
     *
     * @param diagnose
     */
    public abstract void setDiagnoseDetails(Diagnose diagnose);

    /**
     *
     * @param user
     */
    public abstract void setUserDetails(User_IF user);

    /**
     *
     * @param employee
     */
    public abstract void setEmployeeDetails(Employee employee);

    /**
     *
     * @return
     */
    public abstract Patient getSelectedPatient();

    /**
     *
     * @return
     */
    public abstract Prescription getSelectedPrescription();

    /**
     *
     * @return
     */
    public abstract Diagnose getSelectedDiagnose();

    /**
     *
     * @return
     */
    public abstract Drug getSelectedDrug();

    /**
     *
     * @return
     */
    public abstract User_IF getSelectedUser();

    /**
     *
     * @return
     */
    public abstract Employee getSelectedEmployee();

    /**
     *
     * @return
     */
    public abstract Prescription getPrescriptionForm();

    /**
     *
     * @param drug
     */
    public abstract void setDrugDetails(Drug drug);

    /**
     *
     * @param message
     */
    public abstract void setMessageDetails(Message message);

    /**
     *
     * @return
     */
    public abstract Message getSelectedMessage();

    /**
     *
     * @return
     */
    public abstract Message getMessageForm();

    /**
     *
     * @param drug
     */
    public abstract void setUserForm(User_IF user);
    public abstract void setUserTools();
    public abstract User_IF getUserForm();
}

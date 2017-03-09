package gui;

import java.util.List;
import model.Diagnose;
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
public interface MainGUI_IF {
    public abstract void setLogin();
    public abstract void setLoginFailed();
    public abstract void setAccessDenied();
    public abstract void setSideBar();
    public abstract <T> void loadTabPane(List<T> data);
    public abstract void setPatientList();
    public abstract void setDrugList();
    public abstract void setPrescriptionList();
    public abstract void setMessageList();
    public abstract void setUserList();
    public abstract void setEmployeeList();
    public abstract void setDatabaseList();
    public abstract void setPrescriptionForm(Prescription prescription);
    public abstract void setPrescriptionTools();
    public abstract void setPatientDetails(Patient patient);
    public abstract void setPatientDiagnoses(List<Diagnose> list);
    public abstract void setPatientPrescriptions(List<Prescription> list);
    public abstract void setPrescriptionDetails(Prescription prescription);
    public abstract void setDiagnoseDetails(Diagnose diagnose);
    public abstract void setUserDetails(User_IF user);
    public abstract void setEmployeeDetails(Employee employee);
    public abstract void setNullDoseMessage();
    public abstract void setInsuffucientDoseMessage();
    public abstract void setOptimalDoseMessage();
    public abstract void setOverOptimalDoseMessage();
    public abstract void setRiskLimitDoseMessage();
    public abstract void setOverdoseMessage();
    public abstract Patient getSelectedPatient();
    public abstract Prescription getSelectedPrescription();
    public abstract Diagnose getSelectedDiagnose();
    public abstract Drug getSelectedDrug();
    public abstract User_IF getSelectedUser();
    public abstract Employee getSelectedEmployee();
    public abstract Prescription getPrescriptionForm();
}

package gui;

import java.util.List;
import model.Diagnose;
import model.Patient;
import model.Prescription;

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
    public abstract void setPatientDetails(List<String> list);
    public abstract void setPatientDiagnoses(List<Diagnose> list);
    public abstract void setPatientPrescriptions(List<Prescription> list);
    public abstract void setPrescriptionDetails(List<String> list);
    public abstract void setDiagnoseDetails(List<String> list);
    public abstract Patient getSelectedPatient();
    public abstract Prescription getSelectedPrescription();
    public abstract Diagnose getSelectedDiagnose();
}

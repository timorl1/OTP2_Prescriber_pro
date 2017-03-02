package gui;

import model.User;

/**
 *
 * @author joosiika
 */
public interface MainGUI_IF {
    public abstract void loadLogin();
    public abstract void loadSideBar();
    public abstract <T> void loadTabPane(T selection);
    public abstract void loadPatientList();
    public abstract void loadDrugList();
    public abstract void loadPrescriptionList();
    public abstract void loadMessageList();
    public abstract void loadUserList();
    public abstract void loadEmployeeList();
    public abstract void loadDatabaseList();
}

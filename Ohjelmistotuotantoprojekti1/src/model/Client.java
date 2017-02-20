package model;

import dao.PatientDAO;
import java.util.List;

/**
 *
 * @author joosiika
 */
public class Client {
    PatientDAO db = new PatientDAO();
    List<Patient> patients = db.readPatients();
    List<Employee> employees = db.readEmployees();
    
    public List<Patient> getPatients() {
        return patients;
    }
    
    public List<Diagnose> getPatientDiagnoses(Patient patient) {
        return db.readPatientDiagnoses(patient);
    }
    
    public List<Employee> getEmployees() {
        return employees;
    }
}

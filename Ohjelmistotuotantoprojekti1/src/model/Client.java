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
    
    public List<Patient> getPatients() {
        return patients;
    }
    
    public List<Diagnose> getPatientDiagnoses(Patient patient) {
        return db.readPatientDiagnoses(patient);
    }
}

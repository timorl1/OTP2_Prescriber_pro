package model;

import dao.PatientDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joosiika
 */
public class ClientResources {
    PatientDAO db = new PatientDAO();
    
    public List<Patient> getPatients() {
        return db.readPatients();
    }
    
    public List<Diagnose> getPatientDiagnoses(Patient patient) {
        return db.readPatientDiagnoses(patient);
    }
}

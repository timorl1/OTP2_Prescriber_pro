package main;

import java.util.List;

/**
 *
 * @author joosiika
 */
public class Patients {
    
    private List<Patient> patientlist;

    public Patients(List patientlist) {
        this.patientlist = patientlist;
    }
    
    public void addPatient(Patient patient) {
        this.patientlist.add(patient);
    }
    
    public Patient getPatient(int SSN) {
        if (patientlist.indexOf(SSN) != -1) {
            return patientlist.get(patientlist.indexOf(SSN));
        }
        return null;
    }
    
    public List getCollection() {
        return this.patientlist;
    }
    
    public Patient forEach() {
        for (Patient patient : this.patientlist) {
            return patient;
        }
        return null;
    }
}

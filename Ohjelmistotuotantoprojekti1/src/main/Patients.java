package main;

import java.util.ArrayList;

/**
 *
 * @author joosiika
 */
public class Patients {
    
    private ArrayList<Patient> patientlist;

    public Patients() {
        this.patientlist = new ArrayList();
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
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.patient;

import java.util.ArrayList;
import resources.CompositeResource;
import java.util.List;
import resources.Resource;

/**
 *
 * @author joosiika
 */
public class PatientResources extends CompositeResource {
    
    private static Resource INSTANCE;
    
    private final PatientDAO_IF patientDAO;
    private List<Patient> patients;
    
    private PatientResources() {
        this.patientDAO = new PatientDAO();
        this.patients = patientDAO.readPatients();
    }
    
    public synchronized static Resource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PatientResources();
        }
        return INSTANCE;
    }

    public List<String> listPatients() {
        List<String> list = new ArrayList();
        this.patients.forEach(p -> list.add(p.toString()));
        return list;
    }
    
    public Patient getPatient(int i) {
        return this.patients.get(i);
    }

    @Override
    public void refresh() {
        this.patients = this.patientDAO.readPatients();
    }
}

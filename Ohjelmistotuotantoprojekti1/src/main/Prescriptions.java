package main;

import java.util.List;

/**
 *
 * @author joosiika
 */
public class Prescriptions {
    private List<Prescription> prescriptionlist;

    public Prescriptions(List prescriptionlist) {
        this.prescriptionlist = prescriptionlist;
    }
    
    public void addPrescription(Prescription prescription) {
        this.prescriptionlist.add(prescription);
    }
    
    public Prescription getPrescription(int id) {
        if (prescriptionlist.indexOf(id) != -1) {
            return prescriptionlist.get(prescriptionlist.indexOf(id));
        }
        return null;
    }
    
    public List getCollection() {
        return this.prescriptionlist;
    }
    
    public Prescription forEach() {
        for (Prescription prescription : this.prescriptionlist) {
            return prescription;
        }
        return null;
    }
}

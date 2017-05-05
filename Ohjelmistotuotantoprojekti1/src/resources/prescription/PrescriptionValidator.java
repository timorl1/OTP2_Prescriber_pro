/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.prescription;

/**
 *
 * @author joosiika
 */
public class PrescriptionValidator {
    private static PrescriptionValidator INSTANCE = null;
    
    public static PrescriptionValidator getInstance() {
        if (INSTANCE == null) {
            return new PrescriptionValidator();
        }
        return INSTANCE;
    }
    
    public boolean isCalculable(Prescription prescription) {
        return prescription.getPatient() != null && prescription.getDrug() != null;
    }
    
    public boolean isEvaluable(Prescription prescription) {
        return prescription.getPatient() != null && prescription.getDrug() != null && prescription.getDose() != 0;
    }
    
    public boolean validate(Prescription prescription) {
        return prescription.getEndDate() != null
                && prescription.getStartDate() != null
                && prescription.getInfo() != null
                && !prescription.getInfo().isEmpty()
                && prescription.getTimesADay() != 0
                && prescription.getDose() != 0
                && prescription.getDiagnoseID() != 0
                && prescription.getDrug() != null
                && prescription.getPatientID() != null
                && !prescription.getPatientID().isEmpty()
                && prescription.getDoctorID() != 0;
    }
}

package model;

import dao.PatientDAO;
import dao.PatientDAO_IF;
import dao.PrescriptionDAO;
import dao.PrescriptionDAO_IF;
import java.util.List;

/**
 *
 * @author joosiika
 */
public class PatientBuilder implements PatientBuilder_IF{
    private final PatientDAO_IF patientdb;
    private final PrescriptionDAO_IF prescriptiondb;
    
    private Patient patient;

    public PatientBuilder() {
        this.patientdb = new PatientDAO();
        this.prescriptiondb = new PrescriptionDAO();
    }

    @Override
    public List<Diagnose> getPatientDiagnoses(Patient patient) {
        return this.patientdb.readPatientDiagnoses(patient);
    }

    @Override
    public List<Prescription> getPatientPrescriptions(Patient patient) {
        return this.prescriptiondb.getPrescriptionsByPatient(patient);
    }

    @Override
    public Patient buildPatient(Patient patient) {
        this.patient = patient;
        this.patient.setDiagnoses(this.getPatientDiagnoses(this.patient));
        this.patient.setPrescriptions(this.getPatientPrescriptions(this.patient));
        return this.patient;
    }
    
}

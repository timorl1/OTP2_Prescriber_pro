package model;

import dao.DiseaseDAO;
import dao.DiseaseDAO_IF;
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
    private final DiseaseDAO_IF diseasedb;
    
    private Patient patient;

    public PatientBuilder() {
        this.patientdb = new PatientDAO();
        this.prescriptiondb = new PrescriptionDAO();
        this.diseasedb = new DiseaseDAO();
    }

    @Override
    public List<Diagnose> getPatientDiagnoses(Patient patient) {
        List<Diagnose> diagnoses = this.patientdb.readPatientDiagnoses(patient);
        diagnoses.forEach((diagnose) -> {
            diagnose.setPatient(this.patient);
            diagnose.setDisease(this.diseasedb.getDisease(diagnose.getDiseaseID()));
        });
        return diagnoses;
    }

    @Override
    public List<Prescription> getPatientPrescriptions(Patient patient) {
        List<Prescription> prescriptions = this.prescriptiondb.getPrescriptionsByPatient(patient);
        prescriptions.forEach((prescription) -> {
            prescription.setPatient(this.patient);
        });
        return prescriptions;
    }

    @Override
    public Patient buildPatient(Patient patient) {
        this.patient = patient;
        this.patient.setDiagnoses(this.getPatientDiagnoses(this.patient));
        this.patient.setPrescriptions(this.getPatientPrescriptions(this.patient));
        return this.patient;
    }
    
}

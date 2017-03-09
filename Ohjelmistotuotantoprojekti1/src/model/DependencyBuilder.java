package model;

import dao.DiseaseDAO;
import dao.DiseaseDAO_IF;
import dao.DrugDAO;
import dao.DrugDAO_IF;
import dao.PatientDAO;
import dao.PatientDAO_IF;
import dao.PrescriptionDAO;
import dao.PrescriptionDAO_IF;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author joosiika
 */
public class DependencyBuilder implements DependencyBuilder_IF {
    
    private DiseaseDAO_IF diseaseDAO;
    private PatientDAO_IF patientDAO;
    private PrescriptionDAO_IF prescriptionDAO;
    private DrugDAO_IF drugDAO;
    
    private Patient patient;
    private User_IF doctor;
    private Diagnose diagnose;
    private Prescription prescription;
    private Map<String, Patient> patients;
    private Map<Integer, User_IF> users;
    
    public DependencyBuilder(Map<String, Patient> patients, Map<Integer, User_IF> users) {
        this.diseaseDAO = new DiseaseDAO();
        this.patientDAO = new PatientDAO();
        this.drugDAO = new DrugDAO();
        this.prescriptionDAO = new PrescriptionDAO();
        this.patients = patients;
        this.users = users;
    }

    /*@Override
    public Patient buildPatient(Patient patient) {
        this.patient = patient;
        this.patient.setDiagnoses(this.patientDAO.readPatientDiagnoses(this.patient));
        this.patient.getDiagnoses().forEach(d -> {
            d.setPatient(this.patient);
            d.setDisease(this.diseaseDAO.getDisease(d.getDiseaseID()));
            d.setDoctor(this.users.get(d.getDoctorId()));
            System.out.println(d.getDoctor());
        });
        this.patient.setPrescriptions(this.prescriptionDAO.getPrescriptionsByPatient(this.patient));
        this.patient.getPrescriptions().forEach(p -> {
            p.setPatient(this.patient);
            p.setDoctor(this.users.get(p.getDoctorID()));
            p.setUsername(this.users.get(p.getDoctorID()).getUsername());
            p.setDrug(this.drugDAO.readDrug(p.getDrugID()));
            Map<Integer, Diagnose> diagnoses = new HashMap();
            this.patient.getDiagnoses().forEach(d -> diagnoses.put(d.getId(), d));
            p.setDiagnose(diagnoses.get(p.getDiagnoseID()));
        });
        return this.patient;
    }
    
    @Override
    public List<Prescription> buildDoctorPrescriptions(User_IF doctor) {
        this.doctor = doctor;
        List<Prescription> list = this.prescriptionDAO.getPrescriptionsByDoctor(doctor);
        list.forEach(p -> {
            p.setPatient(this.patients.get(p.getPatientID()));
            p.setDoctor(this.users.get(p.getDoctorID()));
        });
        return list;
    }*/
    
    @Override
    public Patient buildPatient(Patient patient) {
        this.patient = patient;
        this.patient.setDiagnoses(this.patientDAO.readPatientDiagnoses(this.patient));
        this.patient.setPrescriptions(this.prescriptionDAO.getPrescriptionsByPatient(this.patient));
        return this.patient;
    }
    
    @Override
    public Diagnose buildDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
        this.diagnose.setPatient(this.buildPatient(this.patients.get(this.diagnose.getPatientId())));
        this.diagnose.setDoctor(this.users.get(this.diagnose.getDoctorId()));
        this.diagnose.setDisease(this.diseaseDAO.getDisease(this.diagnose.getDiseaseID()));
        return this.diagnose;
    }
    
    @Override
    public Prescription buildPrescription(Prescription prescription) {
        this.prescription = prescription;
        this.prescription.setPatient(this.buildPatient(this.patients.get(this.prescription.getPatientID())));
        this.prescription.setDoctor(this.users.get(this.prescription.getDoctorID()));
        this.prescription.setDrug(this.drugDAO.readDrug(this.prescription.getDrugID()));
        Map<Integer, Diagnose> diagnoses = new HashMap();
        this.prescription.getPatient().getDiagnoses().forEach(d -> diagnoses.put(d.getId(), d));
        this.prescription.setDiagnose(this.buildDiagnose(diagnoses.get(this.prescription.getDiagnoseID())));
        return this.prescription;
    }

    @Override
    public List<Prescription> buildDoctorPrescriptions(User_IF doctor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

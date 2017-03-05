package model;

import dao.PatientDAO;
import dao.PatientDAO_IF;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.converter.DoubleStringConverter;

/**
 *
 * @author joosiika
 */
public class ClientResources implements ClientResources_IF {
    private PatientDAO_IF db;
    private PatientBuilder_IF pBuilder;
    private DiagnoseBuilder_IF dBuilder;
    private PrescriptionBuilder_IF presBuilder;
    private DoubleStringConverter dsc;
    
    private Map<String, Patient> patients;
    
    public ClientResources() {
        this.db = new PatientDAO();
        this.pBuilder = new PatientBuilder();
        this.dBuilder = new DiagnoseBuilder();
        this.presBuilder = new PrescriptionBuilder();
        this.dsc = new DoubleStringConverter();
        this.patients = new HashMap();
        this.db.readPatients().forEach((patient) -> {
            this.patients.put(patient.getSSN(), patient);
        });
    }

    public List<Patient> getPatients() {
        return db.readPatients();
    }
    
    @Override
    public List<String> getPatientDetails(Patient patient) {
        List<String> list = new ArrayList();
        list.add("Sosiaaliturvatunnus: " + patient.getSSN());
        list.add("Etunimi: " + patient.getFirstName());
        list.add("Sukunimi: " + patient.getLastName());
        list.add("Sukupuoli: " + patient.getGender());
        list.add("Pituus: " + dsc.toString(patient.getHeight()) + " cm");
        list.add("Paino: " + dsc.toString(patient.getWeight()) + " kg");
        return list;
    }
    
    @Override
    public List<Diagnose> getPatientDiagnoses(Patient patient) {
        List<Diagnose> diagnoses = this.pBuilder.getPatientDiagnoses(patient);
        diagnoses.forEach(this.dBuilder::buildDiagnose);
        return diagnoses;
    }

    @Override
    public List<Prescription> getPatientPrescriptions(Patient patient) {
        List<Prescription> prescriptions = this.pBuilder.getPatientPrescriptions(patient);
        prescriptions.forEach(this.presBuilder::buildPrescription);
        return prescriptions;
    }

    @Override
    public List<String> getEmployees() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEmployeeDetails(String SSN) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getPrescriptionDetails(Prescription prescription) {
        List<String> list = new ArrayList();
        list.add("Tunnus: " + prescription.getId());
        list.add("Luontipäivä: " + prescription.getCreationDate());
        list.add("Potilas: " + prescription.getPatient().getLastName() + ", " + prescription.getPatient().getFirstName() + ", " + prescription.getPatient().getSSN());
        list.add("Lääkäri: " + prescription.getDoctor().getLastName() + ", " + prescription.getDoctor().getFirstName());
        list.add("Diagnoosi: " + prescription.getDiagnose().getId() + ": " + prescription.getDiagnose().getDisease());
        list.add("Lääke: " + prescription.getDrug().getName());
        list.add("Annostus: " + prescription.getDose() + "" + prescription.getDrug().getUnit() + ", " + prescription.getTimesADay() + " kertaa päivässä.");
        list.add("Ohjeet: " + prescription.getInfo());
        list.add("Alkaen: " + prescription.getStartDate());
        list.add("Päättyen: " + prescription.getEndDate());
        return list;
    }

    @Override
    public List<String> getDiagnoseDetails(Diagnose diagnose) {
        List<String> list = new ArrayList();
        list.add("Tunnus: " + diagnose.getId());
        list.add("Luontipäivä: " + diagnose.getCreationDate());
        list.add("Potilas: " + diagnose.getPatient().getLastName() + ", " + diagnose.getPatient().getFirstName() + ", " + diagnose.getPatient().getSSN());
        list.add("Lääkäri: " + diagnose.getDoctor().getLastName() + ", " + diagnose.getDoctor().getFirstName());
        list.add("Sairaus: " + diagnose.getDisease());
        list.add("Epikriisi: " + diagnose.getEpicrisis());
        if (diagnose.getResolutionDate() != null) {
            list.add("Diagnoosin tila: hoidettu, " + diagnose.getResolutionDate());
        }
        else {
            list.add("Diagnoosin tila: ei hoidettu" );
        }
        return list;
    }
    
}

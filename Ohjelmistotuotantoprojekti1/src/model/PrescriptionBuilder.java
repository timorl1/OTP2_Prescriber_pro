/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.DiseaseDAO;
import dao.DiseaseDAO_IF;
import dao.DrugDAO;
import dao.DrugDAO_IF;
import dao.PatientDAO;
import dao.PatientDAO_IF;


public class PrescriptionBuilder implements PrescriptionBuilder_IF {
    private final PatientDAO_IF patientdb;
    private final DiseaseDAO_IF diseasedb;
    private final DrugDAO_IF drugdb;
    private final PatientBuilder_IF pBuilder;
    private final DiagnoseBuilder_IF dBuilder;
    
    private Prescription prescription;

    public PrescriptionBuilder() {
        this.patientdb = new PatientDAO();
        this.diseasedb = new DiseaseDAO();
        this.drugdb = new DrugDAO();
        this.pBuilder = new PatientBuilder();
        this.dBuilder = new DiagnoseBuilder();
    }

    @Override
    public Patient getPrescriptionPatient(Prescription prescription) {
        return this.pBuilder.buildPatient(this.patientdb.readPatient(prescription.getPatientID()));
    }

    @Override
    public Doctor getPrescriptionDoctor(Prescription prescription) {
        Doctor doctor = new Doctor();
        doctor.setFirstName("Place");
        doctor.setLastName("Holder");
        return doctor;
    }

    @Override
    public Diagnose getPrescriptionDiagnose(Prescription prescription) {
        return this.dBuilder.buildDiagnose(this.patientdb.readDiagnose(this.prescription.getDiagnoseID()));
    }

    @Override
    public Drug getPrescriptionDrug(Prescription prescription) {
        return this.drugdb.readDrug(prescription.getDrugID());
    }

    @Override
    public Prescription buildPrescription(Prescription prescription) {
        this.prescription = prescription;
        this.prescription.setPatient(this.getPrescriptionPatient(this.prescription));
        this.prescription.setDoctor(this.getPrescriptionDoctor(this.prescription));
        this.prescription.setDiagnose(this.getPrescriptionDiagnose(this.prescription));
        this.prescription.setDrug(this.getPrescriptionDrug(this.prescription));
        return this.prescription;
    }
    
}

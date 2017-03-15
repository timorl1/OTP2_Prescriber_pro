/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Diagnose;
import model.Patient;

/**
 * Interface that defines methods for getting info on patients and diagnoses
 * from database 
 * @author joosiika
 */
public interface PatientDAO_IF {

    /**
     * Gets patient by social security number
     * @param SSN
     * @return patient object
     */
    public abstract Patient readPatient(String SSN);

    /**
     * Gets a list of all patients
     * @return list of patient objects
     */
    public abstract List<Patient> readPatients();

    /**
     * Gets diagnose by id
     * @param diagnoseID
     * @return diagnose object
     */
    public abstract Diagnose readDiagnose(int diagnoseID);

    /**
     * Gets diagnoses by patient
     * @param pat
     * @return list of diagnose objects
     */
    public abstract List<Diagnose> readPatientDiagnoses(Patient pat);
}

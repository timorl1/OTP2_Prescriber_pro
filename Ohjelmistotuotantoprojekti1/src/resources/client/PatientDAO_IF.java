/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.client;

import java.util.List;
import resources.data.Diagnose;
import resources.client.Patient;

/**
 * Interface that defines methods for getting info on patients and diagnoses
 * from database 
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public interface PatientDAO_IF {

    /**
     * Gets patient by social security number
     * @param SSN patient's social security number
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
     * @param diagnoseID diagnose's id
     * @return diagnose object
     */
    public abstract Diagnose readDiagnose(int diagnoseID);

    /**
     * Gets diagnoses by patient
     * @param pat patient object
     * @return list of diagnose objects
     */
    public abstract List<Diagnose> readPatientDiagnoses(Patient pat);
}

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
 *
 * @author joosiika
 */
public interface PatientDAO_IF {

    /**
     *
     * @param SSN
     * @return
     */
    public abstract Patient readPatient(String SSN);

    /**
     *
     * @return
     */
    public abstract List<Patient> readPatients();

    /**
     *
     * @param diagnoseID
     * @return
     */
    public abstract Diagnose readDiagnose(int diagnoseID);

    /**
     *
     * @param pat
     * @return
     */
    public abstract List<Diagnose> readPatientDiagnoses(Patient pat);
}

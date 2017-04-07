/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.client;

import java.util.List;

/**
 *
 * @author joosiika
 */
public interface DiagnoseDAO_IF {

    /**
     * Gets diagnoses by patient
     * @param pat patient object
     * @return list of diagnose objects
     */
    public abstract List<Diagnose> readPatientDiagnoses(Patient pat);
    
    /**
     * Gets diagnose by id
     * @param diagnoseID diagnose's id
     * @return diagnose object
     */
    public abstract Diagnose readDiagnose(int diagnoseID);
    
}

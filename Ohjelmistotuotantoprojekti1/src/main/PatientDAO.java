/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Timo
 */
public class PatientDAO implements PatientDAO_IF{
    
    private PatientDBParameter parameters;
    private Patient patient;
    
    //Set database parameters, what to get
    public PatientDAO(PatientDBParameter parameters){
        this.parameters = parameters;
    }
    
    //Get patient from database with SSN
    @Override
    public Patient readPatient(String SSN) {
        parameters.getSSNField();
        return null;
    }
    
    //Get all patients from database
    @Override
    public Patient[] readPatients() {
        
        return null;
    }
    
}

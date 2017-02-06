/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author joosiika
 */
public interface PatientDatabaseDAO_IF {
    //Creates the connection to the Patient database
    public abstract boolean createConnection(String url, String username, String password);
    //Reads all field names from the Patient database and returns them as array of strings
    public abstract String[] getDBFieldNames();
    //Accesses the software's database and reads from the patientdbparameter-table and constructs the PatientDBParamater-object
    public abstract PatientDBParameter readPatientDBParameter();
    //Accesses the software's database and writes a PatientDBParameter-object into the patientdbparameter-table
    public abstract boolean writePatientDBParameter();
}

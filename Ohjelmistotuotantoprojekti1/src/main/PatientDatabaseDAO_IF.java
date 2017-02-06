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
    public abstract Patient readPatient(String SSN);
    public abstract Patient[] readPatients();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.SQLException;

/**
 *
 * @author joosiika
 */
public interface PatientDAO_IF {
    public abstract Patient readPatient(String SSN) throws SQLException;
    public abstract Patient[] readPatients() throws SQLException;
}

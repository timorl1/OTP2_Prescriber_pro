/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Diagnose;
import model.Patient;

/**
 *
 * @author joosiika
 */
public interface PatientDAO_IF {
    public abstract Patient readPatient(String SSN) throws SQLException;
    public abstract Patient[] readPatients() throws SQLException;
    public abstract List<Diagnose> readPatientDiagnoses(Patient pat) throws SQLException;
}

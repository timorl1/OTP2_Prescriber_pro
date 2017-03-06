/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author joosiika
 */
public interface PatientBuilder_IF {
    public abstract List<Diagnose> getPatientDiagnoses(Patient patient);
    public abstract List<Prescription> getPatientPrescriptions(Patient patient);
    public abstract Patient buildPatient(Patient patient);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author joosiika
 */
public interface PrescriptionMaker_IF {
    //public abstract Prescription createPrescription(User_IF user);
    //public abstract boolean savePrescription(Prescription prescription);
    public abstract DoseStatus evaluateDose(Prescription prescription);
    public abstract double getOptimalDose(Prescription prescription);
}

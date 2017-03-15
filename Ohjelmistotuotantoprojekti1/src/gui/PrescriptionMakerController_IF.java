/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 * Interface for implementing controller class for PrescriptionMaker
 * @author joosiika
 */
public interface PrescriptionMakerController_IF {

    /**
     * Method to get the optimal dose for a patient.
     * @return the optimal dose of a certain drug for a certain patient as a double value
     */
    public abstract double getOptimalDose();

    /**
     * Method to check the dosage level of a drug.
     */
    public abstract void checkDose();
    
    /**
     * Method to check if the drug contains allergens not suitable for the patient.
     */
    public abstract void checkAllergens();
}

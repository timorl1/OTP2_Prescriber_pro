/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.prescription;

import java.util.List;
import calculator.DoseStatus;
import java.util.HashMap;

/**
 * Interface for implementing a prescription maker class.
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public interface PrescriptionEditor_IF {
    public abstract void setCalculatorStrategy(int i);
    public abstract void editPrescription(Prescription prescription);
    public abstract Prescription getPrescription();
    public abstract void undo();

    /**
     * Method to evaluate the prescription's dose.
     * @param prescription the prescription to be evaluated containing at least patient, drug and dose information.
     * @return DoseStatus enum to set dosage state
     */
    public abstract DoseStatus evaluateDose();

    /**
     * Method to get the optimal dose for a prescription.
     * @param prescription a prescription containing at least patient and drug information.
     * @return the optimal dose in double value
     */
    public abstract double getOptimalDose();
    
    /**
     * Method to check if the patient is allergic to the drug of a prescription.
     * @param prescription a prescription containing at least patient and drug information.
     * @return a list of matching allergens
     */
    public abstract List<String> isAllergic();
    
    public abstract HashMap crossReaction();
    
    public abstract String getOptimalDoseFormula();
    public abstract String getMaxDoseFormula();
    public abstract String getCumulativeDoseFormula();
}

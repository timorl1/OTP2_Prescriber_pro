/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.prescription;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public interface PrescriptionFormGUI_IF {
    
    public abstract void changeStrategy();
    
    public abstract void save();
    
    public abstract void cancel();
    
    public abstract void setPatient();
    
    public abstract void setDiagnose();
    
    public abstract void setDrug();
    
    public abstract void setDose();
    
    public abstract void setTimesADay();
    
    public abstract void setStartDate();
    
    public abstract void setEndDate();
    
    public abstract void setInfo();
    
    /**
     * Method to set update date to the info field of the prescription if the prescription is not new.
     */
    public abstract void markUpdate();
    
    /**
     * Method to check the dosage level of a drug.
     */
    public abstract void checkDose();
    
    /**
     * Method to get the optimal dose for a patient.
     */
    public abstract void getOptimalDose();
    
    public abstract void performChecks();
    
    /**
     * Method to check if the drug contains allergens not suitable for the patient.
     */
    public abstract void checkAllergens();
    
    public abstract void checkCrossReactions();
    
    public abstract void setCrossReactionMessage(HashMap crossReactions);
    
    /**
     * Method to set patient is allergic to the drug message.
     * @param allergens list of matching allergens
     */
    public abstract void setIsAllergicMessage(List<String> allergens);

    /**
     * Method to set no result message to the dose field.
     */
    public abstract void setNullDoseMessage();

    /**
     * Method to set low dosage message to the dose field.
     */
    public abstract void setInsuffucientDoseMessage();

    /**
     * Method to set optimal dose message to the dose field.
     */
    public abstract void setOptimalDoseMessage();

    /**
     * Method to set semi high dose message to the dose field.
     */
    public abstract void setOverOptimalDoseMessage();

    /**
     * Method to set high message to the dose field.
     */
    public abstract void setRiskLimitDoseMessage();

    /**
     * Method to set standard overdose message to the dose field.
     */
    public abstract void setOverdoseMessage();

    /**
     * Method to set cumulative overdose message to the dose field.
     */
    public abstract void setCumulativeOverdoseMessage();
}

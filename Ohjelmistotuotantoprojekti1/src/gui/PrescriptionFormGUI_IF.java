/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import resources.client.Diagnose;
import resources.app.Prescription;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public interface PrescriptionFormGUI_IF {

    /**
     * Method to get the cancel button element from this custom form element.
     * @return cancel button of the form
     */
    public abstract Button getCancelButton();

    /**
     * Method to get the save button element from this custom view element.
     * @return save button of the form
     */
    public abstract Button getSaveButton();

    /**
     * Method to get the diagnose selector element from this custom view element.
     * @return choice box element with type Diagnose
     */
    public abstract ChoiceBox<Diagnose> getDiagnoseSelector();

    /**
     * Method to get the patient text element from this custom view element.
     * @return patient info text element of the form
     */
    public abstract Text getPatientField();

    /**
     * Method to get the prescription handled by this view.
     * @return prescription updated by this view
     */
    public abstract Prescription getPrescription();

    /**
     * Method to set a diagnose to the form.
     * @param diagnose to be set to the form
     */
    public abstract void setDiagnose(Diagnose diagnose);

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
    
    /**
     * Method to set patient is allergic to the drug message.
     * @param allergens list of matching allergens
     */
    public abstract void setIsAllergicMessage(List<String> allergens);
    
    /**
     * Method to set update date to the info field of the prescription if the prescription is not new.
     */
    public abstract void markUpdate();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import model.Diagnose;
import model.Prescription;

/**
 *
 * @author joosiika
 */
public interface PrescriptionFormGUI_IF {
    public abstract Button getCancelButton();
    public abstract Button getSaveButton();
    public abstract ChoiceBox<Diagnose> getDiagnoseSelector();
    public abstract Text getPatientField();
    public abstract Prescription getPrescription();
    public abstract void setDiagnose(Diagnose diagnose);
    public abstract void setNullDoseMessage();
    public abstract void setInsuffucientDoseMessage();
    public abstract void setOptimalDoseMessage();
    public abstract void setOverOptimalDoseMessage();
    public abstract void setRiskLimitDoseMessage();
    public abstract void setOverdoseMessage();
    public abstract void setCumulativeOverdoseMessage();
    public abstract void markUpdate();
}

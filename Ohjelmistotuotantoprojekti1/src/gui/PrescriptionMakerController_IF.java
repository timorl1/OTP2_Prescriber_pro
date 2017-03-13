/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author joosiika
 */
public interface PrescriptionMakerController_IF {
    public abstract double getOptimalDose();
    public abstract void checkDose();
    public abstract void checkAllergens();
}

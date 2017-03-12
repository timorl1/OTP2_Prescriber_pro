/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import model.DoseStatus;
import model.PrescriptionMaker;
import model.PrescriptionMaker_IF;


public class PrescriptionMakerController implements PrescriptionMakerController_IF {
    private PrescriptionFormGUI_IF gui;
    private PrescriptionMaker_IF maker;

    public PrescriptionMakerController(PrescriptionFormGUI_IF gui) {
        this.gui = gui;
        this.maker = new PrescriptionMaker();
    }

    @Override
    public double getOptimalDose() {
        return this.maker.getOptimalDose(this.gui.getPrescription());
    }

    @Override
    public void checkDose() {
        DoseStatus status = this.maker.evaluateDose(this.gui.getPrescription());
        System.out.println("evaluating dose");
        switch (status) {
            case NULL:
                this.gui.setNullDoseMessage();
                break;
            case INSUFFICIENT:
                this.gui.setInsuffucientDoseMessage();
                break;
            case OPTIMAL:
                this.gui.setOptimalDoseMessage();
                break;
            case OVER_OPTIMAL:
                this.gui.setOverOptimalDoseMessage();
                break;
            case RISK_LIMIT:
                this.gui.setRiskLimitDoseMessage();
                break;
            case OVERDOSE:
                this.gui.setOverdoseMessage();
                break;
            case CUMULATIVE_OVERDOSE:
                this.gui.setCumulativeOverdoseMessage();
                break;
        }
    }
    
}

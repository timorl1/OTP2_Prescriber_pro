/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prescriptionMaker;

import resources.prescription.PrescriptionFormGUI_IF;
import java.util.List;
import calculator.DoseStatus;
import prescriptionmaker.PrescriptionMaker;
import prescriptionmaker.PrescriptionMaker_IF;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
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

    @Override
    public void checkAllergens() {
        List<String> allergens = this.maker.isAllergic(this.gui.getPrescription());
        if (!allergens.isEmpty()) {
            this.gui.setIsAllergicMessage(allergens);
        }
    }
    
}

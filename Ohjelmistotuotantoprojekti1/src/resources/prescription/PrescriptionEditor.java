/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.prescription;

import resources.patient.Patient;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import calculator.DoseCalculator;
import calculator.DoseCalculator_IF;
import calculator.DoseStatus;
import calculator.DuboisBSAConverter;
import calculator.MonstellerBSAConverter;
import java.sql.Date;
import java.util.HashMap;
import resources.drug.Drug;
import resources.user.User_IF;
import calculator.CalculatorStrategy;
/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class PrescriptionEditor implements PrescriptionEditor_IF {
    private final PrescriptionDAO_IF prescriptionDAO;
    private final DoseCalculator_IF doseCalculator;
    private final CalculatorStrategy[] strategies = {null, new MonstellerBSAConverter(), new DuboisBSAConverter()};
    
    private double optimalDose;
    private double maxDose;
    private double cumulativeDose;
    private Prescription prescription;
    private Object memento;

    public PrescriptionEditor() {
        this.prescriptionDAO = new PrescriptionDAO();
        this.doseCalculator = new DoseCalculator();
    }
    
    @Override
    public void setCalculatorStrategy(int i) {
        this.doseCalculator.changeStrategy(this.strategies[i]);
    }
    
    @Override
    public void editPrescription(Prescription prescription) {
        this.prescription = prescription;
        this.memento = new Memento(prescription);
    }

    @Override
    public boolean savePrescription() {
        return this.prescriptionDAO.createPrescription(this.prescription);
    }
    
    @Override
    public void undo() {
        if (this.memento != null) {
            Memento undo = (Memento)this.memento;
            this.prescription = undo.original;
        }
    }

    @Override
    public double getOptimalDose() {
        this.optimalDose = this.doseCalculator.getOptimalDose(this.prescription.getPatient(), this.prescription.getDrug());
        return this.optimalDose;
    }

    @Override
    public DoseStatus evaluateDose() {
        Patient patient = this.prescription.getPatient();
        Drug drug = this.prescription.getDrug();
        double dose = this.prescription.getDose();
        int timesADay = this.prescription.getTimesADay();
        int duration = 1;
        try{
        LocalDate startDate = this.prescription.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = this.prescription.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        duration = Math.toIntExact(days);
        } catch (Exception e) {
        }
        if (dose == 0 || patient == null || drug == null) {
            return DoseStatus.NULL;
        }
        else {
            this.optimalDose = this.doseCalculator.getOptimalDose(patient, drug);
            this.maxDose = this.doseCalculator.getMaxDose(patient, drug);
            this.cumulativeDose = this.doseCalculator.getCumulativeDose(patient, drug, dose, timesADay, duration);
            if (this.cumulativeDose < this.optimalDose/2) {
                return DoseStatus.INSUFFICIENT;
            }
            else if (this.cumulativeDose >= this.optimalDose/2 && this.cumulativeDose < this.maxDose/2) {
                return DoseStatus.OPTIMAL;
            }
            else if (this.cumulativeDose >= this.maxDose/2 && this.cumulativeDose < this.maxDose*0.75) {
                return DoseStatus.OVER_OPTIMAL;
            }
            else if (this.cumulativeDose >= this.maxDose*0.75 && this.cumulativeDose <= this.maxDose) {
                return DoseStatus.RISK_LIMIT;
            }
            else if (dose > this.maxDose){
                return DoseStatus.OVERDOSE;
            }
            else {
                return DoseStatus.CUMULATIVE_OVERDOSE;
            }
        }
    }

    @Override
    public List<String> isAllergic() {
        List<String> patientAllergens = new ArrayList();
        this.prescription.getPatient().getDiagnoses().forEach(d -> d.getDisease().getAllergenList().forEach(a -> patientAllergens.add(a.getName())));
        List<String> drugAllergens = new ArrayList();
        this.prescription.getDrug().getAllergens().forEach(da -> drugAllergens.add(da.getName()));
        List<String> hits = new ArrayList();
        drugAllergens.forEach(das -> patientAllergens.forEach(pas -> {
            if(das.contains(pas)) {
                hits.add(pas);
            }
        }));
        return hits;
    }

    @Override
    public HashMap crossReaction() {
        HashMap<String, String> map = new HashMap<>();
        
        this.prescription.getDrug().getCrossReactions().forEach(b -> 
                map.put(b.getAine1().getName(), b.getAine2().getName()));
        for (int i = 0; i < map.size(); i++){
            System.out.println(map.values().toString());
        }

        return map;
    }
    
    private class Memento {    
        private Prescription original;
        
        public Memento(Prescription prescription) {
            this.original = prescription.clone();
        }
    }
    
}

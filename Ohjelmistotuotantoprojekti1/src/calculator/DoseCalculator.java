/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import resources.drug.ActiveAgent;
import resources.drug.DrugActiveAgent;
import resources.drug.Drug;
import resources.patient.Patient;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class DoseCalculator implements DoseCalculator_IF {
    
    private static final int DAY = 24;
    private CalculatorStrategy converter = null;
    private final AgeCalculator ageCalculator = AgeCalculator.getInstance();
    private final KMFactor factor = KMFactor.getInstance();
    
    @Override
    public void changeStrategy(CalculatorStrategy converter) {
        this.converter = converter;
    }
    
    @Override
    public double getOptimalDose(Patient patient, Drug drug) {
        double min = 0.0;
        double multiplier;
        if (this.converter == null) {
            multiplier = patient.getWeight();
        }
        else {
            multiplier = this.converter.convert(patient.getWeight(), patient.getHeight(), factor.getFactor(this.ageCalculator.calculateAge(patient.getSSN())));
        }
        for (DrugActiveAgent a : drug.getDrugActiveAgents()) {
            double result = a.getActiveAgent().getRecommendedDose()*multiplier/a.getConcentration();
            if (min == 0.0 || min > result) {
                min = result;
            }
        }
        System.out.println(min);
        return min;
    }

    @Override
    public double getMaxDose(Patient patient, Drug drug) {
        double min = 0.0;
        double multiplier;
        if (this.converter == null) {
            multiplier = patient.getWeight();
        }
        else {
            multiplier = this.converter.convert(patient.getWeight(), patient.getHeight(), factor.getFactor(this.ageCalculator.calculateAge(patient.getSSN())));
        }
        for (DrugActiveAgent a : drug.getDrugActiveAgents()) {
            double result = a.getActiveAgent().getMaxDose()*multiplier/a.getConcentration();
            if (min == 0.0 || min > result) {
                min = result;
            }
        }
        return min;
    }

    @Override
    public double getCumulativeDose(Patient patient, Drug drug, double dose, int timesADay, int duration) {
        ActiveAgent limitingActiveAgent = drug.getDrugActiveAgents().get(0).getActiveAgent();
        double min = 0.0;
        for (DrugActiveAgent a : drug.getDrugActiveAgents()) {
            double result =  a.getConcentration()*a.getActiveAgent().getHalfTime()/a.getActiveAgent().getMaxDose();
            if (min == 0.0 || min > result) {
                min = result;
                limitingActiveAgent = a.getActiveAgent();
            }
        }
        double remaining = dose;
        for (int i = 0; i < (timesADay-1)*duration; i++) {
            remaining = dose+remaining*(limitingActiveAgent.getHalfTime()/(DAY/timesADay));
        }
        return remaining;
    }
    
}

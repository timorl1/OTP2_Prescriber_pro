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
    private CalculatorAlgorithm optimalDose = new SingleDoseAlgorithm();
    private CalculatorAlgorithm maxDose = new SingleDoseAlgorithm();
    private CalculatorStrategy converter = null;
    private final AgeCalculator ageCalculator = AgeCalculator.getInstance();
    private final KMFactor factor = KMFactor.getInstance();
    private String optimalDoseString;
    private String maxDoseString;
    private String cumulativeDoseString;
    
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
        Operator[] operators = {Operators.MULTIPLY, Operators.DIVIDE};
        for (DrugActiveAgent a : drug.getDrugActiveAgents()) {
            double[] values = {a.getActiveAgent().getRecommendedDose(), multiplier, a.getConcentration()};
            double result = this.optimalDose.calculate(values, operators);
            if (min == 0.0 || min > result) {
                min = result;
                this.optimalDoseString = this.optimalDose.toString();
            }
        }
        System.out.println("OptimalDose formula: " + this.optimalDoseString);
        return min;
    }

    @Override
    public double getMaxDose(Patient patient, Drug drug) {
        double min = 0.0;
        double multiplier;
        Operator[] operators = {Operators.MULTIPLY, Operators.DIVIDE};
        if (this.converter == null) {
            multiplier = patient.getWeight();
        }
        else {
            multiplier = this.converter.convert(patient.getWeight(), patient.getHeight(), factor.getFactor(this.ageCalculator.calculateAge(patient.getSSN())));
        }
        for (DrugActiveAgent a : drug.getDrugActiveAgents()) {
            double[] values = {a.getActiveAgent().getMaxDose(), multiplier, a.getConcentration()};
            double result = this.maxDose.calculate(values, operators);
            if (min == 0.0 || min > result) {
                min = result;
                this.maxDoseString = this.maxDose.toString();
            }
        }
        System.out.println("MaxDose formula: " + this.maxDoseString);
        return min;
    }

    @Override
    public double getCumulativeDose(Patient patient, Drug drug, double dose, int timesADay, int duration) {
        ActiveAgent limitingActiveAgent = drug.getDrugActiveAgents().get(0).getActiveAgent();
        double min = 0.0;
        String resultString = "d1: " + String.valueOf(dose);
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
            resultString += "\nd" + (i+2) + ": "+ String.valueOf(remaining);
        }
        this.cumulativeDoseString = resultString;
        return remaining;
    }

    @Override
    public String getOptimalDoseFormula() {
        return this.optimalDoseString;
    }

    @Override
    public String getMaxDoseFormula() {
        return this.maxDoseString;
    }
    
    @Override
    public String getCumulativeDoseFormula() {
        return this.cumulativeDoseString;
    }
    
}

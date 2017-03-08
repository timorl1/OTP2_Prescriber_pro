/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


public class DoseCalculator implements DoseCalculator_IF {

    @Override
    public double getOptimalDose(Patient patient, Drug drug) {
        double min = 0.0;
        for (DrugActiveAgent a : drug.getDrugActiveAgents()) {
            double result = a.getActiveAgent().getRecommendedDose()*patient.getWeight()/a.getConcentration();
            if (min == 0.0 || min > result) {
                min = result;
            }
        }
        return min;
    }

    @Override
    public double getMaxDose(Patient patient, Drug drug) {
        double min = 0.0;
        for (DrugActiveAgent a : drug.getDrugActiveAgents()) {
            double result = a.getActiveAgent().getMaxDose()*patient.getWeight()/a.getConcentration();
            if (min == 0.0 || min > result) {
                min = result;
            }
        }
        return min;
    }
    
}

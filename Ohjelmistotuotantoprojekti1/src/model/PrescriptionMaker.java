/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.PrescriptionDAO;
import dao.PrescriptionDAO_IF;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;


public class PrescriptionMaker implements PrescriptionMaker_IF {
    private PrescriptionDAO_IF prescriptionDAO;
    private DoseCalculator_IF doseCalculator;

    public PrescriptionMaker() {
        this.prescriptionDAO = new PrescriptionDAO();
        this.doseCalculator = new DoseCalculator();
    }

    /*@Override
    public Prescription createPrescription(User_IF user) {
        Prescription prescription = new Prescription();
        prescription.setDoctor(user);
        prescription.setDoctorID(user.getUserID());
        prescription.setCreationDate(Date.valueOf(LocalDate.now()));
        return prescription;
    }

    @Override
    public boolean savePrescription(Prescription prescription) {
        return this.prescriptionDAO.createPrescription(prescription);
    }*/

    @Override
    public double getOptimalDose(Prescription prescription) {
        return this.doseCalculator.getOptimalDose(prescription.getPatient(), prescription.getDrug());
    }

    @Override
    public DoseStatus evaluateDose(Prescription prescription) {
        Patient patient = prescription.getPatient();
        Drug drug = prescription.getDrug();
        double dose = prescription.getDose();
        int timesADay = prescription.getTimesADay();
        int duration = 1;
        try{
        LocalDate startDate = prescription.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = prescription.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        duration = Math.toIntExact(days);
        } catch (Exception e) {
        }
        if (dose == 0 || patient == null || drug == null) {
            return DoseStatus.NULL;
        }
        /*else if (timesADay == 0 || duration == 0) {
            if (timesADay != 0) {
                dose = dose*timesADay;
            }
            if (dose < this.doseCalculator.getOptimalDose(patient, drug)/2) {
                return DoseStatus.INSUFFICIENT;
            }
            else if (dose >= this.doseCalculator.getOptimalDose(patient, drug)/2 && dose < this.doseCalculator.getMaxDose(patient, drug)/2) {
                return DoseStatus.OPTIMAL;
            }
            else if (dose >= this.doseCalculator.getMaxDose(patient, drug)/2 && dose < this.doseCalculator.getMaxDose(patient, drug)*0.75) {
                return DoseStatus.OVER_OPTIMAL;
            }
            else if (dose >= this.doseCalculator.getMaxDose(patient, drug)*0.75 && dose <= this.doseCalculator.getMaxDose(patient, drug)) {
                return DoseStatus.RISK_LIMIT;
            }
            else {
                return DoseStatus.OVERDOSE;
            }
        }*/
        else {
            if (this.doseCalculator.getCumulativeDose(patient, drug, dose, timesADay, duration) < this.doseCalculator.getOptimalDose(patient, drug)/2) {
                return DoseStatus.INSUFFICIENT;
            }
            else if (this.doseCalculator.getCumulativeDose(patient, drug, dose, timesADay, duration) >= this.doseCalculator.getOptimalDose(patient, drug)/2 && this.doseCalculator.getCumulativeDose(patient, drug, dose, timesADay, duration) < this.doseCalculator.getMaxDose(patient, drug)/2) {
                return DoseStatus.OPTIMAL;
            }
            else if (this.doseCalculator.getCumulativeDose(patient, drug, dose, timesADay, duration) >= this.doseCalculator.getMaxDose(patient, drug)/2 && this.doseCalculator.getCumulativeDose(patient, drug, dose, timesADay, duration) < this.doseCalculator.getMaxDose(patient, drug)*0.75) {
                return DoseStatus.OVER_OPTIMAL;
            }
            else if (this.doseCalculator.getCumulativeDose(patient, drug, dose, timesADay, duration) >= this.doseCalculator.getMaxDose(patient, drug)*0.75 && this.doseCalculator.getCumulativeDose(patient, drug, dose, timesADay, duration) <= this.doseCalculator.getMaxDose(patient, drug)) {
                return DoseStatus.RISK_LIMIT;
            }
            else if (dose > this.doseCalculator.getMaxDose(patient, drug)){
                return DoseStatus.OVERDOSE;
            }
            else {
                return DoseStatus.CUMULATIVE_OVERDOSE;
            }
        }
    }
    
}

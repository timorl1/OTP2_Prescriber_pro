/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prescriptionMaker;

import resources.prescription.PrescriptionDAO_IF;
import resources.prescription.Prescription;
import resources.prescription.PrescriptionDAO;
import resources.patient.Patient;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import calculator.DoseCalculator;
import calculator.DoseCalculator_IF;
import calculator.DoseStatus;
import resources.drug.Drug;

/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
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

    @Override
    public List<String> isAllergic(Prescription prescription) {
        List<String> patientAllergens = new ArrayList();
        prescription.getPatient().getDiagnoses().forEach(d -> d.getDisease().getAllergenList().forEach(a -> patientAllergens.add(a.getName())));
        List<String> drugAllergens = new ArrayList();
        prescription.getDrug().getAllergens().forEach(da -> drugAllergens.add(da.getName()));
        List<String> hits = new ArrayList();
        drugAllergens.forEach(das -> patientAllergens.forEach(pas -> {
            if(das.contains(pas)) {
                hits.add(pas);
            }
        }));
        return hits;
    }
    
}

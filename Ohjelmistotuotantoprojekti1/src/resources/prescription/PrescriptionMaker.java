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
import resources.drug.Drug;
import resources.user.User_IF;
import calculator.CalculatorStrategy;
/**
 *
 * @author Timo Lehtola, Paula Rinta-Harri, Joonas Siikavirta, Johanna Tani
 */
public class PrescriptionMaker implements PrescriptionMaker_IF {
    private PrescriptionDAO_IF prescriptionDAO;
    private DoseCalculator_IF doseCalculator;
    private final CalculatorStrategy[] strategies = {null, new MonstellerBSAConverter(), new DuboisBSAConverter()};
    
    private double optimalDose;
    private double maxDose;
    private double cumulativeDose;

    public PrescriptionMaker() {
        this.prescriptionDAO = new PrescriptionDAO();
        this.doseCalculator = new DoseCalculator();
    }

    @Override
    public Prescription createPrescription(User_IF user) {
        Prescription prescription = new Prescription();
        prescription.setDoctor(user);
        prescription.setDoctorID(user.getUserID());
        prescription.setTimesADay(1);
        prescription.setCreationDate(Date.valueOf(LocalDate.now()));
        return prescription;
    }

    @Override
    public boolean savePrescription(Prescription prescription) {
        if (prescription.getEndDate()== null || 
                prescription.getStartDate()== null ||
                prescription.getInfo() == null ||
                prescription.getInfo().isEmpty() ||
                prescription.getTimesADay() == 0 ||  
                prescription.getDose() == 0 ||
                prescription.getDiagnoseID() == 0 || 
                prescription.getDrug() == null || 
                prescription.getPatientID() == null ||
                prescription.getPatientID().isEmpty() || 
                prescription.getDoctorID()== 0 ){
            return false;
        }else {
            return this.prescriptionDAO.createPrescription(prescription);
        }
    }

    @Override
    public double getOptimalDose(Prescription prescription) {
        this.optimalDose = this.doseCalculator.getOptimalDose(prescription.getPatient(), prescription.getDrug());
        return this.optimalDose;
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
            //this.optimalDose = this.doseCalculator.getOptimalDose(patient, drug);
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

    @Override
    public void setCalculatorStrategy(int i) {
        this.doseCalculator.changeStrategy(this.strategies[i]);
    }
    
}

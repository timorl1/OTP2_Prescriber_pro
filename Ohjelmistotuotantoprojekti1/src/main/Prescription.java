/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Johanna
 */
public class Prescription {
    
    
    private int id;
    private Patient patient;
    private Doctor doctor;
    //private Drug drug;
    //private Diagnose diagnose;  
    private double dosage; //vai oliko dose?
    private int timesADay;
    private String[] info;
    private String startDate;
    private String endDate;

    public Prescription(int id, Patient patient, Doctor doc, /*Drug drug, Diagnose diagnose,*/ double dosage, int timesADay, String[] info, String startDate, String endDate) {
        this.id = id;
        this.patient = patient;
        this.doctor = doc;
        //this.drug = drug;
        //this.diagnose = diagnose;
        this.dosage = dosage;
        this.timesADay = timesADay;
        this.info = info;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoc() {
        return doctor;
    }

    public void setDoc(Doctor doc) {
        this.doctor = doc;
    }

    /*public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public Diagnose getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
    }*/

    public double getDosage() {
        return dosage;
    }

    public void setDosage(double dosage) {
        this.dosage = dosage;
    }

    public int getTimesADay() {
        return timesADay;
    }

    public void setTimesADay(int timesADay) {
        this.timesADay = timesADay;
    }

    public String[] getInfo() {
        return info;
    }

    public void setInfo(String[] info) {
        this.info = info;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    
    
    
}
